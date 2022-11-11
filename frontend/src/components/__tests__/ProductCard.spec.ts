import { vi, describe, it, expect, type Mock } from "vitest";
import { mount } from "@vue/test-utils";
import { useRoute } from "vue-router";
import ProductCard from "@/components/ProductCard.vue";

vi.mock("vue-router", () => ({
  useRoute: vi.fn(),
  useRouter: vi.fn(() => ({
    push: () => {},
  })),
}));

describe("ProductCard", () => {
  it("renders properly", () => {
    (useRoute as Mock).mockImplementationOnce(() => ({
      params: {
        itemId: "123",
      },
    }));
    const wrapper = mount(ProductCard, { props: { itemId: 123 } });
    wrapper.get("a-card").isVisible();
    expect(wrapper.findAll("a-card")).toHaveLength(1);
    wrapper.get("a-card-meta").isVisible();
    expect(wrapper.findAll("a-card-meta")).toHaveLength(1);
  });
});

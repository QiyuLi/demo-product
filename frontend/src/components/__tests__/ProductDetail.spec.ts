import { vi, describe, it, expect, type Mock } from "vitest";
import { mount } from "@vue/test-utils";
import { useRoute } from "vue-router";

import ProductDetail from "@/components/ProductDetail.vue";

vi.mock("vue-router", () => ({
  useRoute: vi.fn(),
  useRouter: vi.fn(() => ({
    push: () => {},
  })),
}));

describe("ProductDetail", () => {
  it("renders properly", () => {
    (useRoute as Mock).mockImplementationOnce(() => ({
      params: {
        itemId: "123",
      },
    }));
    const wrapper = mount(ProductDetail);
    wrapper.get("a-descriptions").isVisible();
    expect(wrapper.findAll("a-descriptions-item")).toHaveLength(8);
    wrapper.get("a-card").isVisible();
    expect(wrapper.get("a-card").attributes("title")).toBe("Product Detail");
  });
});

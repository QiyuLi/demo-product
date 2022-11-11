<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();
const props = defineProps(["itemId"]);
const item = ref<any>({});

function toDetailView() {
  router.push({ name: "item", params: { itemId: props.itemId } });
}

onMounted(() => {
  axios(
    `http://localhost:8080/api-proxy/service/affil/product/v2/items/${props.itemId}`
  ).then((res) => {
    if (res.data.items.length > 0) {
      item.value = res.data.items[0];
      item.value.customerRating =
        Math.round(item.value.customerRating * 100) / 100;
    }
  });
});
</script>

<template>
  <a-card
    hoverable
    :bodyStyle="{
      padding: '4px',
      height: '200px',
      overflow: 'hidden',
      'text-overflow': 'ellipsis',
    }"
    :bordered="false"
    @click="toDetailView"
  >
    <template #cover>
      <div class="center-image">
        <img style="width: 70%; height: auto" :src="item.thumbnailImage" />
      </div>
    </template>
    <a-card-meta :title="item.name">
      <template #description>
        <div>{{ `Price: $ ${item.salePrice}` }}</div>
        <div>{{ `Rating: ${item.customerRating}` }}</div>
        <br />
        <div v-html="item.shortDescription"></div>
      </template>
    </a-card-meta>
  </a-card>
</template>

<style scoped>
.center-image {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>

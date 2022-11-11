<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import { unescape } from "lodash";

const route = useRoute();
const item = ref<any>({});

onMounted(() => {
  axios(
    `http://localhost:8080/api-proxy/service/affil/product/v2/items/${route.params.itemId}`
  ).then((res) => {
    if (res.data.items.length > 0) {
      item.value = res.data.items[0];
      item.value.longDescription = unescape(item.value.longDescription);
      item.value.customerRating =
        Math.round(item.value.customerRating * 100) / 100;
    }
  });
});
</script>

<template>
  <a-row>
    <a-col
      :xs="24"
      :sm="24"
      :md="24"
      :lg="12"
      :xl="12"
      class="detailImageContainer"
      ><img style="height: auto" :src="item.largeImage"
    /></a-col>
    <a-col
      :xs="24"
      :sm="24"
      :md="24"
      :lg="12"
      :xl="12"
      class="descriptionContainer"
    >
      <a-descriptions title="Specifications">
        <a-descriptions-item label="Name" :span="12">{{
          item.name
        }}</a-descriptions-item>
        <a-descriptions-item label="Price" :span="12">{{
          `$ ${item.salePrice}`
        }}</a-descriptions-item>
        <a-descriptions-item label="Stock" :span="12">{{
          item.stock
        }}</a-descriptions-item>
        <a-descriptions-item label="Brand" :span="12">{{
          item.brandName
        }}</a-descriptions-item>
        <a-descriptions-item label="Color" :span="12">{{
          item.color
        }}</a-descriptions-item>
        <a-descriptions-item label="Rating" :span="12">{{
          item.customerRating
        }}</a-descriptions-item>
        <a-descriptions-item label="Free to ship to store" :span="12">{{
          item.freeShipToStore ? "Yes" : "No"
        }}</a-descriptions-item>
        <a-descriptions-item label="Two days free shipping" :span="12">{{
          item.isTwoDayShippingEligible ? "Yes" : "No"
        }}</a-descriptions-item>
      </a-descriptions>
    </a-col>
  </a-row>
  <a-card title="Product Detail" :bordered="true">
    <div v-html="item.longDescription"></div>
  </a-card>
</template>

<style scoped>
.ant-card-cover {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>

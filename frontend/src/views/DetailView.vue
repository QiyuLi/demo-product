<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";

import ProductCard from "@/components/ProductCard.vue";
import ProductDetail from "@/components/ProductDetail.vue";

const router = useRouter();
const route = useRoute();
const item = ref<any>({});
const recommends = ref<any[]>([]);

function back() {
  router.push("/");
}

onMounted(() => {
  axios(
    `http://localhost:8080/api-proxy/service/affil/product/v2/items/${route.params.itemId}`
  ).then((res) => {
    if (res.data.items.length > 0) {
      item.value = res.data.items[0];
    }
  });
  axios(
    `http://localhost:8080/api-proxy/service/affil/product/v2/nbp?itemId=${route.params.itemId}`
  ).then((res) => {
    recommends.value = res.data.slice(0, 10);
  });
});
</script>

<template>
  <main>
    <a-page-header class="navBar" :title="item.name" @back="back" />
    <div class="searchResult">
      <ProductDetail :itemId="item.itemId" />
      <a-row class="recommendList">
        <a-row
          ><span style="font-size: x-large"
            >Customers also considered</span
          ></a-row
        >
        <a-list
          :grid="{
            gutter: 16,
            xs: 1,
            sm: 2,
            md: 4,
            lg: 6,
            xl: 7,
            xxl: 8,
            xxxl: 8,
          }"
          :data-source="recommends"
        >
          <template #renderItem="{ item }">
            <a-list-item>
              <ProductCard :itemId="item.itemId" />
            </a-list-item>
          </template>
        </a-list>
      </a-row>
    </div>
  </main>
</template>

<style scoped>
.ant-card-body {
  padding: 4px;
  height: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.ant-card-body {
  padding: 4px;
  height: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>

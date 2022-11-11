<script setup lang="ts">
import { ref } from "vue";
import axios from "axios";

import ProductCard from "@/components/ProductCard.vue";

const searchContent = ref("");
const items = ref<any[]>([]);

function search() {
  axios("http://localhost:8080/api-proxy/service/affil/product/v2/search", {
    params: {
      query: searchContent.value,
    },
  }).then((res) => {
    items.value = res.data.items.slice(0, 10);
  });
}
</script>

<template>
  <main>
    <a-row class="searchBar">
      <a-input-group compact>
        <a-input
          v-model:value="searchContent"
          style="width: calc(100% - 100px)"
          placeholder="Enter product name"
          @keyup.enter="search"
        />
        <a-button type="primary" class="searchButton" @click="search"
          >Search</a-button
        >
      </a-input-group>
    </a-row>
    <a-list
      :grid="{
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 2,
        lg: 4,
        xl: 5,
        xxl: 5,
        xxxl: 5,
      }"
      class="searchResult"
      :data-source="items"
    >
      <template #renderItem="{ item }">
        <a-list-item>
          <ProductCard :itemId="item.itemId" />
        </a-list-item>
      </template>
    </a-list>
  </main>
</template>

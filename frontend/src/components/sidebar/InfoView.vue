<template>
  <div>
    <!-- 실시간 인기지역 -->
    <b-card id="popular-area" class="mt-3">
      <b-row class="ml-2">
        <h3>실시간 인기지역</h3>
      </b-row>
      <b-row>
        <b-list-group class="mt-3">
          <b-list-group-item
            href="#"
            class="outline-light list-item"
            v-for="(area, index) in popularArea"
            :key="index"
          >
            {{ index + 1 }}. {{ area.gugun }} {{ area.dong }}
          </b-list-group-item>
        </b-list-group>
      </b-row>
    </b-card>
    <!-- 부동산 관련 뉴스 -->
    <b-card id="news" class="mt-3">
      <b-row class="ml-2"><h3>오늘의 뉴스</h3></b-row>
      <b-row>
        <b-list-group class="mt-3">
          <news-list-item
            v-for="news in newsList"
            :key="news.title"
            v-bind="news"
          >
          </news-list-item>
        </b-list-group>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import NewsListItem from "@/components/sidebar/news/NewsListItem.vue";
import { getNewsDatas } from "@/api/news";

export default {
  name: "InfoView",
  components: {
    NewsListItem,
  },
  data() {
    return {
      newsList: [],
      popularArea: [
        {
          gugun: "서구",
          dong: "내당동",
          code: "1",
        },
        {
          gugun: "서구",
          dong: "내당동",
          code: "2",
        },
        {
          gugun: "서구",
          dong: "내당동",
          code: "3",
        },
        {
          gugun: "서구",
          dong: "내당동",
          code: "4",
        },
        {
          gugun: "서구",
          dong: "내당동",
          code: "5",
        },
        {
          gugun: "서구",
          dong: "내당동",
          code: "6",
        },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    getNewsDatas(
      param,
      (response) => {
        this.newsList = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped>
b-col {
  margin: 10px;
}

.list-item {
  border: none;
}
</style>

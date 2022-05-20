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
          <b-list-group-item
            :href="news.url"
            class="outline-light list-item"
            v-for="(news, index) in todayNews"
            :key="index"
          >
            {{ news.title }}
          </b-list-group-item>
        </b-list-group>
      </b-row>
    </b-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
      todayNews: [
        {
          url: "#",
          title: "오늘의 뉴스 전세의 월세화 가속도… 8월 어쩌나",
        },
        {
          url: "#",
          title: "부동산 규제 완화 강드라이브… 시장은 기대반 우려반",
        },
        {
          url: "#",
          title: "시행 2년 된 임대차법, 어떻게 손질 될까",
        },
        {
          url: "#",
          title: "규제 완화 기대감에… 서울 주민 ‘원정투자’ 다시 고개",
        },
        {
          url: "#",
          title: "서울 아파트 '사자' 심리 다시 주춤…한주만에 매매수급지수 하락",
        },
      ],
    };
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

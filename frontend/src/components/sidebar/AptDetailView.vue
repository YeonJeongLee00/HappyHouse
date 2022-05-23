<template>
  <div id="aptDetail">
    <h3 class="mt-4 green-color">{{ aptName }}</h3>
    <line-chart-view class="mt-4" :dealList="dealList"></line-chart-view>
    <table class="table-style mt-4">
      <thead>
        <th>거래가격</th>
        <th>년</th>
        <th>월</th>
        <th>일</th>
      </thead>
      <tbody>
        <tr v-for="(deal, index) in dealList" :key="index">
          <td>{{ deal.dealAmount }}</td>
          <td>{{ deal.dealYear }}</td>
          <td>{{ deal.dealMonth }}</td>
          <td>{{ deal.dealDay }}</td>
        </tr>
      </tbody>
    </table>
    <b-card class="mt-4 mb-5">
      <!-- 카카오 api로 가능 -->
      <h5 class="green-color">
        편의 시설
        <font-awesome-icon icon="fa-solid fa-map-location-dot" class="icon" />
      </h5>
      <li>편의점!!!</li>
    </b-card>
  </div>
</template>

<script>
import LineChartView from "@/components/sidebar/LineChartView.vue";

export default {
  data() {
    return {
      stickyHeader: true,
      aptName: "광화문풍림스페이스본",
      dealList: [
        { dealAmount: 105000, dealYear: 2019, dealMonth: 1, dealDay: 7 },
        { dealAmount: 119000, dealYear: 2019, dealMonth: 1, dealDay: 19 },
        { dealAmount: 119000, dealYear: 2019, dealMonth: 1, dealDay: 30 },
        { dealAmount: 150000, dealYear: 2019, dealMonth: 3, dealDay: 4 },
        { dealAmount: 104800, dealYear: 2019, dealMonth: 3, dealDay: 8 },
      ],
      aptNo: null,
    };
  },
  components: {
    LineChartView,
  },
  created() {
    let aptNo = this.$route.params.aptNo;
    console.log(aptNo);
  },
};
</script>

<style>
#aptDetail .icon {
  color: #ffba00;
}

.table-style {
  border-collapse: collapse;
  width: 100%;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
}

.table-style thead {
  background-color: #6d9773;
  color: #ffffff;
  font-weight: 100;
}

.table-style th,
.table-style td {
  padding: 12px 15px;
}

.table-style tbody tr {
  border-bottom: 1px solid #dddddd;
}

.table-style tbody tr:nth-of-type(even) {
  background-color: rgb(109, 151, 115, 0.2);
}

.table-style tbody tr:last-of-type {
  border-bottom: 2px solid #6d9773;
}
</style>

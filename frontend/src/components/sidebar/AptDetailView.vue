<template>
  <div id="aptDetail">
    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#apartDetail"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >{{ house.apartmentName }}</b-button
      >
    </div>
    <b-collapse id="apartDetail">
      <b-card
        ><b-table-simple striped class="mt-3 b-table-style">
          <b-thead>
            <b-tr>
              <b-th class="col-md-4 head">분류</b-th>
              <b-th class="col-md-5 head">정보</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr>
              <b-th class="col-md-3 head">도로명 주소</b-th>
              <b-th class="col-md-9 head"
                >{{ house.sidoName }} {{ house.gugunName }} {{ house.roadName
                }}<br />
                ({{ house.dongName }}, {{ house.apartmentName }})
              </b-th>
            </b-tr>
            <b-tr>
              <b-th class="col-md-2 head">법정동 주소</b-th>
              <b-th class="col-md-9 head"
                >{{ house.sidoName }} {{ house.gugunName }}
                {{ house.dongName }} {{ house.jibun }}<br />
                ({{ house.apartmentName }})
              </b-th>
            </b-tr>
            <b-tr>
              <b-th class="col-md-2 head">설립 연도</b-th>
              <b-th class="col-md-9 head">{{ house.buildYear }}년</b-th>
            </b-tr>
          </tbody>
        </b-table-simple></b-card
      >
    </b-collapse>
    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#apartGraph"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >거래 내역 그래프</b-button
      >
    </div>

    <b-collapse id="apartGraph">
      <b-card
        ><div align="center">
          <b-button class="b-button ml-2 mr-2" @click="allList" :type="five"
            >5년</b-button
          >
          <b-button class="b-button ml-2 mr-2" @click="oneYearList" :type="one"
            >1년</b-button
          >
          <b-button
            class="b-button ml-2 mr-2"
            @click="groupYearList"
            :type="group"
            >연도별</b-button
          >
        </div>
        <line-chart-view class="mt-4" :dealList="dealList"> </line-chart-view>

        <!-- 그룹 출력 -->

        <table class="table-style mt-4" v-if="type === 'group'">
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

        <!-- 기본 출력 -->
        <table class="table-style mt-4" v-else>
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
      </b-card>
    </b-collapse>
    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#apartSide"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >주변 편의 시설</b-button
      >
    </div>
    <b-collapse id="apartSide">
      <b-card>
        <h5 class="green-color">
          편의 시설
          <font-awesome-icon icon="fa-solid fa-map-location-dot" class="icon" />
        </h5>
        <li>편의점!!!</li>
      </b-card>
    </b-collapse>
    <h3 class="mt-4 green-color">{{ aptName }}</h3>
  </div>
</template>

<script>
import LineChartView from "@/components/sidebar/LineChartView.vue";
import {
  selectHouse,
  listHouse,
  listHouseOneYear,
  listHouseYear,
} from "@/api/apt/";
import { mapState } from "vuex";
import { addLikeApt } from "@/api/like.js";

const userStore = "userStore";
const likeStore = "likeStore";

export default {
  data() {
    return {
      name: "AptDetailView",
      components: { selectHouse, listHouse },
      house: {},
      stickyHeader: true,
      dealList: [],
      type: "",
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(likeStore, ["likeApt"]),
  },
  components: {
    LineChartView,
  },
  created() {
    console.log(this.$route.params.aptCode);
    selectHouse(
      this.$route.params.aptCode,
      (response) => {
        console.log(response.data);
        this.house = response.data[0];
      },
      (error) => {
        console.log("아파트정보를 가져오지 못했습니다.", error);
      }
    );
    this.aptCode = this.$route.params.aptCode;
    this.likeApt.forEach((element) => {
      console.log(element);
    });
  },
  methods: {
    allList() {
      listHouse(
        this.$route.params.aptCode,
        (response) => {
          this.dealList = response.data;
        },
        (error) => {
          console.log("아파트정보를 가져오지 못했습니다.", error);
        }
      );
    },
    oneYearList() {
      listHouseOneYear(
        this.$route.params.aptCode,
        (response) => {
          this.dealList = response.data;
        },
        (error) => {
          console.log("아파트정보를 가져오지 못했습니다.", error);
        }
      );
    },
    groupYearList() {
      console.log(this.type);
      listHouseYear(
        this.$route.params.aptCode,
        (response) => {
          this.dealList = response.data;
        },
        (error) => {
          console.log("아파트정보를 가져오지 못했습니다.", error);
        }
      );
    },

    addApt() {
      const data = {
        user_id: this.userInfo.id,
        houseinfo_aptCode: this.aptCode,
      };
      addLikeApt(
        data,
        ({ data }) => {
          console.log(data);
        },
        () => {}
      );
    },
    deleteApt() {},
  },
};
</script>

<style scoped>
.b-button {
  width: 100px;
  background-color: #ffba00;
  border: #ffba00;
  color: white;
  font-weight: bold;
}

.button {
  background-color: #6d9773;
  border-color: #6d9773;
  height: 60px;
  font-size: 20px;
  font-weight: bold;
}

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

.b-table-style {
  border-collapse: collapse;
  width: 100%;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
}

.b-table-style b-thead b-tr {
  background-color: #6d9773;
  color: #ffffff;
  font-weight: 100;
}

.b-table-style b-th,
.b-table-style b-td {
  padding: 12px 15px;
}

.b-table-style b-tbody b-tr {
  border-bottom: 1px solid #dddddd;
}

.b-table-style b-tbody b-tr:nth-of-type(even) {
  background-color: rgb(109, 151, 115, 0.2);
}

.b-table-style b-tbody b-tr:last-of-type {
  border-bottom: 2px solid #6d9773;
}
.non-selected-heart,
.selected-heart {
  color: #d62042;
}
</style>

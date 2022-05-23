<template>
  <div id="side-bar">
    <b-container class="make-scroll" right shadow>
      <!-- 시, 구, 동 선택 -->
      <div id="search">
        <!-- 시/도 -->
        <div class="search-bar">
          <div class="full mr-3">
            <b-form-select
              :options="sidos"
              v-model="selectedSido"
              size="lg"
              class="sido"
              @change="gugunList"
            >
            </b-form-select>
          </div>
          <!-- 구/ 군 -->
          <div class="full mr-3">
            <b-form-select
              v-model="selectedGugun"
              :options="guguns"
              size="lg"
              class="gugun"
              @change="dongList"
            ></b-form-select>
          </div>
          <!-- 읍  / 면/ 동 -->
          <!-- :disabled="isSelected" 설정 -->
          <div class="full">
            <b-form-select
              v-model="selectedDong"
              :options="dongs"
              size="lg"
              class="dong"
            ></b-form-select>
          </div>
        </div>
        <div class="mr-2">
          <!-- 검색 버튼 -->
          <b-button @click="aptSearch" class="search-btn full mr-2" size="lg">
            검색</b-button
          >
        </div>
      </div>

      <div id="like-area" v-if="isLogin">
        <b-button-group
          size="sm"
          class="mr-2 mt-2"
          v-for="(area, index) in likeArea"
          :key="index"
        >
          <b-button class="like-btn" @click="likeAptList(area.dongCode)"
            >{{ area.gugunName }} {{ area.dongName }}</b-button
          >
          <b-button class="like-icon" @click="deleteArea(area.no)"
            ><font-awesome-icon icon="fa-solid fa-star" class="icon"
          /></b-button>
        </b-button-group>
      </div>
      <!-- url에 따라서 변경되는 화면  -->
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { deleteLikeArea } from "@/api/like.js";

const aptStore = "aptStore";
const userStore = "userStore";
const likeStore = "likeStore";

export default {
  data() {
    return {
      // isSelected: false,
      selectedSido: null,
      selectedGugun: null,
      selectedDong: null,
    };
  },
  computed: {
    ...mapState(aptStore, ["sidos", "guguns", "houses", "dongs"]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(likeStore, ["likeArea"]),
  },
  created() {
    this.CLEAR_SIDO_LIST(); // 초기화
    this.getSido(); // sidolist 가져오기
    this.getLikeArea(this.userInfo.id); // 사용자 getLikeArea 가져오기
  },
  methods: {
    ...mapActions(aptStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "areaName",
    ]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    ...mapActions(likeStore, ["getLikeArea"]),
    //  검색 버튼 눌렀을 때
    aptSearch() {
      if (this.selectedDong) {
        this.getHouseList(this.selectedDong);
        this.areaName(this.selectedDong);
        this.$router.push({
          name: "aptView",
        });
      }
    },
    gugunList() {
      console.log(this.selectedSido);
      this.CLEAR_GUGUN_LIST();
      this.selectedGugun = null;
      if (this.selectedSido) this.getGugun(this.selectedSido);
    },
    dongList() {
      console.log(this.selectedGugun);
      this.CLEAR_DONG_LIST();
      this.selectedDong = null;
      if (this.selectedGugun) this.getDong(this.selectedGugun);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
    deleteArea(no) {
      deleteLikeArea(
        no,
        () => {
          this.getLikeArea(this.userInfo.id);
        },
        () => {}
      );
    },
    // 관심 지역 버튼 눌렀을 때 처리
    // select box 관심지역 처리
    // 검색 처리
    //
    likeAptList(dongCode) {
      let sidoCode = dongCode.substr(0, 2);
      let gugunCode = dongCode.substr(2, 5);
      this.selectedSido = sidoCode;
      this.selectedGugun = gugunCode;
    },
  },
  components: {},
};
</script>

<style scoped>
#like-area .like-btn {
  background-color: white;
  border-color: #ffba00;
  color: #0c3b3e;
}
#like-area .like-btn:hover {
  background-color: #ffba00;
  border-color: #ffba00;
  color: white;
}

#like-area .like-icon {
  background-color: white;
  border-color: #ffba00;
  color: #ffba00;
}

#like-area .like-icon:hover {
  background-color: #ffba00;
  border-color: #ffba00;
  color: white;
}

#search .search-btn {
  background-color: #0c3b3e;
}

#search .search-btn:hover {
  color: #0c3b3e;
  background-color: white;
}

#search .sido,
#search .gugun,
#search .dong {
  border-color: #0c3b3e;
  color: #0c3b3e;
  width: 100%;
}

#search .full {
  width: 100%;
}

#search {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  margin-top: 30px;
}

#search .search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 80%;
}
</style>

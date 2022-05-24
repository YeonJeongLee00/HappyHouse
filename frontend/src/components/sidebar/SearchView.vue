<template>
  <div id="side-bar">
    <b-container class="container make-scroll" right shadow>
      <!-- 시, 구, 동 선택 -->
      <div id="search">
        <!-- 시/도 -->
        <div class="search-bar">
          <div class="full mr-3">
            <b-form-select
              :options="sido"
              v-model="selectedSido"
              size="lg"
              class="sido"
            >
            </b-form-select>
          </div>
          <!-- 구/ 군 -->
          <div class="full mr-3">
            <b-form-select
              v-model="selectedGugun"
              :options="gugun"
              size="lg"
              class="gugun"
            ></b-form-select>
          </div>
          <!-- 읍  / 면/ 동 -->
          <div class="full">
            <b-form-select
              v-model="selectedDong"
              :options="dong"
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

      <div id="like-area" class="mt-3">
        <b-button-group size="sm" class="mr-2">
          <b-button class="like-btn">달서구 상인동 </b-button>
          <b-button class="like-icon"
            ><font-awesome-icon icon="fa-solid fa-star" class="icon"
          /></b-button>
        </b-button-group>
        <b-button-group size="sm" class="mr-2">
          <b-button class="like-btn">동작구 사당동</b-button>
          <b-button class="like-icon"
            ><font-awesome-icon icon="fa-solid fa-star" class="icon"
          /></b-button>
        </b-button-group>
        <b-button-group size="sm" class="mr-2">
          <b-button class="like-btn">유성구 궁동 </b-button>
          <b-button class="like-icon"
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
export default {
  data() {
    return {
      selectedSido: null,
      selectedGugun: null,
      selectedDong: null,
      sido: [
        { value: null, text: "시/도", disabled: true },
        { value: "a", text: "대구광역시" },
        { value: "b", text: "서울특별시" },
        { value: "c", text: "부산광역시" },
      ],
      gugun: [
        { value: null, text: "구/군", disabled: true },
        { value: "d", text: "달서구" },
        { value: "e", text: "중구" },
        { value: "f", text: "동구" },
      ],
      dong: [
        { value: null, text: "읍/면/동", disabled: true },
        { value: "g", text: "월성동" },
        { value: "h", text: "상인동" },
        { value: "j", text: "진천동" },
      ],
    };
  },
  methods: {
    moveMapPoint() {},
    aptSearch() {
      this.$router.push("/search");
    },
  },
  components: {},
};
</script>

<style scoped>
.container {
  height: 92vh;
  overflow: auto;
}
.container::-webkit-scrollbar {
  width: 10px;
}
.container::-webkit-scrollbar-thumb {
  background-color: #ffba00;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.container::-webkit-scrollbar-track {
  background-color: #0c3b2e;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
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

<template>
  <div>
    <!-- 찜한 아파트 -->
    <b-card id="popular-area" class="mt-3" v-if="isLogin">
      <b-row class="ml-2 toggle-set" @click="AptToggle">
        <div><h3>관심 아파트</h3></div>
        <div class="mr-3">
          <h3>
            <font-awesome-icon
              v-if="!likeAptToggle"
              icon="fa-solid fa-angle-down"
              class="icon"
            />
            <font-awesome-icon
              v-if="likeAptToggle"
              icon="fa-solid fa-angle-up"
              class="icon"
            />
          </h3>
        </div>
      </b-row>
      <b-row v-show="likeAptToggle">
        <b-list-group class="mt-3 ml-2">
          <b-list-group-item
            href="#"
            class="outline-light list-item"
            v-for="(apt, index) in likeApt"
            :key="index"
          >
            {{ apt.apartmentName }}
            <span>
              <font-awesome-icon
                class="ml-2 selected-heart"
                icon="fa-solid fa-heart"
              />
            </span>
          </b-list-group-item>
        </b-list-group>
      </b-row>
    </b-card>
    <!-- 실시간 인기지역 -->
    <b-card id="popular-area" class="mt-3">
      <b-row class="ml-2 toggle-set">
        <div><h3>실시간 인기지역</h3></div>
        <div class="mr-3">
          <h3 @click="AreaToggle">
            <font-awesome-icon
              v-if="!pouplarAreaToggle"
              icon="fa-solid fa-angle-down"
              class="icon"
            />
            <font-awesome-icon
              v-if="pouplarAreaToggle"
              icon="fa-solid fa-angle-up"
              class="icon"
            />
          </h3>
        </div>
      </b-row>
      <b-row v-show="pouplarAreaToggle">
        <b-list-group class="mt-3">
          <b-list-group-item
            href="#"
            class="outline-light list-item"
            v-for="(area, index) in popularArea"
            :key="index"
            @click="popularAreaMove(area.dongCode)"
          >
            {{ index + 1 }}. {{ area.gugunName }} {{ area.dongName }}
          </b-list-group-item>
        </b-list-group>
      </b-row>
    </b-card>
    <!-- 부동산 관련 뉴스 -->
    <b-card id="news" class="mt-3">
      <b-row class="ml-2 toggle-set">
        <div><h3>오늘의 뉴스</h3></div>
        <div class="mr-3">
          <h3 @click="NewsToggle">
            <font-awesome-icon
              v-if="!newsToggle"
              icon="fa-solid fa-angle-down"
              class="icon"
            />
            <font-awesome-icon
              v-if="newsToggle"
              icon="fa-solid fa-angle-up"
              class="icon"
            />
          </h3>
        </div>
      </b-row>
      <b-row v-show="newsToggle">
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
import { getPopularArea } from "@/api/like";
import { mapState, mapActions, mapMutations } from "vuex";

const likeStore = "likeStore";
const userStore = "userStore";
const aptStore = "aptStore";

export default {
  name: "InfoView",
  components: {
    NewsListItem,
  },
  data() {
    return {
      newsList: [],
      popularArea: [],
      pouplarAreaToggle: true,
      newsToggle: true,
      likeAptToggle: true,
    };
  },
  computed: {
    ...mapState(likeStore, ["likeApt"]),
    ...mapState(userStore, ["isLogin"]),
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
    getPopularArea(
      ({ data }) => {
        this.popularArea = data;
      },
      () => {}
    );
  },
  methods: {
    ...mapActions(aptStore, ["changeSelect", "getDong", "getGugun"]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    AreaToggle() {
      console.log("areaToggle");
      this.pouplarAreaToggle = !this.pouplarAreaToggle;
    },
    NewsToggle() {
      this.newsToggle = !this.newsToggle;
    },
    AptToggle() {
      this.likeAptToggle = !this.likeAptToggle;
    },
    popularAreaMove(code) {
      console.log(code);
      let sido = code.substr(0, 2);
      let gugun = code.substr(0, 5);
      let dong = code;
      let payload = {
        sido,
        gugun,
        dong,
      };
      this.$emit("abc-ssafy", payload);
      // this.changeSelect(payload);

      // this.CLEAR_GUGUN_LIST();
      // this.CLEAR_DONG_LIST();
      // this.getGugun(payload.sido);
      // this.getDong(payload.gugun);
    },
  },
};
</script>

<style scoped>
b-col {
  margin: 10px;
}
.icon {
  color: #6d9773;
}
.list-item {
  border: none;
}
.toggle-set {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
}
.non-selected-heart,
.selected-heart {
  color: #d62042;
}
</style>

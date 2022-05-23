<template>
  <div>
    <b-row class="mt-4 mb-3"
      ><b-col class="green-color"
        ><h4>
          {{ areaName }}
          <!-- 선택 안함 -->
          <span class="ml-2" v-if="isLogin">
            <font-awesome-icon
              icon="fa-regular fa-star"
              class="icon-non-selected"
              v-if="!isSelected"
              @click="addArea"
            />
            <!-- 선택 함 -->
            <font-awesome-icon
              icon="fa-solid fa-star"
              class="icon-selected"
              v-if="isSelected"
              @click="removeStar"
            />
          </span>
        </h4>
      </b-col>
    </b-row>
    <b-card id="aptList" class="">
      <b-row class="ml-2"><h4 class="green-color">아파트</h4> </b-row>
      <b-row class="ml-2">
        <b-list-group class="mt-3">
          <b-list-group-item
            @click="aptDetail(apt.no)"
            class="outline-light list-item"
            v-for="apt in houses"
            :key="apt.no"
          >
            {{ apt.aptName }}
          </b-list-group-item>
        </b-list-group>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { addLikeArea } from "@/api/like.js";

const aptStore = "aptStore";
const userStore = "userStore";
const likeStore = "likeStore";

export default {
  data() {
    return {
      isSelected: false,
    };
  },
  created() {},
  computed: {
    ...mapState(aptStore, ["houses", "areaName", "code"]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(likeStore, ["getLikeArea"]),
    aptDetail(code) {
      this.$router.push({
        name: "aptDetail",
        params: {
          aptNo: code,
        },
      });
    },
    overStar() {
      this.isSelected = !this.isSelected;
    },
    addArea() {
      console.log("addArea in!");
      const data = {
        user_id: this.userInfo.id,
        dongCode: this.code,
      };
      addLikeArea(
        data,
        () => {
          this.getLikeArea(this.userInfo.id);
        },
        () => {}
      );
    },
    removeArea() {},
  },
};
</script>

<style scoped>
.list-item {
  border: none;
  color: #0c3b3e;
  width: 100%;
}

.icon-non-selected,
.icon-selected {
  color: #ffba00;
}
</style>

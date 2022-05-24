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
              @click="removeArea"
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
            @click="aptDetail(apt.aptCode)"
            class="outline-light list-item"
            v-for="(apt, index) in houses"
            :key="apt.aptCode"
          >
            {{ apt.apartmentName }}
            <font-awesome-icon
              v-if="!aptSelectedState[index]"
              class="ml-2 non-selected-heart"
              icon="fa-regular fa-heart"
            />
            <font-awesome-icon
              v-if="aptSelectedState[index]"
              class="ml-2 selected-heart"
              icon="fa-solid fa-heart"
            />
          </b-list-group-item>
        </b-list-group>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { addLikeArea, deleteLikeArea } from "@/api/like.js";

const aptStore = "aptStore";
const userStore = "userStore";
const likeStore = "likeStore";

export default {
  data() {
    return {
      isSelected: false,
      no: null,
      title: null,
      aptSelectedState: [],
    };
  },
  created() {
    this.isSelected = false;
    this.likeArea.forEach((element) => {
      if (element.dongCode == this.code) {
        this.isSelected = true;
        this.no = element.no;
        return false;
      }
    });
    this.getLikeApt(this.userInfo.id);
    this.houses.forEach((house) => {
      let check = false;
      this.likeApt.forEach((apt) => {
        if (apt.aptCode == house.aptCode) {
          check = true;
          return false;
        }
      });
      this.aptSelectedState.push(check);
    });
  },
  watch: {
    areaName() {
      this.setIcon();
    },
    likeApt() {
      this.setLikeIcon();
    },
  },
  computed: {
    ...mapState(aptStore, ["houses", "areaName", "code"]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(likeStore, ["likeArea", "likeApt"]),
  },
  methods: {
    ...mapActions(likeStore, ["getLikeArea", "getLikeApt"]),
    aptDetail(code) {
      this.$router.push({
        name: "aptDetail",
        params: {
          aptCode: code,
        },
      });
    },
    // 관심 지역 추가
    async addArea() {
      const data = {
        user_id: this.userInfo.id,
        dongCode: this.code,
      };
      await addLikeArea(
        data,
        () => {
          console.log("addlikeArea");
        },
        () => {}
      );
      await this.getLikeArea(this.userInfo.id);
      this.setIcon();
    },
    // 관심 지역 삭제
    async removeArea() {
      await deleteLikeArea(
        this.no,
        () => {
          this.getLikeArea(this.userInfo.id);
          this.setIcon();
        },
        () => {}
      );
    },
    setIcon() {
      console.log("in setIcon");
      this.isSelected = false;
      this.likeArea.forEach((element) => {
        if (element.dongCode == this.code) {
          this.isSelected = true;
          this.no = element.no;
          return false;
        }
      });
    },
    setLikeIcon() {
      this.houses.forEach((house) => {
        let check = false;
        this.likeApt.forEach((apt) => {
          if (apt.aptCode == house.aptCode) {
            check = true;
            return false;
          }
        });
        this.aptSelectedState.push(check);
      });
    },
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

.non-selected-heart,
.selected-heart {
  color: #d62042;
}
</style>

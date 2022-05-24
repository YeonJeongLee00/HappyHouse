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
              v-if="!aptSelectedState[index] && isLogin"
              class="ml-2 non-selected-heart"
              icon="fa-regular fa-heart"
            />
            <font-awesome-icon
              v-if="aptSelectedState[index] && isLogin"
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
import { mapActions, mapMutations, mapState } from "vuex";
import { addLikeArea, deleteLikeArea } from "@/api/like.js";

const aptStore = "aptStore";
const userStore = "userStore";
const likeStore = "likeStore";
const mapStore = "mapStore";

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
    if (this.isLogin) {
      this.likeArea.forEach((element) => {
        if (element.dongCode == this.code) {
          this.isSelected = true;
          this.no = element.no;
          return false;
        }
      });
      this.getLikeApt(this.userInfo.id);
      this.aptSelectedState = [];
      this.houses.forEach((house) => {
        let check = false;
        this.likeApt.forEach((apt) => {
          if (apt.aptCode == house.aptCode) {
            check = true;
            return false;
          }
        });
        console.log("insss!!!");
        this.aptSelectedState.push(check);
      });
    }
  },
  watch: {
    areaName() {
      this.setIcon();
      // this.setLikeIcon();
    },
    likeArea() {
      this.setIcon();
    },
    likeApt() {
      this.setLikeIcon();
    },
    houses() {
      this.setLikeIcon();
      console.log();
      for (let index = 0; index < this.dongs.length; index++) {
        if (this.dongs[index].value == this.code) {
          console.log(this.dongsPoint[index - 1].name);
          console.log(
            this.dongsPoint[index - 1].lng +
              "   " +
              this.dongsPoint[index - 1].lat
          );
          this.SET_LNG(this.dongsPoint[index - 1].lng);
          this.SET_LAT(this.dongsPoint[index - 1].lat);
          break;
        }
      }
    },
  },
  computed: {
    ...mapState(aptStore, [
      "houses",
      "areaName",
      "code",
      "dongs",
      "dongsPoint",
    ]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(likeStore, ["likeArea", "likeApt"]),
  },
  methods: {
    ...mapActions(likeStore, ["getLikeArea", "getLikeApt"]),
    ...mapMutations(mapStore, ["SET_LNG", "SET_LAT"]),
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
      await this.setIcon();
    },
    // 관심 지역 삭제
    async removeArea() {
      await deleteLikeArea(
        this.no,
        () => {},
        () => {}
      );
      await this.getLikeArea(this.userInfo.id);
      await this.setIcon();
    },
    setIcon() {
      console.log("in setIcon");
      this.isSelected = false;
      if (this.isLogin && this.likeArea.length > 0) {
        this.likeArea.forEach((element) => {
          if (element.dongCode == this.code) {
            this.isSelected = true;
            this.no = element.no;
            return false;
          }
        });
      }
    },
    setLikeIcon() {
      console.log(this.aptSelectedState);
      const temp = [];
      if (this.isLogin && this.likeApt.length > 0) {
        this.houses.forEach((house) => {
          let check = false;
          this.likeApt.forEach((apt) => {
            if (apt.aptCode == house.aptCode) {
              check = true;
              return false;
            }
          });
          console.log("in!!!");
          temp.push(check);
        });
      }
      this.aptSelectedState = temp;
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

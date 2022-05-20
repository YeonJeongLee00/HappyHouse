<template>
  <b-card id="aptList" class="mt-3">
    <b-row class="ml-2"><h4 class="green-color">아파트</h4> </b-row>
    <b-row class="ml-2">
      <b-list-group class="mt-3">
        <b-list-group-item
          @click="aptDetail(apt.aptCode)"
          class="outline-light list-item"
          v-for="apt in aptList"
          :key="apt.aptCode"
        >
          {{ apt.aptName }}
        </b-list-group-item>
      </b-list-group>
    </b-row>
  </b-card>
</template>

<script>
export default {
  data() {
    return {
      aptList: [
        {
          aptName: "포레스트힐 시티",
          aptCode: 1,
        },
        {
          aptName: "대성스카이렉스",
          aptCode: 2,
        },
        {
          aptName: "삼성",
          aptCode: 3,
        },
        {
          aptName: "에지앙빌",
          aptCode: 4,
        },
      ],

      sido: "대구광역시",
      gugun: "달서구",
      dong: "상인동",
    };
  },
  methods: {
    aptDetail(code) {
      this.$router.push({ name: "aptDetail", params: code });
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
</style>

<template>
  <b-container>
    <h1>공지사항</h1>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "BoardView",
};
</script>

<style>
h1 {
  text-align: center;
  margin-top: 60px;
}
</style>

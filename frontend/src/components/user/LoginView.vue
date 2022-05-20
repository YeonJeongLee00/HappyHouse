<template>
  <div class="mb-5">
    <h3 class="green-color">로그인</h3>
    <b-form-group
      label="아이디"
      label-for="id"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input id="id" placeholder="아이디를 입력해주세요."></b-form-input>
    </b-form-group>
    <b-form-group
      label="비밀번호"
      label-for="password"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        id="password"
        placeholder="비밀번호를 입력해주세요."
      ></b-form-input>
    </b-form-group>

    <div class="button-setting">
      <b-button id="login-btn" type="submit" class="mr-5 ml-5 mb-3"
        >로그인</b-button
      >
      <b-button
        id="join-btn"
        type="submit"
        class="mr-5 ml-5 mb-3"
        @click="moveJoin"
        >회원가입</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    login() {
      console.log("로그인 처리");
    },
    moveJoin() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scoped>
.button-setting {
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin-top: 2rem;
}

#login-btn,
#join-btn {
  background-color: white;
  color: #0c3b2e;
  border: 1px solid #0c3b2e;
}

#login-btn:hover {
  background-color: #0c3b2e;
  color: white;
  border: 1px solid #0c3b2e;
}

#join-btn:hover {
  background-color: #6d9773;
  color: white;
  border: 1px solid #6d9773;
}
</style>

import jwt_decode from "jwt-decode";
import { login, findById } from "@/api/user.js";

const userStore = {
  namespaced: true,
  state: {
    //   로그인 여부
    isLogin: false,
    // 로그인 에러
    isLoginError: false,
    // user 정보 저장
    userInfo: null,
  },
  getters: {
    // userInfo 접근
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  //   동기
  mutations: {
    // 로그인 상태 저장
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    // 로그인 후 유저 정보 저장
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  //   비동기
  actions: {
    // { commit } state.commit 의미
    async userConfirm({ commit }, user) {
      console.log("userStore - userConfirm");
      console.log(user);
      await login(
        // json형태의 user 전송
        user,
        (response) => {
          console.log(response.data.message);
          if (response.data.message === "success") {
            // 성공
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            // 성공하지 않았으면 Error를 true로 변경해준다.
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        (error) => {
          console.log("실패" + error);
        }
      );
    },
    // login 후
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.id,
        (response) => {
          if (response.data.message === "success") {
            console.log(response.data);
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;

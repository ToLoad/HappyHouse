import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById, signup } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isSignUp: false,
    isSignUpError: false,
    myPageList: {},
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    myPageList(state) {
      return state.myPageList;
    },
    isLogin(state) {
      return state.isLogin;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      if (state.isLogin && !isLogin) state.myPageList.userid = "";
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_SIGNUP: (state, isSignUp) => {
      state.isSignUp = isSignUp;
    },
    SET_IS_SIGNUP_ERROR: (state, isSignUpError) => {
      state.isSignUpError = isSignUpError;
    },
    SET_LIST_INFO: (state, payload) => {
      state.isList = true;
      state.myPageList = payload;
    },
    SET_MYPAGE_LIST(state, payload) {
      state.myPageList = payload;
      console.log("왔나요?");
      console.log(state.myPageList);
    },
  },
  actions: {
    //--------------------로그인
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    //----------------회원가입
    async userSignUp({ commit }, user) {
      await signup(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_SIGNUP", true);
            commit("SET_IS_SIGNUP_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_SIGNUP", false);
            commit("SET_IS_SIGNUP_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    //-------------- 로그인 아이디 추출
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_MYPAGE_LIST", response.data.userInfo); //로그인 아이디 추출시 myPageList값 변경
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
    //------------
  },
};

export default memberStore;

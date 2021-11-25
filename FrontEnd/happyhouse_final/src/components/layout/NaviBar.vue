<template>
  <div id="temp">
    <b-navbar toggleable="lg" fixed="top">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/happyhouse_logo.png"
            class="d-inline-block align-middle"
            width="60px"
            alt="logo"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link
              :to="{ name: 'Home' }"
              class="link"
              style="
                font-family: 'SDSamliphopangche_Outline';
                font-size: 17pt;
                color: black;
              "
              >HOME</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link
              :to="{ name: 'NoticeList' }"
              class="link"
              style="
                font-family: 'SDSamliphopangche_Outline';
                font-size: 17pt;
                color: black;
              "
            >
              공지사항</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link
              :to="{ name: 'QuestionList' }"
              class="link"
              style="
                font-family: 'SDSamliphopangche_Outline';
                font-size: 17pt;
                color: black;
              "
            >
              Q&A</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link
              :to="{ name: 'House' }"
              class="link"
              style="
                font-family: 'SDSamliphopangche_Outline';
                font-size: 17pt;
                color: black;
              "
            >
              아파트정보</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item
            class="align-self-center"
            style="font-family: 'twayair'; font-size: 13pt; color: black"
            ><b-avatar
              variant="info"
              v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
            ></b-avatar
            >&nbsp; {{ userInfo.username }}({{ userInfo.userid }})님
            환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'MyPage' }"
              class="link align-self-center"
              style="font-family: 'twayair'; font-size: 13pt; color: cadetblue"
              >내정보보기</router-link
            ></b-nav-item
          >
          <b-nav-item
            class="link align-self-center"
            style="font-family: 'twayair'; font-size: 13pt; color: gray"
            @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link
                :to="{ name: 'SignUp' }"
                class="link"
                style="font-family: 'twayair'; color: black"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link
                :to="{ name: 'SignIn' }"
                class="link"
                style="font-family: 'twayair'; color: black"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations, mapGetters } from "vuex";
import "@/assets/css/font.css";
const memberStore = "memberStore";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(mapGetters, ["myPageList"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      console.log("로그아웃");
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") {
        this.$router.push({ name: "Home" });
        this.$router.push({ name: "Home" });
      }
    },
  },
};
</script>

<style>
.navbar {
  background-color: #ffefd5;
  border-bottom: 2px solid black;
}
</style>

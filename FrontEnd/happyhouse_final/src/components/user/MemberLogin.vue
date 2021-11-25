<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <b-jumbotron style="background: white">
      <template #header>
        <div style="font-family: 'twayair'">
          로그인
          <img
            src="https://img.icons8.com/external-bearicons-flat-bearicons/128/000000/external-Welcome-miscellany-texts-and-badges-bearicons-flat-bearicons.png"
          />
        </div>
      </template>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card
            class="text-center mt-3"
            style="max-width: 40rem; background-color: snow"
            align="left"
          >
            <b-form class="text-left">
              <b-alert show variant="danger" v-if="isLoginError"
                >아이디 또는 비밀번호를 확인하세요.</b-alert
              >
              <b-input-group label-for="userid">
                <b-input-group-prepend is-text>
                  <b-icon icon="person-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  required
                  placeholder="아이디 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-input-group>
              <br />
              <b-input-group label-for="password">
                <b-input-group-prepend is-text>
                  <b-icon icon="key-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  type="password"
                  id="password"
                  v-model="user.password"
                  required
                  placeholder="비밀번호 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-input-group>
              <br />
              <div class="text-center">
                <b-button
                  type="button"
                  variant="primary"
                  class="m-1"
                  @click="confirm"
                  style="font-family: 'twayair'"
                  >로그인</b-button
                >
                <b-button
                  type="button"
                  variant="success"
                  class="m-1"
                  style="font-family: 'twayair'"
                  @click="movePage"
                  >회원가입</b-button
                >
              </div>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-jumbotron>
    <img
      src="@/assets/로그인.png"
      alt="로그인 시 추천매물 볼 수 있습니다"
      style="width: 950px"
    />
    <br /><br />
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
    movePage() {
      this.$router.push({ name: "SignUp" });
    },
  },
};
</script>

<style></style>

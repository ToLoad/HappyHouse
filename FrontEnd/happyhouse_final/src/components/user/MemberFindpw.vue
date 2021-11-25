<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <b-jumbotron style="background: white">
      <template #header>
        <div style="font-family: 'twayair'">
          비밀번호 찾기
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
              <!-- <b-alert show variant="danger" v-if="isLoginError"
                >아이디 또는 비밀번호를 확인하세요.</b-alert
              > -->
              <b-input-group label-for="username">
                <b-input-group-prepend is-text>
                  <b-icon icon="person-circle"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  id="username"
                  v-model="user.username"
                  required
                  ref="username"
                  placeholder="이름 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-input-group>
              <br />
              <b-input-group label-for="userid">
                <b-input-group-prepend is-text>
                  <b-icon icon="person-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  required
                  ref="userid"
                  placeholder="아이디 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-input-group>
              <br />
              <b-input-group label-for="email">
                <b-input-group-prepend is-text>
                  <b-icon icon="envelope"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  id="email"
                  v-model="user.email"
                  ref="email"
                  required
                  placeholder="이메일 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-input-group>
              <br />
              <div class="text-center">
                <b-button
                  type="button"
                  variant="primary"
                  class="m-1"
                  @click="find"
                  style="font-family: 'twayair'"
                  >찾기</b-button
                >
                <b-button
                  type="button"
                  variant="danger"
                  class="m-1"
                  @click="movePage"
                  style="font-family: 'twayair'"
                  >취소</b-button
                >
              </div>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-jumbotron>
  </b-container>
</template>

<script>
import { findPw } from "../../api/member";

export default {
  name: "MemberFindpw",
  data() {
    return {
      user: {
        username: null,
        userid: null,
        password: null,
      },
    };
  },
  computed: {},
  methods: {
    find(event) {
      event.preventDefault();
      let err = true;
      let msg = "에러";
      !this.user.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.user.userid &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      if (!err) alert(msg);
      else
        findPw(
          this.user,
          (response) => {
            alert("비밀번호는 : " + response.data.password + " 입니다.");
            this.movePage();
          },
          (error) => {
            console.log(error);
          }
        );
    },
    movePage() {
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>

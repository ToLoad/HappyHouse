<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                ref="username"
                placeholder="이름을 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <br />
            <b-form-group label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                ref="userid"
                placeholder="아이디 입력을 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <br />
            <b-form-group label-for="userpwd">
              <b-form-input
                type="password"
                id="password"
                ref="password"
                v-model="user.password"
                required
                placeholder="비밀번호를 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <br />
            <b-form-group label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                ref="email"
                placeholder="이메일을 입력입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <br />
            <b-form-group label-for="phone_num">
              <b-form-input
                id="phone_num"
                v-model="user.phone_num"
                required
                ref="phone_num"
                placeholder="전화번호를 입력입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <br />
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="signupBtn"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { signup } from "@/api/member";
export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        username: "",
        userid: "",
        password: "",
        email: "",
        phone_num: "",
        join_date: "",
      },
    };
  },
  methods: {
    signupBtn(event) {
      event.preventDefault();
      console.log("1111");
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
        !this.user.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.password.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      err &&
        !this.user.phone_num &&
        ((msg = "전화번호를 입력해주세요"),
        (err = false),
        this.$refs.phone_num.focus());
      if (!err) alert(msg);
      else this.JoinMember();
    },
    JoinMember() {
      signup(
        {
          username: this.user.username,
          userid: this.user.userid,
          password: this.user.password,
          email: this.user.email,
          phone_num: this.user.phone_num,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
      console.log(this.phone_num);
    },
    moveList() {
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>

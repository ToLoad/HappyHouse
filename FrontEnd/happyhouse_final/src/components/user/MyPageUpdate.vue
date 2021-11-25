<template>
  <div>
    <b-jumbotron style="background: oldlace; font-family: 'twayair'">
      <template #header>
        <img
          src="https://img.icons8.com/color-glass/80/000000/spa-flower.png"
        />
        My Page 수정
        <img
          src="https://img.icons8.com/color-glass/80/000000/spa-flower.png"
        />
      </template>

      <template #lead> 개인 정보 수정 페이지입니다. </template>

      <hr class="my-4" />
      <table class="table table-bordered">
        <tr>
          <td style="background: moccasin">아이디</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userid"
              @click="confirm"
              v-model="user.userid"
              readonly
            />
          </td>
        </tr>
        <tr>
          <td style="background: moccasin">이름</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="username"
              name="username"
              @click="confirm"
              v-model="user.username"
            />
          </td>
        </tr>
        <tr>
          <td style="background: moccasin">비밀번호</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="password"
              name="password"
              @click="confirm"
              v-model="user.password"
            />
          </td>
        </tr>
        <tr>
          <td style="background: moccasin">이메일</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="email"
              name="email"
              @click="confirm"
              v-model="user.email"
            />
          </td>
        </tr>
        <tr>
          <td style="background: moccasin">전화번호</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="phone_num"
              name="phone_num"
              @click="confirm"
              v-model="user.phone_num"
            />
          </td>
        </tr>
      </table>
      <b-button @click="onSubmit" pill variant="primary" href="#"
        >수정하기</b-button
      >&nbsp;
      <router-link :to="{ name: 'MyPage' }" class="link">
        <b-button variant="danger" pill>취소</b-button> </router-link
      ><br /><br />
      <router-link :to="{ name: 'Home' }">홈으로</router-link>
    </b-jumbotron>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import { update } from "@/api/member";

const memberStore = "memberStore";
export default {
  name: "MyPageUpdate",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        password: "",
        email: "",
        phone_num: "",
      },
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  created() {
    this.user.userid = this.myPageList.userid;
    this.user.username = this.myPageList.username;
    this.user.password = this.myPageList.password;
    this.user.email = this.myPageList.email;
    this.user.phone_num = this.myPageList.phone_num;
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log(this.myPageList);
      }
    },
    onSubmit() {
      update(
        {
          userid: this.user.userid,
          username: this.user.username,
          password: this.user.password,
          email: this.user.email,
          phone_num: this.user.phone_num,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.confirm();
          this.$router.push({ name: "Home" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>

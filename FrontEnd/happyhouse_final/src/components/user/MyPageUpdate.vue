<template>
  <div>
    <b-jumbotron>
      <template #header>My Page 수정</template>

      <template #lead> 개인 정보 수정 페이지입니다. </template>

      <hr class="my-4" />
      <table class="table table-bordered">
        <tr>
          <td style="background: powderblue">아이디</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userid"
              v-model="userid"
              readonly
            />
          </td>
        </tr>
        <tr>
          <td style="background: powderblue">이름</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="username"
              name="username"
              v-model="username"
            />
          </td>
        </tr>
        <tr>
          <td style="background: powderblue">비밀번호</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="password"
              name="password"
              v-model="password"
            />
          </td>
        </tr>
        <tr>
          <td style="background: powderblue">이메일</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="email"
              name="email"
              v-model="email"
            />
          </td>
        </tr>
        <tr>
          <td style="background: powderblue">전화번호</td>
          <td>
            <input
              type="text"
              class="form-control"
              id="phone_num"
              name="phone_num"
              v-model="phone_num"
            />
          </td>
        </tr>
      </table>
      <b-button @click="onSubmit" variant="primary" href="#">수정하기</b-button>
      <b-button variant="success" href="#">찜목록</b-button>
    </b-jumbotron>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { update } from "@/api/member";

const memberStore = "memberStore";
export default {
  name: "MyPageUpdate",
  data() {
    return {
      userid: "",
      username: "",
      password: "",
      email: "",
      phone_num: "",
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
  created() {
    this.userid = this.myPageList.userid;
    this.username = this.myPageList.username;
    this.password = this.myPageList.password;
    this.email = this.myPageList.email;
    this.phone_num = this.myPageList.phone_num;
  },
  methods: {
    onSubmit() {
      update(
        {
          username: this.username,
          password: this.password,
          email: this.email,
          phone_num: this.phone_num,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          console.log(this.username);
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
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

<template>
  <div>
    <b-jumbotron>
      <template #header>My Page</template>

      <template #lead> 개인 정보 수정 및 확인 가능합니다. </template>

      <hr class="my-4" />
      <table class="table table-bordered">
        <tr>
          <td style="background: powderblue">아이디</td>
          <td v-text="myPageList.userid"></td>
        </tr>
        <tr>
          <td style="background: powderblue">이름</td>
          <td v-text="myPageList.username"></td>
        </tr>
        <tr>
          <td style="background: powderblue">비밀번호</td>
          <td v-text="myPageList.password"></td>
        </tr>
        <tr>
          <td style="background: powderblue">이메일</td>
          <td v-text="myPageList.email"></td>
        </tr>
        <tr>
          <td style="background: powderblue">전화번호</td>
          <td v-text="myPageList.phone_num"></td>
        </tr>
      </table>
      <router-link :to="{ name: 'MyPageUpdate' }" class="link">
        <b-button variant="primary">수정하기</b-button>
      </router-link>
      <b-button variant="success" href="#">찜목록</b-button>
      <br /><br />
      <router-link :to="{ name: 'Home' }">홈으로</router-link>
    </b-jumbotron>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MyPage",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
};
</script>

<style></style>

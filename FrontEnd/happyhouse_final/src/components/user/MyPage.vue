<template>
  <div>
    <b-jumbotron style="background: oldlace; font-family: 'twayair'">
      <template #header>
        <img src="https://img.icons8.com/color-glass/80/000000/flower.png" />
        마이 페이지
        <img src="https://img.icons8.com/color-glass/80/000000/flower.png" />
      </template>

      <template #lead> 개인 정보 수정 및 확인 가능합니다. </template>

      <hr class="my-4" />
      <table class="table table-bordered">
        <tr>
          <td style="background: moccasin">아이디</td>
          <td v-text="myPageList.userid" style="background: white"></td>
        </tr>
        <tr>
          <td style="background: moccasin">이름</td>
          <td v-text="myPageList.username" style="background: white"></td>
        </tr>
        <tr>
          <td style="background: moccasin">비밀번호</td>
          <td v-text="myPageList.password" style="background: white"></td>
        </tr>
        <tr>
          <td style="background: moccasin">이메일</td>
          <td v-text="myPageList.email" style="background: white"></td>
        </tr>
        <tr>
          <td style="background: moccasin">전화번호</td>
          <td v-text="myPageList.phone_num" style="background: white"></td>
        </tr>
      </table>
      <router-link :to="{ name: 'MyPageUpdate' }" class="link">
        <b-button pill variant="success">수정하기</b-button> </router-link
      >&nbsp;
      <!-- <b-button pill variant="outline-danger">찜하기</b-button> -->
      <router-link
        :to="{ name: 'MyWishList', params: { userid: myPageList.userid } }"
        class="link"
      >
        <b-button variant="danger" pill>찜목록</b-button>
      </router-link>
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

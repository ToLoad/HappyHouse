<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="myPageList.userid === 'admin'">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<div style='color: #42b983'><h2>${article.num}. ${article.title}</h2>
          </div>
          <div style='float: right'><h6>[ ${article.writer_id} ] ${article.write_time}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left" style="background-color: lightgray">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/notice";
import { mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.num,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "NoticeUpdate",
        params: { num: this.article.num },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      if (confirm("삭제하시겠습니까?")) {
        deleteArticle(this.article.num, () => {
          this.$router.push({ name: "NoticeList" });
        });
      }
    },
  },
};
</script>

<style></style>

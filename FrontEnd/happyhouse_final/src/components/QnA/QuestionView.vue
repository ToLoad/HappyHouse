<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listQuestion"
          >목록</b-button
        >
      </b-col>
      <b-col
        class="text-right"
        v-if="
          myPageList.userid === questions.writer_id ||
          myPageList.userid === 'admin'
        "
      >
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQuestion"
          class="mr-2"
          >질문수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeQuestion"
          >질문삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<div style='color: dodgerblue'><h2>${questions.num}. ${questions.title}</h2>
          </div>
          <div style='float: right'><h6>[ ${questions.writer_id} ] ${questions.write_time}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <br />
    <br />
    <b-row class="mb-1">
      <b-col>
        <table class="table table-bordered">
          <tr>
            <td>
              {{ answers.writer_id }}
            </td>
            <td colspan="2">
              {{ answers.content }}
            </td>
          </tr>
          <tr>
            <td>
              <input
                type="text"
                v-model="writer_id"
                id="write_id"
                name="writer_id"
                placeholder="아이디입력"
              />
            </td>
            <td>
              <input
                type="text"
                v-model="content"
                id="content"
                name="content"
                placeholder="내용입력"
              />
            </td>
            <td>
              <button>답변등록</button>
            </td>
          </tr>
        </table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getQuestion, deleteQuestion, getAnswer } from "@/api/qna";
import { mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  data() {
    return {
      questions: {},
      answers: {},
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
    message() {
      if (this.questions.content)
        return this.questions.content.split("\n").join("<br>");
      return "";
    },
    answermessage() {
      if (this.answers.content)
        return this.answers.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQuestion(
      this.$route.params.num,
      (response) => {
        this.questions = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
    getAnswer(
      this.$route.params.num,
      (response) => {
        this.answers = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listQuestion() {
      this.$router.push({ name: "QuestionList" });
    },
    moveModifyQuestion() {
      this.$router.replace({
        name: "QuestionUpdate",
        params: { num: this.questions.num },
      });
    },
    removeQuestion() {
      if (confirm("삭제하시겠습니까?")) {
        deleteQuestion(this.questions.num, () => {
          this.$router.push({ name: "QuestionList" });
        });
      }
    },
  },
};
</script>

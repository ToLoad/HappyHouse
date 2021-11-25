<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button
          variant="outline-primary"
          @click="listQuestion"
          style="font-family: 'twayair'"
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
      <b-col style="font-family: 'yg-jalnan'">
        <b-card
          :header-html="`<div style='color: dodgerblue'><h2>${questions.num}. ${questions.title}</h2>
          </div>
          <div style='float: right'><h6>[ ${questions.writer_id} ]님 ${questions.write_time}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-center">
            <div
              v-html="message"
              style="background-color: snow; font-size: 14pt"
            ></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <br />
    <br />
    <!-- 답변 -->
    <b-row class="mb-1">
      <b-col>
        <div class="accordion" role="tablist">
          <b-card no-body class="mb-1">
            <b-card-header header-tag="header" class="p-1" role="tab">
              <b-button
                block
                v-b-toggle.accordion-1
                variant="info"
                style="font-family: 'twayair'; font-size: 15pt"
                >💛 답변 💛</b-button
              >
            </b-card-header>
            <b-collapse
              id="accordion-1"
              visible
              accordion="my-accordion"
              style="background-color: snow"
              role="tabpanel"
            >
              <b-card-body>
                <b-card-text
                  ><table
                    class="table table-bordered"
                    style="font-family: 'twayair'; font-size: 13pt"
                  >
                    <tr v-for="(answer, index) in answers" :key="index">
                      <td style="background-color: mintcream">
                        {{ answer.writer_id }} 님
                      </td>
                      <td
                        v-text="answer.content"
                        style="
                          background-color: white;
                          font-family: 'IM_Hyemin-Bold';
                        "
                      ></td>
                      <td
                        v-if="
                          myPageList.userid === answer.writer_id ||
                          myPageList.userid === 'admin'
                        "
                      >
                        <div
                          type="button"
                          @click="removeAnswer(answer.num)"
                          style="font-size: small; color: tomato"
                        >
                          삭제
                        </div>
                      </td>
                    </tr>
                  </table></b-card-text
                >
              </b-card-body>
            </b-collapse>
          </b-card>
        </div>
        <answer-write style="font-family: 'twayair'" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {
  getQuestion,
  deleteQuestion,
  listAnswer,
  deleteAnswer,
} from "@/api/qna";
import { mapGetters } from "vuex";
const memberStore = "memberStore";
import AnswerWrite from "./answer/AnswerWrite.vue";

export default {
  components: {
    AnswerWrite,
  },
  data() {
    return {
      questions: {},
      answers: [],
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
    message() {
      if (this.questions.content)
        return this.questions.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQuestion(
      this.$route.params.num,
      (response) => {
        this.questions = response.data;
        console.log(this.questions);
      },
      (error) => {
        console.log("getQuestion 에러발생!!", error);
      }
    );
    listAnswer(
      this.$route.params.num,
      (response) => {
        this.answers = response.data;
        console.log(this.answers);
      },
      (error) => {
        console.log("listAnswer 에러발생!!", error);
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
    removeAnswer(num) {
      if (confirm("삭제하시겠습니까?")) {
        deleteAnswer(num, () => {
          this.$router.go(0); //새로고침 reload
        });
      }
    },
  },
};
</script>

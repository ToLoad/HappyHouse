<template>
  <b-container class="bv-example-row mt-3" style="font-family: 'twayair'">
    <br /><br /><br />
    <img src="@/assets/qna.png" alt="qna" />
    <b-row class="mb-1">
      <b-col class="text-right">
        <br />
        <b-button variant="outline-primary" @click="moveWrite()"
          >질문등록</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="questions.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark" style="font-size: 14.5pt">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>내용</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <question-list-row
              style="font-size: 13pt"
              v-for="(question, index) in questions"
              :key="index"
              v-bind="question"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import QuestionListRow from "@/components/QnA/QuestionListRow";
import { listQuestion } from "@/api/qna.js";

export default {
  name: "QuestionList",
  components: {
    QuestionListRow,
  },
  data() {
    return {
      questions: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listQuestion(
      param,
      (response) => {
        console.log(response);
        console.log("response");
        this.questions = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QuestionWrite" });
    },
  },
};
</script>

<style></style>

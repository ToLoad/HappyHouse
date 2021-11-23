<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="writer_id"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="writer_id"
            :disabled="isUserid"
            v-model="question.writer_id"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="질문:"
          label-for="title"
          description="질문을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="question.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="question.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>
<script>
import { writeQuestion, getQuestion, modifyQuestion } from "@/api/qna";

export default {
  name: "QuestionWriteForm",
  data() {
    return {
      question: {
        num: 0,
        question_num: 0,
        writer_id: "",
        title: "",
        content: "",
        write_time: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      getQuestion(
        this.$route.params.num,
        ({ data }) => {
          this.question = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.question.writer_id &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.writer_id.focus());
      err &&
        !this.question.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());

      if (!err) alert(msg);
      else
        this.type === "register"
          ? this.registQuestion()
          : this.updateQuestion();
    },
    onReset(event) {
      event.preventDefault();
      this.question.num = 0;
      this.question.title = "";
      this.question.content = "";
      this.$router.push({ name: "QuestionList" });
    },
    registQuestion() {
      writeQuestion(
        {
          writer_id: this.question.writer_id,
          title: this.question.title,
          content: this.question.content,
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
    },
    updateQuestion() {
      modifyQuestion(
        {
          num: this.question.num,
          writer_id: this.question.writer_id,
          title: this.question.title,
          content: this.question.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "QuestionList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "QuestionList" });
    },
  },
};
</script>

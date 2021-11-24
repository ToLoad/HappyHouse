<template>
  <div class="text-center">
    <br />
    <input
      type="text"
      v-model="answers.content"
      id="content"
      name="content"
      ref="content"
      style="width: 800px; height: 60px"
    />&nbsp;
    <b-button pill @click="registAnswer">답변등록</b-button>
  </div>
</template>

<script>
import { writeAnswer } from "@/api/qna";
import { mapGetters } from "vuex";
const memberStore = "memberStore";
//  질문번호 갖고오기
export default {
  name: "AnwerWrite",
  data() {
    return {
      answers: {
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
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
  created() {
    this.isUserid = true;
  },
  methods: {
    registAnswer() {
      let err = true;
      let msg = "";
      !this.answers.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else {
        writeAnswer(
          {
            writer_id: this.myPageList.userid,
            title: this.myPageList.userid,
            content: this.answers.content,
            question_num: `${this.$route.params.num}`,
          },
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            console.log(this.num);
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
      }
    },
    moveList() {
      this.$router.go(0);
    },
  },
};
</script>

<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>질문수정</h3></b-alert>
      </b-col>
    </b-row>
    <question-write-form type="modify" />
  </b-container>
</template>

<script>
import QuestionWriteForm from "./QuestionWriteForm.vue";

export default {
  name: "QuestionUpdate",
  components: {
    QuestionWriteForm,
  },
};
</script>

<style></style>

<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <question-write-form type="register" />
  </b-container>
</template>

<script>
import QuestionWriteForm from "./QuestionWriteForm.vue";

export default {
  name: "QuestionWrite",
  components: {
    QuestionWriteForm,
  },
};
</script>

<style></style>

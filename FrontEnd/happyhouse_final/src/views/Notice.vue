<template>
  <b-container class="bv-example-row mt-3 text-center"
    ><br /><br /><br />
    <img src="@/assets/notice_logo.png" alt="위시리스트" />
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "Notice",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>

<template>
  <div id="app">
    <navi-bar />
    <router-view />
    <div class="mb-5"></div>
    <Footer />
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";
import Footer from "./components/layout/Footer.vue";
export default {
  name: "App",
  components: {
    NaviBar,
    Footer,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>

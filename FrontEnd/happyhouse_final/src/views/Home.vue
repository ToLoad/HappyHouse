<template>
  <div>
    <carousel />
    <br />
    <router-link :to="{ name: 'NoticeList' }">
      <img
        src="https://img.icons8.com/dusk/64/000000/summer.png"
      /> </router-link
    >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link :to="{ name: 'QuestionList' }">
      <img
        src="https://img.icons8.com/dusk/64/000000/star--v2.png"
      /> </router-link
    >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link :to="{ name: 'House' }">
      <img src="https://img.icons8.com/dusk/64/000000/bright-moon.png" />
    </router-link>
    <house-recommend />
  </div>
</template>

<script>
// @ is an alias to /src
import Carousel from "@/components/layout/Carousel.vue";
import HouseRecommend from "../components/house/HouseRecommend.vue";

export default {
  name: "Home",
  components: {
    Carousel,
    HouseRecommend,
  },
};
</script>

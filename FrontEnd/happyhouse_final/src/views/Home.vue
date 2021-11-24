<template>
  <div>
    <carousel />
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

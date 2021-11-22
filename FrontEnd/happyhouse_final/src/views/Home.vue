<template>
  <div>
    <carousel />
  </div>
</template>

<script>
// @ is an alias to /src
import Carousel from "@/components/layout/Carousel.vue";

export default {
  name: "Home",
  components: {
    Carousel,
  },
};
</script>

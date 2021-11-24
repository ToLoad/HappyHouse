<template>
  <div>
    <br /><br /><br />
    <img src="@/assets/house_service.png" alt="해피하우스" />
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <house-search-bar></house-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="5" align="left">
          <house-list />
        </b-col>
        <b-col cols="7">
          <house-map />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseMap from "@/components/house/HouseMap.vue";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    HouseMap,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>

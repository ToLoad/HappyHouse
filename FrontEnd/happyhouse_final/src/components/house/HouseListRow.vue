<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        :src="require(`@/assets/aptimg/${house.img}`)"
        :alt="`${house.img}`"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ house.no }}] {{ house.aptName }} {{ house.floor }}층
    </b-col>
    <b-modal
      :id="`modal-${house.no}`"
      :title="`${house.aptName} 상세정보`"
      size="lg"
    >
      <b-img
        center
        rounded
        width="500"
        height="300"
        :src="require(`@/assets/aptimg/${house.img}`)"
        :alt="`${house.img}`"
      ></b-img>
      <b-alert show variant="secondary" class="mt-3 text-center"
        >아파트 식별번호 : {{ house.no }}</b-alert
      >
      <b-alert show variant="warning" class="text-center"
        >주소 : {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
        {{ house.jibun }}</b-alert
      >
      <b-alert show variant="danger" class="mt-3 text-center"
        >건축연도 : {{ house.dealYear }}년 {{ house.dealMonth }}월
        {{ house.dealDay }}일</b-alert
      >
      <b-alert show variant="info" class="mt-3 text-center"
        >최근 거래가격 : {{ house.recentPrice }} 만원</b-alert
      >
      <b-alert show variant="info" class="mt-3 text-center"
        >크기 : {{ house.area }} m2</b-alert
      >
      <b-alert show variant="info" class="mt-3 text-center"
        >층수 : {{ house.floor }} 층</b-alert
      >
      <div class="text-center">
        <b-button pill variant="outline-danger">찜하기</b-button>
      </div>
    </b-modal>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      this.detailHouse(this.house);
      this.$bvModal.show("modal-" + this.house.no);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>

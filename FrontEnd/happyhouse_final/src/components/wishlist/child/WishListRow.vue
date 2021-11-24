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
        :src="require(`@/assets/aptimg/${list.img}`)"
        :alt="`${list.img}`"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ list.no }}] {{ list.aptName }} {{ list.floor }}층
    </b-col>
    <b-modal
      :id="`modal-${list.no}`"
      :title="`${list.aptName} 상세정보`"
      size="lg"
    >
      <b-img
        center
        rounded
        width="500"
        height="300"
        :src="require(`@/assets/aptimg/${list.img}`)"
        :alt="`${list.img}`"
      ></b-img>
      <b-alert show variant="secondary" class="mt-3 text-center"
        >아파트 식별번호 : {{ list.no }}</b-alert
      >
      <b-alert show variant="warning" class="text-center"
        >주소 : {{ list.sidoName }} {{ list.gugunName }} {{ list.dongName }}
        {{ list.jibun }}</b-alert
      >
      <b-alert show variant="danger" class="mt-3 text-center"
        >건축연도 : {{ list.dealYear }}년 {{ list.dealMonth }}월
        {{ list.dealDay }}일</b-alert
      >
      <b-alert show variant="info" class="mt-3 text-center"
        >최근 거래가격 : {{ list.recentPrice }} 만원</b-alert
      >
      <b-alert show variant="info" class="mt-3 text-center"
        >크기 : {{ list.area }} m2</b-alert
      >
      <b-alert show variant="info" class="mt-3 text-center"
        >층수 : {{ list.floor }} 층</b-alert
      >
      <div class="text-center">
        <b-button pill variant="outline-danger" @click="removeWish"
          >삭제</b-button
        >
      </div>
    </b-modal>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
// import { deleteWish } from "@/api/wishlist";
import { mapGetters } from "vuex";
const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "WishListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    list: Object,
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.list);
      this.detailHouse(this.list);
      this.$bvModal.show("modal-" + this.list.no);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    removeWish() {
      console.log("등록 먼저 하고 삭제");
      // if (confirm("정말로 삭제?")) {
      //   deleteWish(this.listdata, () => {
      //     this.$router.push({ name: "BoardList" });
      //   });
      // }
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: oldlace;
}
</style>

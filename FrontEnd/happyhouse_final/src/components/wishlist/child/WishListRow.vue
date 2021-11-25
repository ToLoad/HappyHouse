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
        class="mb-5"
        center
        rounded
        width="500"
        height="300"
        :src="require(`@/assets/aptimg/${house.img}`)"
        :alt="`${house.img}`"
      ></b-img>
      <b-card
        border-variant="dark"
        :header="`${house.aptName}`"
        header-bg-variant="info"
        header-text-variant="white"
        align="center"
      >
        <b-table-simple small stacked>
          <colgroup>
            <col />
            <col />
          </colgroup>
          <colgroup>
            <col />
            <col />
            <col />
          </colgroup>
          <colgroup>
            <col />
            <col />
          </colgroup>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>아파트 식별번호</b-th>
              <b-th>주소</b-th>
              <b-th>건축연도</b-th>
              <b-th>최근 거래가격</b-th>
              <b-th>크기</b-th>
              <b-th>층수</b-th>
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr>
              <b-th rowspan="3" class="text-center">상세 정보</b-th>
              <b-th stacked-heading="아파트 식별번호">{{ house.no }}</b-th>
              <b-td stacked-heading="주소" variant="success"
                >{{ house.sidoName }} {{ house.gugunName }}
                {{ house.dongName }} {{ house.jibun }}</b-td
              >
              <b-td stacked-heading="건축연도"
                >{{ house.dealYear }}년 {{ house.dealMonth }}월
                {{ house.dealDay }}일</b-td
              >
              <b-td stacked-heading="최근 거래가격"
                >{{ house.recentPrice }} 만원</b-td
              >
              <b-td stacked-heading="크기">{{ house.area }} m2</b-td>
              <b-td stacked-heading="층수">{{ house.floor }} 층</b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-card>
      <div class="text-center mt-2">
        <b-button pill variant="outline-danger" @click="removeWish(house.no)"
          >삭제</b-button
        >
      </div>
    </b-modal>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
import { mapState } from "vuex";
import { deleteWish } from "@/api/wishlist.js";
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
    house: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
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
    removeWish(num) {
      deleteWish(
        { userid: this.userInfo.userid, apt_no: num },
        () => {
          console.log("삭제완료");
          this.$router.go(0);
        },
        (error) => {
          console.log(error);
        }
      );
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

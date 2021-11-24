<template>
  <div>
    <b-container
      v-if="mylist && mylist.length != 0"
      class="bv-example-row mt-3"
    >
      <wish-list-row
        v-for="(list, index) in mylist"
        :key="index"
        :list="list"
      />
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>찜 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { getWish } from "@/api/wishlist";
import WishListRow from "@/components/wishlist/child/WishListRow";

import { mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MyWishList",
  components: {
    WishListRow,
  },
  data() {
    return {
      mylist: [],
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
  created() {
    getWish(
      this.myPageList.userid,
      (response) => {
        this.mylist = response.data;
        console.log(this.mylist);
      },
      (error) => {
        console.log("getQuestion 에러발생!!", error);
      }
    );
  },
};
</script>

<style></style>

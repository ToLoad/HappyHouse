<template>
  <div>
    <br />
    <hr />
    <div v-if="myPageList.userid === undefined || myPageList.userid === ''">
      <img
        src="@/assets/로그인.png"
        alt="로그인 시 추천매물 볼 수 있습니다"
        style="width: 950px"
      />
    </div>
    <div
      class="container"
      v-else
      style="font-family: 'twayair'; font-size: 17px"
    >
      <b-row class="mb-1">
        <b-col>
          <div class="accordion" role="tablist">
            <b-card no-body class="mb-1">
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button
                  block
                  v-b-toggle.accordion-1
                  variant="info"
                  style="font-family: 'yg-jalnan'"
                  >{{ myPageList.username }} 님의 추천매물!!!</b-button
                >
              </b-card-header>
              <b-collapse
                id="accordion-1"
                visible
                accordion="my-accordion"
                role="tabpanel"
              >
                <b-card-body>
                  <b-card-text>
                    <house-recommend-row
                      v-for="(house, index) in houses"
                      :key="index"
                      :house="house"
                    />
                  </b-card-text>
                </b-card-body>
              </b-collapse>
            </b-card>
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { getHouseRecommend } from "@/api/house";
import { mapGetters } from "vuex";
const memberStore = "memberStore";
import HouseRecommendRow from "./HouseRecommendRow.vue";
export default {
  components: {
    HouseRecommendRow,
  },
  data() {
    return {
      houses: [],
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
  created() {
    this.getRecommend();
  },
  watch: {
    myPageList: function () {
      this.getRecommend();
    },
  },
  methods: {
    getRecommend() {
      console.log(this.myPageList.userid);
      getHouseRecommend(
        this.myPageList.userid,
        (response) => {
          this.houses = response.data;
        },
        (error) => {
          console.log("추천 아파트리스트 에러발생!", error);
        }
      );
    },
  },
};
</script>

<style></style>

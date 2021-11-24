<template>
  <div>
    <br />
    <hr />
    <div class="container" v-if="myPageList.userid !== ''">
      <b-row class="mb-1">
        <b-col>
          <div class="accordion" role="tablist">
            <b-card no-body class="mb-1">
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block v-b-toggle.accordion-1 variant="info"
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
    <div v-else>로그인 하쇼</div>
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
    getHouseRecommend(
      this.myPageList.userid,
      (response) => {
        this.houses = response.data;
        console.log(this.houses);
      },
      (error) => {
        console.log("추천 아파트리스트 에러발생!", error);
      }
    );
  },
};
</script>

<style></style>

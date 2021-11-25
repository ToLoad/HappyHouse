<template>
  <b-container class="bv-example-row mt-3">
    <br /><br /><br />
    <img src="@/assets/notice_logo.png" alt="위시리스트" />
    <b-row class="mb-1">
      <b-col class="text-right" v-if="myPageList.userid === 'admin'">
        <br />
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead
            head-variant="dark"
            style="font-family: 'twayair'; font-size: 14.5pt"
          >
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>내용</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <notice-list-row
              style="font-family: 'twayair'; font-size: 13pt"
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import NoticeListRow from "@/components/notice/NoticeListRow";
import { listArticle } from "@/api/notice.js";
import { mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  name: "NoticeList",
  components: {
    NoticeListRow,
  },
  data() {
    return {
      articles: [],
    };
  },
  computed: {
    ...mapGetters(memberStore, ["myPageList"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        console.log(response);
        this.articles = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>

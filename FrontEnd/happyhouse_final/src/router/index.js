import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import Member from "@/views/Member.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MyPage from "@/components/user/MyPage.vue";
import MyPageUpdate from "@/components/user/MyPageUpdate.vue";

import House from "@/views/House.vue";

import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeView from "@/components/notice/NoticeView.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";

import QnA from "@/views/QnA.vue";
import QuestionList from "@/components/QnA/QuestionList.vue";
import QuestionWrite from "@/components/QnA/QuestionWrite.vue";
import QuestionView from "@/components/QnA/QuestionView.vue";
import QuestionUpdate from "@/components/QnA/QuestionUpdate.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};
const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    children: [
      {
        path: "signup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "singin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "mypage",
        name: "MyPage",
        component: MyPage,
      },
      {
        path: "mypageupdate",
        name: "MyPageUpdate",
        beforeEnter: onlyAuthUser,
        component: MyPageUpdate,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    beforeEnter: onlyAuthUser,
    component: House,
  },
  {
    path: "/notice",
    name: "Notice",
    beforeEnter: onlyAuthUser,
    component: Notice,
    children: [
      {
        path: "noticelist",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "noticeview/:num",
        name: "NoticeView",
        component: NoticeView,
      },
      {
        path: "noticewrite",
        name: "NoticeWrite",
        component: NoticeWrite,
      },
      {
        path: "noticeupdate",
        name: "NoticeUpdate",
        component: NoticeUpdate,
      },
    ],
  },
  {
    path: "/qna",
    name: "QnA",
    beforeEnter: onlyAuthUser,
    component: QnA,
    children: [
      {
        path: "qnalist",
        name: "QuestionList",
        component: QuestionList,
      },
      {
        path: "qnawrite",
        name: "QuestionWrite",
        component: QuestionWrite,
      },
      {
        path: "qnaupdate",
        name: "QuestionUpdate",
        component: QuestionUpdate,
      },
      {
        path: "qnaview/:num",
        name: "QuestionView",
        component: QuestionView,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

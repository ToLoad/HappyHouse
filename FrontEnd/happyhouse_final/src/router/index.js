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

Vue.use(VueRouter);

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
        component: MyPageUpdate,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: House,
  },
  {
    path: "/notice",
    name: "Notice",
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

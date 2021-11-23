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
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

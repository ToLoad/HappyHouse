<template>
  <b-tr>
    <b-td>{{ num }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'NoticeView', params: { num: num } }"
        style="color: #42b983"
        >{{ title }}</router-link
      >
    </b-th>
    <b-td>{{ content }}</b-td>
    <b-td>{{ writer_id }}</b-td>
    <b-td>{{ write_time }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "NoticeListRow",
  props: {
    num: Number,
    title: String,
    content: String,
    writer_id: String,
    write_time: String,
  },
  computed: {},
};
</script>

<style></style>

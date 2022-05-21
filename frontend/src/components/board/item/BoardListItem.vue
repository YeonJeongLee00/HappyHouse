<template>
  <b-tr>
    <b-td class="td">{{ no }}</b-td>
    <b-td class="td">{{ tag_no }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardDetail', params: { no: no } }">{{
        title
      }}</router-link>
    </b-th>
    <b-td class="td">{{ user_id }}</b-td>
    <b-td class="td">{{ view }}</b-td>
    <b-td class="td">{{ registDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    no: Number,
    title: String,
    user_id: String,
    registDate: String,
    view: Number,
    tag_no: Number,
  },
  filters: {
    dateFormat(registDate) {
      return moment(new Date(registDate)).format("YY.MM.DD");
    },
  },
};
</script>

<style>
.td {
  text-align: center;
}
</style>

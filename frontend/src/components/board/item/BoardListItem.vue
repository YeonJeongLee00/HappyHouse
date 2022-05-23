<template>
  <b-tr>
    <b-td class="td">{{ board.no }}</b-td>
    <b-td class="td">{{ board.tag_name }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'boardDetail', params: { no: no } }"
        class="router"
        >{{ board.title }}</router-link
      >
    </b-th>
    <b-td class="td">{{ board.user_id }}</b-td>
    <b-td class="td">{{ board.view }}</b-td>
    <b-td class="td">{{ board.registDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
import { selectTag } from "@/api/board";

export default {
  name: "BoardListItem",
  data() {
    return {
      board: {
        no: this.no,
        title: this.title,
        user_id: this.user_id,
        registDate: this.registDate,
        view: this.view,
        tag_no: this.tag_no,
        tag_name: "",
      },
    };
  },
  props: {
    no: Number,
    title: String,
    user_id: String,
    registDate: String,
    view: Number,
    tag_no: String,
  },

  created() {
    selectTag(
      this.board.no,
      (response) => {
        this.board.tag_name = response.data;
      },
      (error) => {
        console.log("에러발생", error);
      }
    );
  },

  filters: {
    dateFormat(registDate) {
      return moment(new Date(registDate)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
.td {
  text-align: center;
}
.router {
  font-size: 17px;
  color: #6d9773;
}
</style>

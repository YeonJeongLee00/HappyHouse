<template>
  <b-container class="container bv-example-row mt-3">
    <b-row>
      <b-table-simple hover responsive>
        <b-thead>
          <b-tr class="title" align="center">
            <b-th class="col-md-1 head">번호</b-th>
            <b-th class="col-md-1 head">태그</b-th>
            <b-th class="col-md-5 head">제목</b-th>
            <b-th class="col-md-2 head">작성자</b-th>
            <b-th class="col-md-1 head">조회수</b-th>
            <b-th class="col-md-1 head">날짜</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <board-list-item
            v-for="board in boards"
            :key="board.no"
            v-bind="board"
          />
        </tbody>
      </b-table-simple>
    </b-row>
    <b-row>
      <b-col class="col-md-2">
        <b-button class="button" @click="moveWrite()">글 쓰기</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listBoard } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem.vue";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      boards: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listBoard(
      param,
      (response) => {
        this.boards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    // 글쓰기 누르면 글 작성 component로 이동
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
};
</script>

<style>
.table {
  margin-top: 50px;
}
.title {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}

.button {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}
</style>

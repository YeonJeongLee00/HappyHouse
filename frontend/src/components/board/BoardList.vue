<template>
  <b-container class="container bv-example-row mt-3">
    <b-row>
      <b-form-select
        class="select"
        v-model="selected"
        :options="options"
        @change="onChange($event)"
      ></b-form-select>
      <b-button class="button ml-2" @click="moveWrite()">글 쓰기</b-button>

      <b-table-simple
        hover
        responsive
        id="my-table"
        :items="items"
        :current-page="currentPage"
        :per-page="perPage"
      >
        <b-thead>
          <b-tr class="title" align="center">
            <b-th class="col-md-1 head">번호</b-th>
            <b-th class="col-md-1 head">글 종류</b-th>
            <b-th class="col-md-5 head">제목</b-th>
            <b-th class="col-md-2 head">작성자</b-th>
            <b-th class="col-md-1 head">조회수</b-th>
            <b-th class="col-md-1 head">날짜</b-th>
          </b-tr>
        </b-thead>
        <!-- 모든 리스트 출력 start -->
        <tbody v-if="tagBoards.length == 0 && selected == null">
          <board-list-item
            v-for="board in boards"
            :key="board.no"
            v-bind="board"
          />
        </tbody>
        <!-- 모든 리스트 출력 end -->

        <!-- Tag 리스트 출력 -->
        <tbody v-else>
          <board-list-item
            v-for="board in tagBoards"
            :key="board.no"
            v-bind="board"
          />
        </tbody>
        <!-- Tag 리스트 출력 end -->
      </b-table-simple>
    </b-row>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>
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
      tagBoards: [],
      selected: null,
      options: [
        { value: null, text: "글 종류" },
        { value: 1, text: "공지사항" },
        { value: 2, text: "잡담" },
        { value: 3, text: "꿀팁" },
      ],
      currentPage: 1,
      perPage: 10,
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
  computed: {
    rows() {
      return this.boards.length;
    },
  },
  methods: {
    // 글쓰기 누르면 글 작성 component로 이동
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    // 원하는 글 종류만 출력
    onChange(event) {
      let list = this.boards.filter((board) => {
        if (board.tag_no.includes(event)) {
          return true;
        }
      });
      console.log(list);
      this.tagBoards = list;
    },
  },
};
</script>

<style scoped>
.select {
  width: 120px;
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
  color: white;
}
.table {
  margin-top: 20px;
}
.title {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
  color: white;
}

.button {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}
</style>

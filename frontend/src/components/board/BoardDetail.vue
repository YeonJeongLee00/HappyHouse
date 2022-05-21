<template>
  <b-container class="bv-example-row mt-3">
    <h3>글 보기</h3>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button class="button" @click="moveList()">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button class="mr-2 button" @click="moveModify()">글 수정</b-button>
        <b-button class="button" @click="deleteBoard()">글 삭제</b-button>
      </b-col>
    </b-row>
    <b-col class="mt-3 text-left" style="float: none; margin: 0 auto">
      <b-row class="mt-2">
        <b-form-group
          class="col-md-4"
          id="tag-group"
          label="태그"
          label-for="tag"
        >
          <b-form-input
            id="tag"
            type="text"
            readonly
            :value="`${board.tag_no}`"
          >
          </b-form-input>
        </b-form-group>
        <b-form-group
          class="col-md-4"
          id="author-group"
          label="작성자"
          label-for="author"
        >
          <b-form-input
            id="author"
            type="text"
            readonly
            :value="`${board.user_id}`"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          class="col-md-4"
          id="date-group"
          label="작성일"
          label-for="date"
        >
          <b-form-input
            id="date"
            type="text"
            readonly
            :value="`${board.registDate}` | dateFormat"
          ></b-form-input>
        </b-form-group>
      </b-row>
    </b-col>
    <b-row>
      <b-col class="mt-2" style="text-align: left">
        <b-form-group id="subject-group" label="제목" label-for="subject">
          <b-form-input
            id="subject"
            type="text"
            readonly
            :value="`${board.title}`"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="content-group" label="내용" label-for="context">
          <b-form-textarea
            id="content"
            readonly
            :value="`${board.content}`"
            rows="15"
            max-rows="20"
          ></b-form-textarea>
        </b-form-group>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { selectBoard, deleteBoard } from "@/api/board";
import moment from "moment";

export default {
  name: "BoardDetail",
  data() {
    return {
      // 가져온 데이터 저장할 객체
      board: {},
    };
  },
  // 생성되자마자 데이터가져오기
  created() {
    console.log(this.$route.params.no);
    selectBoard(
      this.$route.params.no,
      ({ data }) => {
        this.board = data;
      },
      (error) => {
        console.log("데이터 에러발생!!", error);
      }
    );
  },
  methods: {
    // 리스트 페이지
    moveList() {
      this.$router.push({ name: "boardList" });
    },
    // 수정 페이지
    moveModify() {
      this.$router.replace({
        name: "boardModify",
        params: { no: this.board.no },
      });
    },
    // 글 삭제하기
    deleteBoard() {
      deleteBoard(
        this.$route.params.no,
        this.$router.replace({ name: "boardList" }),
        (error) => {
          console.log("삭제시 에러발생!!", error);
        }
      );
    },
  },

  // 날짜 필터
  filters: {
    dateFormat(registDate) {
      return moment(new Date(registDate)).format("YY년 MM월 DD일 H시 M분");
    },
  },
};
</script>

<style>
h3 {
  text-align: center;
  margin-top: 30px;
  margin-bottom: 30px;
}

.border {
  border-block-color: "#6D9773";
}
.button {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}
</style>

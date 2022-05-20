<template>
  <b-container class="bv-example-row mt-3">
    <h3>글 보기</h3>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button class="button" @click="moveList()">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button class="mr-2 button" @click="moveModify()">글 수정</b-button>
        <b-button class="button">글 삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mt-3 ml-2 mr-3 mb-1 border rounded border-4">
      <b-col class="mt-3" style="text-align: left">
        <b-form-group id="author-group" label="작성자" label-for="author">
          <b-form-input
            id="author"
            type="text"
            readonly
            placeholder="ssafy"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="date-group" label="작성일" label-for="date">
          <b-form-input
            id="date"
            type="date"
            readonly
            placeholder="날짜들어갈자리"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="subject-group" label="제목" label-for="subject">
          <b-form-input
            id="subject"
            type="text"
            readonly
            placeholder="제목들어갈자리"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="content-group" label="내용" label-for="context">
          <b-form-textarea
            id="content"
            readonly
            placeholder="내용들어갈자리"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "BoardDetail",
  methods: {
    // 리스트 페이지
    moveList() {
      this.$router.push({ name: "boardList" });
    },
    // 수정 페이지
    moveModify() {
      this.$router.push({ name: "boardModify" });
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

<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="author-group" label="작성자" label-for="author">
          <b-form-input
            id="author"
            type="text"
            readonly
            placeholder="ssafy"
          ></b-form-input>
        </b-form-group>

        <!-- 글 작성 - 제목 -->
        <b-form-group
          id="subject-group"
          label="제목"
          label-for="subject"
          v-if="this.type === 'register'"
        >
          <b-form-input
            id="subject"
            type="text"
            required
            placeholder="제목을 입력하세요"
          ></b-form-input>
        </b-form-group>

        <!-- 글 수정 - 제목 -->
        <b-form-group
          id="subject-group"
          label="제목"
          label-for="subject"
          v-else
        >
          <b-form-input
            id="subject"
            type="text"
            required
            placeholder="제목"
          ></b-form-input>
        </b-form-group>

        <!-- 글 작성 - 내용 -->
        <b-form-group
          id="content-group"
          label="내용"
          label-for="content"
          v-if="this.type === 'register'"
        >
          <b-form-textarea
            id="content"
            placeholder="내용을 입력하세요"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <!-- 글 수정 - 내용 -->
        <b-form-group
          id="content-group"
          label="내용"
          label-for="content"
          v-else
        >
          <b-form-textarea
            id="content"
            placeholder="내용"
            required
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          class="button m-1"
          v-if="this.type === 'register'"
          >글 작성</b-button
        >
        <b-button type="submit" class="button m-1" v-else>글 수정</b-button>
        <b-button type="reset" class="button m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
//import http from "@/api/http";

export default {
  name: "BoardInputItem",
  data() {
    return {};
  },
  props: {
    type: { type: String },
  },
};
</script>

<style>
.button {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}
</style>

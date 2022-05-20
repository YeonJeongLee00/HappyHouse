<template>
  <!-- <div class="container">
    <table class="table">
      <thead class="thead-dark">
        <tr align="center">
          <th class="col-md-8">제목</th>
          <th class="col-md-1">날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>[공지사항] HappyHouse 개인정보 처리방침 개정 안내</td>
          <td>2022-05-15</td>
        </tr>
        <tr>
          <td>[공지사항] HappyHouse 업데이트 안내</td>
          <td>2022-05-15</td>
        </tr>
        <tr>
          <td>[공지사항] HappyHouse 공공데이터 안내</td>
          <td>2022-05-15</td>
        </tr>
      </tbody>
    </table>
    <ul class="pagination">
      <li class="page-item"><a class="page-link" href="#">Previous</a></li>
      <li class="page-item active"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
    <button type="button" class="btn btn-outline-warning">등록</button>
  </div> -->
  <b-container class="container bv-example-row mt-3">
    <b-row>
      <b-table-simple hover responsive>
        <b-thead>
          <b-tr class="title" align="center">
            <b-th class="col-md-9 head">제목</b-th>
            <b-th class="col-md-1 head">날짜</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <b-tr>
            <b-td>[공지사항] HappyHouse 공공데이터 안내</b-td>
            <b-td>2022-05-15</b-td>
          </b-tr>
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <board-list-item
            @click="boardDetail(article.articleno)"
            v-for="article in articles"
            :key="article.articleno"
            v-bind="article"
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
// import http from "@/api/http";
// 공지사항 한개 한개 씩 component
//import BoradListItem from "@/components/board/item/BoardListItem.vue";

export default {
  name: "BoardList",
  components: {
    //BoradListItem,
  },
  data() {
    return {
      article: [{ articleno: 1, subject: "공지사항!!" }],
    };
  },
  methods: {
    // 글쓰기 누르면 글 작성 component로 이동
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    boardDetail(articleNo) {
      this.$router.push({ name: "boardDetail", params: articleNo });
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

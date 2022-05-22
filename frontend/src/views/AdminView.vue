<template>
  <div>
    <b-container>
      <h1>회원정보 관리</h1>
    </b-container>
    <b-container class="container bv-example-row mt-3">
      <b-row>
        <b-table-simple hover responsive>
          <b-thead>
            <b-tr class="title" align="center">
              <b-th class="col-md-1 head"></b-th>
              <b-th class="col-md-1 head">아이디</b-th>
              <b-th class="col-md-1 head">비밀번호</b-th>
              <b-th class="col-md-1 head">이름</b-th>
              <b-th class="col-md-2 head">이메일</b-th>
              <b-th class="col-md-1 head">수정</b-th>
              <b-th class="col-md-1 head">삭제</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr v-for="(user, index) in userList" :key="index" align="center">
              <b-td>{{ index + 1 }}</b-td>
              <b-td>{{ user.id }}</b-td>
              <b-td v-model="user.password">{{ user.password }}</b-td>
              <b-td v-model="user.name">{{ user.name }}</b-td>
              <b-td v-model="user.email">{{ user.email }}</b-td>
              <b-td
                ><b-button class="button" @click="updateUser(user.id)"
                  >수정</b-button
                ></b-td
              >
              <b-td
                ><b-button class="button" @click="deleteUser(user.id)"
                  >삭제
                </b-button></b-td
              >
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { getUserList } from "@/api/user.js";

export default {
  data() {
    return {
      userList: null,
    };
  },
  created() {
    getUserList(
      ({ data }) => {
        this.userList = data;
      },
      () => {}
    );
  },
  methods: {
    updateUser(id) {
      console.log(id);
    },
    deleteUser(id) {
      console.log(id);
    },
  },
};
</script>

<style>
h1 {
  text-align: center;
  margin-top: 60px;
}
</style>

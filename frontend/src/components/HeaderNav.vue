<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-light">
      <!-- Brand/logo -->
      <router-link
        class="navbar-brand flex-row d-flex align-items-center"
        to="/"
      >
        <img src="@/assets/logo2.png" alt="logo" class="brand-logo pt-1" />
        <div class="text-center yellow-color">HappyHouse</div>
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target=".collapsibleNavbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Links -->
      <div class="collapse navbar-collapse collapsibleNavbar">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link to="/board" class="nav-link text-white"
              >공지사항</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/board" class="nav-link text-white"></router-link>
          </li>
        </ul>
      </div>
      <div
        class="collapse navbar-collapse collapsibleNavbar justify-content-end"
      >
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link text-white" to="/user/detail"
              >이연정</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link text-white" to="/user/login"
              >로그인</router-link
            >
            <!-- <router-link class="nav-link" href="#" @click="/login">회원가입</router-link> -->
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {};
</script>

<style>
.navbar {
  padding: 15px;
  background-color: #0c3b2e;
}
.nav-link {
  font-size: 17px;
  font-weight: 400;
}

.brand-logo {
  width: 40px;
  margin-right: 1rem;
}
</style>

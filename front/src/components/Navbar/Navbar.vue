<template>
  <nav class="navbar navbar-expand-lg navbar-light sticky-top" style="top:0px">
    <router-link to="/main" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
      <img class="navimg" src="../../assets/img/samplelogo.png" style="width: 70px; height: 70px; top: 0; left: 10px;" />
    </router-link>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <div class="navbar-nav ms-auto p-4 p-lg-0">
        <!-- <router-link to="/mypage" class="nav-item nav-link">마이페이지</router-link> -->
        <router-link to="/calendar" class="nav-item nav-link">캘린더</router-link>
        <router-link to="/camera" class="nav-item nav-link">식단관리</router-link>
        <!-- <router-link to="/foodbuy" class="nav-item nav-link">식재료구매</router-link> -->
        <router-link to="/boardList" class="nav-item nav-link">게시판</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
name: 'Navbar',
};
</script>
/* 특정 디바이스 사이즈에 대한 스타일 */
<style scoped>  
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .navbar{
    height: 70px;
  }
  .navimg{
    width: 50px !important; /* !important를 사용하여 다른 스타일이 덮어쓰지 않도록 */
    height: 50px !important;
    margin-top: 10px;
    margin-bottom: 65px;
  }
  .navbar-toggler{
    margin-top: 10px;
    margin-bottom: 60px;
  }
}

</style>
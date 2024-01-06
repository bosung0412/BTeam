<template>
    <nav class="navbar navbar-expand-lg navbar-light sticky-top">
      <router-link to="/main" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <img src="../../assets/img/samplelogo.png" style="width: 70px; height: 70px; top: 0; left: 10px;" />
      </router-link>
      <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
          <router-link to="/mypage" class="nav-item nav-link">마이페이지</router-link>
          <router-link to="/calender" class="nav-item nav-link">캘린더</router-link>
          <router-link to="/board" class="nav-item nav-link">게시판</router-link>
        <router-link to="/camera_main" class="nav-item nav-link">칼로리 분석</router-link>
        </div>
      </div>
    </nav>
  </template>

<script>
export default {
  name: 'Navbar',
};
</script>
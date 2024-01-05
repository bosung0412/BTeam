<template>
  <div>
    <!-- 네비게이션 바 시작 -->
    <nav class="navbar navbar-expand-lg navbar-light sticky-top">
      <router-link to="/" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <img src="img/samplelogo.png" style="width: 70px; height: 70px; top: 0; left: 10px;" />
      </router-link>
      <button type="button" class="navbar-toggler me-4" @click="toggleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" :class="{ 'show': isNavbarOpen }">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
          <!-- 네비게이션 링크 추가 -->
          <!-- <router-link to="/mypage" class="nav-item nav-link">마이페이지</router-link>
          <router-link to="/calendar" class="nav-item nav-link">캘린더</router-link>
          <router-link to="/board" class="nav-item nav-link">게시판</router-link>
          <router-link to="/calorie-analysis" class="nav-item nav-link">칼로리 분석</router-link> -->
        </div>
      </div>
    </nav>
    <!-- 네비게이션 바 끝 -->

    <!-- 캐러셀 시작 -->
    <div class="container-fluid p-0 wow fadeIn" data-wow-delay="0.1s">
      <div id="header-carousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="w-100" src="img/background.jpg" alt="Image">
            <div class="carousel-caption">
              <div class="container">
                <div class="row justify-content-center">
                  <div class="col-lg-8">
                    <h1 class="display-1 text-white mb-5 animated slideInDown">로그인</h1>
                    <!-- 로그인 입력창 추가 -->
                    <div class="container mt-5">
                      <div class="row justify-content-center">
                        <div class="col-lg-6">
                          <form>
                            <div class="mb-4">
                              <label for="inputEmail" class="form-label">ID</label>
                              <input type="text" class="form-control" id="inputEmail" placeholder="Enter your ID" style="border-radius: 8px;">
                            </div>
                            <div class="mb-4">
                              <label for="inputPassword" class="form-label">Password</label>
                              <input type="password" class="form-control" id="inputPassword" placeholder="Enter your password" style="border-radius: 8px;">
                            </div>
                            <div class="row">
                              <div class="col-6 pe-2">
                                <button @click="login" type="button" class="btn btnmain w-100">로그인</button>
                              </div>
                              <div class="col-6 ps-2">
                                <button @click="redirectToJoin" type="button" class="btn btnmain w-100">회원가입</button>
                              </div>
                            </div>
                            <button @click="redirectToFindAccount" type="button" class="btn btnmain w-100 mt-2">아이디 or 비밀번호찾기</button>
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 캐러셀 끝 -->

    <!-- 저작권 표시 시작 -->
    <div class="container-fluid copyright py-4">
      <div class="container">
        <div class="row">
          <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
            &copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
          </div>
          <div class="col-md-6 text-center text-md-end">
            <!-- ... (저작권 표시 내용) ... -->
          </div>
        </div>
      </div>
    </div>
    <!-- 저작권 표시 끝 -->

    <!-- 맨 위로 가기 버튼 -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isNavbarOpen: false,
    };
  },
  methods: {
    toggleNavbar() {
      this.isNavbarOpen = !this.isNavbarOpen;
    },
    login() {
      // 로그인 로직 추가
      console.log('로그인 시도');
    },
    redirectToJoin() {
      // 회원가입 페이지로 이동하는 로직 추가
      console.log('회원가입 페이지로 이동');
    },
    redirectToFindAccount() {
      // 아이디 또는 비밀번호 찾기 페이지로 이동하는 로직 추가
      console.log('아이디 또는 비밀번호 찾기 페이지로 이동');
    },
  },
};
</script>

<style scoped>
/* 컴포넌트에 특정한 스타일 추가 */
</style>

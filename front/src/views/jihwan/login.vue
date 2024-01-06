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
          <!-- 네비게이션 링크 생략 -->
        </div>
      </div>
    </nav>
    <!-- 네비게이션 바 끝 -->

    <!-- 페이지 헤더 시작 -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">로그인</h1>
        <nav aria-label="breadcrumb animated slideInDown"></nav>
      </div>
    </div>
    <!-- 페이지 헤더 끝 -->

    <!-- 로그인 폼 시작 -->
    <div class="container-fluid py-5">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-7">
          <div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
            <form>
              <!-- 첫 번째 줄: 아이디 입력칸 -->
              <div class="row mb-3">
                <div class="col-sm-12">
                  <label for="id" class="form-label">아이디</label>
                  <input type="text" class="form-control" id="id" name="id">
                </div>
              </div>

              <!-- 두 번째 줄: 비밀번호 입력칸 -->
              <div class="row mb-3">
                <div class="col-sm-12">
                  <label for="password" class="form-label">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password">
                </div>
              </div>

              <!-- 세 번째 줄: 로그인, 아이디/비밀번호 찾기 버튼 -->
              <div class="row mb-3">
                <div class="col-sm-6">
                  <button @click="submitLogin" type="button" class="btn btn-primary btnall w-100">로그인</button>
                </div>
                
                <div class="col-sm-6">
                  <button @click="redirectToJoin" type="button" class="btn btn-primary btnall w-100">회원가입</button>
                </div>
              </div>
                

              <!-- 네 번째 줄: 회원가입 버튼 -->
              <div class="row">
              <div class="col-sm-12">
                  <button @click="redirectToFindAccount" type="button" class="btn btn-primary btnall w-100">아이디 or 비밀번호 찾기</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
    <!-- 로그인 폼 끝 -->

    <!-- ... (이전 코드) ... -->

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
    submitLogin() {
      console.log('로그인 정보를 서버로 전송');
    },
    redirectToJoin() {
      this.$router.push('/join'); 
    },
    redirectToFindAccount() {
      this.$router.push('/find'); 
    },
  },
};
</script>

<style>

</style>

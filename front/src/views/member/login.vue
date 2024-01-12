<template>
  <div>
    <!-- 네비게이션 바 시작 -->
      <Navbar /> 
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
                  <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력해주세요!">
                </div>
              </div>

              <!-- 두 번째 줄: 비밀번호 입력칸 -->
              <div class="row mb-3">
                <div class="col-sm-12">
                  <label for="password" class="form-label">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요!">
                </div>
              </div>

              <!-- 네 번째 줄: 로그인 버튼 -->
              <div class="row">
              <div class="col-sm-12">
                  <button @click="submitLogin" type="button" class="btn btn-primary btnall w-100">로그인</button>
                </div>
              </div>

              <!-- 세 번째 줄: 회원가입, 아이디/비밀번호 찾기 버튼 -->
              <div class="row mb-3">
                <div class="col-sm-6">
                  <button @click="redirectToJoin" type="button" class="btn btn-primary btnall w-100">회원가입</button>
                </div>

                <div class="col-sm-6">
                  <button @click="redirectToFindAccount" type="button" class="btn btn-primary btnall w-100">아이디 or 비밀번호 찾기</button>
                </div>

                <div class="col-sm-12">
                  <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼"/>
                </div>

              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
    <!-- 로그인 폼 끝 -->
    <Footer />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '@/components/Footer/Footer.vue';
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
    kakaoLogin() {
      window.Kakao.Auth.login({
        scope: "profile_image, account_email",
        success: this.getKakaoAccount,
      });
    },getKakaoAccount() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;
          const ninkname = kakao_account.profile.ninkname;
          const email = kakao_account.email;
          console.log("ninkname", ninkname);
          console.log("email", email);

          //로그인처리구현

          alert("로그인 성공!");
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    submitLogin() {
      console.log('로그인 중입니다.');
      this.$router.push('/Homeview');
    },
    redirectToJoin() {
      this.$router.push('/join'); 
    },
    redirectToFindAccount() {
      this.$router.push('/findaccount');
    },
  },
  components: {
    Navbar,
    Footer,
  }
};
</script>



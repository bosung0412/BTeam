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

                <div class="col-sm-12 text-center">
                  <img src='@/assets/img/kakao.png' @click="kakaoLogin" alt="카카오 로그인" style="cursor: pointer;"/>
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
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      isNavbarOpen: false,
    };
  },
  methods: {
    toggleNavbar() {
      this.isNavbarOpen = !this.isNavbarOpen;
    },kakaoLogin() {
        const redirect_uri = 'http://localhost:8081/MainView'; // redirect_uri 내가 정한거
        const clientId = '27be1209a5e94ef12e0e5d5a27ae9161'; // kakao developer 키
        // kakao에서 정해줌
        const Auth_url = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirect_uri}`;
        window.location.href = Auth_url;
      },
    //해당 페이지가 로딩되자마자 인자로 받았던 인가코드를 빼내어 카카오톡에게 토큰값을 요청
    created() {
      // code 안에 query로 받아온 값을 저장
      this.code = this.$route.query.code;
      console.log(this.code)
      this.getToken();
  },
  // token 요청해야하기때문에 실행되자마자 code에 담아둔 내용을 get으로 서버로 전달 
  getToken() { 
      // this를 self 변수로 할당
      const self = this;
      // 서버에다가 보냄(get)
      self.$axios.get('' + self.code)
        .then((res) => {
          console.log(res)
          //  응답 데이터를 vue데이터에 할당
          self.form.id = res.data.id;
          self.form.email = res.data.email;
          self.form.pwd = res.data.pwd;
          self.form.kakaotoken = res.data.accessToken;
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
  }
}
</script>



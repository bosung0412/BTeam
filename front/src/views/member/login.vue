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
                  <input type="text" class="form-control" id="id" name="id" v-model="id" placeholder="아이디를 입력해주세요!">
                </div>
              </div>

              <!-- 두 번째 줄: 비밀번호 입력칸 -->
              <div class="row mb-3">
                <div class="col-sm-12">
                  <label for="password" class="form-label">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" v-model="password" placeholder="비밀번호를 입력해주세요!">
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
import axios from 'axios';
export default {
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      isNavbarOpen: false,
      id: '',
      password: '',
      code : '',
    };
  },
  created() {
    // URL에서 인증 코드 추출
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');

     // 인증 코드가 있으면 백엔드에 요청
     if (code) {
      this.kakaoLogin(code);
    }

  },
  methods: {
    toggleNavbar() {
      this.isNavbarOpen = !this.isNavbarOpen;
    }, 
    kakaoLogin() {
    const redirect_uri = 'http://192.168.0.4:8081/join'; // redirect_uri 내가 정한거
    const clientId = '27be1209a5e94ef12e0e5d5a27ae9161'; // kakao developer 키

    const Auth_url = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirect_uri}`;
    // Kakao 로그인 페이지로 리다이렉트
    

    this.getKakaoUserInfo(this.code);

    window.location.href = Auth_url;
  },
    async getKakaoUserInfo(code) {
    try {
      console.log("urllllll", window.location.search)
      console.log("codeee",code);
      const response = await axios.post('http://192.168.0.4/project/api/v1/auth/oauth2', { code: code });
      const jwtToken = response.data.jwtToken;

      // Vuex 스토어의 setAuthToken 뮤테이션을 호출하여 토큰을 저장
      this.$store.commit('setAuthToken', jwtToken);

      // 콘솔에 저장된 토큰 출력
      console.log(this.$store.state.authToken);

      // 메인 페이지로 리다이렉트
      this.$router.push('/main');
    } catch (error) {
      console.error('사용자 정보 요청 실패', error);
      // 에러 처리 로직
    }
        },
  
      submitLogin() {
        axios.post('http://192.168.0.4/project/api/v1/auth/sign-in', {
          id: this.id,
          password: this.password,
          
    })
          .then(response => {
          const token = response.data.token;
          console.log('로그인 성공', response);
       
          //Vuex 스토어에 토큰 저장
          this.$store.commit('setAuthToken', token);
          // localStorage에도 저장
          localStorage.setItem('authToken', token);

          const payloadBase64 = token.split('.')[1];
          const decodedPayload = JSON.parse(atob(payloadBase64));
          console.log('Decoded Token:', decodedPayload);
          console.log(this.$store.state.authToken);
          this.$router.push('/main');

      })
          .catch(error => {
          console.error('로그인 실패', error);
          sessionStorage.setItem('token', null); // 로그인 실패 시 토큰을 null로 설정
          // 로그인 실패 시 처리 (예: 에러 메시지 표시)
      });

  }
    },
}
</script>
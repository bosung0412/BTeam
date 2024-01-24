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
                  <input type="password" class="form-control" id="password" name="password" v-model="password" placeholder="비밀번호를 입력해주세요!" >
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
      submitLogin() {
        this.checkEmpty();
        axios.post('http://localhost/project/api/v1/auth/sign-in', {
          id: this.id,
          password: this.password,
          
    })
          .then(response => {
          const token = response.data.token;
          console.log('로그인 성공', response);
       
          //Vuex 스토어에 토큰 저장
          this.$store.commit('setAuthToken', token);

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
    },redirectToJoin(){
      this.$router.push('/join');
    }
  }
</script>
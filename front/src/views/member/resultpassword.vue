<template>
    <div>
      <!-- Navbar -->
      <Navbar />
  
      <!-- Page Header -->
      <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
        <!-- Page Header content -->
        <div class="container text-center py-5">
          <h1 class="display-5 text-white mb-4 animated slideInDown">비밀번호 찾기 결과</h1>
          <nav aria-label="breadcrumb animated slideInDown">
          </nav>
        </div>
      </div>
  
      <!-- Result Section -->
      <div class="container-fluid py-5">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-7">
              <div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
                <div class="row g-3">
                  <div class="mb-2 text-left">
                    <p class="mb-2"><strong>입력한 아이디:</strong> </p>
                    <p class="mb-2"><strong>찾은 비밀번호:</strong> </p>
                  </div>
                  <div class="col-sm-12">
                    <button type="button" class="btn btn-primary btnall w-100" @click="goToLogin">로그인 하러가기</button>
                </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Copyright -->
      <Footer />
  
    </div>
  </template>
  <script>
  import Navbar from '@/components/Navbar/Navbar.vue';
  import Footer from '@/components/Footer/Footer.vue';
  export default {
    methods: {
      goToLogin() {
        // 라우터를 이용하여 login 페이지로 이동
        this.$router.push({ name: 'login' })
      }
    },
    components: {
    Navbar,
    Footer,
  }
};
  
  </script>
 
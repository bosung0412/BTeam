<template>
    <div>
      <Navbar/>
  <!-- 페이지 헤더 시작 -->
  <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
    <div class="container text-center py-3">
      <h3 class="display-5 text-white mb-2 animated slideInDown">비밀번호 찾기 결과</h3>
      <nav aria-label="breadcrumb animated slideInDown">
            <!-- Breadcrumb (경로) -->
          </nav>
    </div>
  </div>
<!-- 페이지 헤더 끝 -->
  
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
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 저작권 시작 -->
      <Footer/>
      <!-- 저작권 끝 -->
  
      <!-- Back to Top -->
      <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>
  </template>
 <script>
    
 import Navbar from '@/components/Navbar/Navbar.vue';
 import Footer from '../../components/Footer/Footer.vue';
 
   export default {
     components:{
         Navbar,
         Footer,
     },
     data(){
       return{

       }
     }
   }
 </script>
 
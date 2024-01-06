<template>
    <div>
  <Navbar/>
  <!-- 페이지 헤더 시작 -->
  <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
    <div class="container text-center py-3">
      <h3 class="display-5 text-white mb-2 animated slideInDown">아이디 찾기</h3>
      <nav aria-label="breadcrumb animated slideInDown">
            <!-- Breadcrumb (경로) -->
          </nav>
    </div>
  </div>
   <!-- 페이지 헤더 끝 -->
      <div class="container-fluid py-5">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-7">
              <div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
                <div class="row g-3">
                  <label for="Name" class="form-label">이름</label>
                  <input type="text" class="form-control" id="name" placeholder="아이디를 입력하세요">
                  <label for="Email" class="form-label">이메일</label>
                  <input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요">
                  <button type="button" class="btn btn-success mt-3 btnall">아이디 찾기</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- 저작권 시작 -->
 <Footer/>
      <!-- 저작권 끝 -->
  
      <!-- 맨 위로 가기 버튼 -->
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
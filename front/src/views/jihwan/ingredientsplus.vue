<template>
  <div>
      <Navbar /> 

    <!-- 페이지 헤더 시작 -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">식재료 추가</h1>
        <nav aria-label="breadcrumb animated slideInDown">
        </nav>
         <!-- 프로젝트 시작 -->
    <div class="container-xxl py-5">
      <div class="container mt-5">
        <div class="row g-5 maincol pb-4">
          <div class="col-12 col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.3s">
            <form action="" method="post" enctype="multipart/form-data" class="text-center form-group">
              <div id="webcamContainer" style="position: relative;">
                <video id="webcam" width="100%" height="auto" autoplay style="border: 2px solid #ccc; border-radius: 8px;"></video>
              </div>
              <button id="captureBtn" class="btn btn-primary mt-2 d-block mx-auto btnall">촬영하기</button>
              <input type="file" class="form-control mt-2" id="foodImage" name="foodImage" accept="image/*">
              <img src="" alt="주의사항 이미지" class="img-fluid mt-2">
              <img src="" alt="주의사항 이미지" class="img-fluid mt-2">
            </form>
          </div>

          <div class="col-12 col-md-6 col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
            <form class="text-center">
              <div class="d-flex justify-content-center flex-column">
                <div class="form-group d-flex justify-content-center">
                  <select class="form-control mb-3" id="foodCategory" name="foodCategory">
                    <option value="" selected disabled>--선택해주세요--</option>
                    <option value="야채">야채</option>
                    <option value="육류">육류</option>
                    <option value="과일">과일</option>
                    <option value="견과류">견과류</option>
                  </select>
                </div>

                <label for="foodName" class="form-label">식재료 명</label>
                <input type="text" class="form-control mb-3" id="foodName" name="foodName">

                <label for="calories" class="form-label">칼로리</label>
                <input type="number" class="form-control" id="calories" name="calories">

                <div class="d-flex justify-content-center mt-3">
                  <button onclick="window.location.href='refrigeratorcheck.html'" type="button" class="btn btn-primary mx-2 btnall">저장하기</button>
                  <button onclick="window.location.href='feature.html'" type="button" class="btn btn-primary mx-2 btnall">취소하기</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- 프로젝트 끝 -->

      </div>
      
    </div>
    <!-- 페이지 헤더 끝 -->

   
    <!-- 저작권 정보 시작 -->
    <Footer />
    <!-- 저작권 정보 끝 -->

    <!-- 페이지 상단으로 이동하기 버튼 -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top">
      <i class="bi bi-arrow-up"></i>
    </a>
  </div>
</template>
<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '@/components/Footer/Footer.vue';
export default {
  components: {
    Navbar,
    Footer,
  }
};
  </script>

<template>
    <div>
      <!-- 네비게이션 바 시작 -->
      <nav class="navbar navbar-expand-lg navbar-light sticky-top">
        <a href="home.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
          <img src="img/samplelogo.png" style="width: 70px;height: 70px;top: 0;left: 10px;" />
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <div class="navbar-nav ms-auto p-4 p-lg-0">
            <!-- 네비게이션 링크 -->
          </div>
        </div>
      </nav>
      <!-- 네비게이션 바 끝 -->
  
      <!-- 페이지 헤더 시작 -->
      <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
          <h1 class="display-5 text-white mb-4 animated slideInDown">아이디 찾기</h1>
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
                  <button type="button" class="btn btn-primary mt-3 btnall">아이디 찾기</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- 저작권 시작 -->
      <div class="container-fluid copyright py-4">
        <div class="container">
          <div class="row">
            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
              &copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
            </div>
            <div class="col-md-6 text-center text-md-end">
              <!-- 여기는 템플릿 제작자의 크레딧 정보 -->
            </div>
          </div>
        </div>
      </div>
      <!-- 저작권 끝 -->
  
      <!-- 맨 위로 가기 버튼 -->
      <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>
  </template>
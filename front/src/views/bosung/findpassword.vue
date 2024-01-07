<template>
  <div>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light sticky-top">
      <!-- Navbar content -->
      <a href="home.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <img src="img/samplelogo.png" style="width: 70px;height: 70px;top: 0;left: 10px;" />
      </a>
      <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
      </button>
      <!-- <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
          <a href="file:///C:/Users/user/Desktop/123/123/gardener-1.0.0/mypage.html" class="nav-item nav-link">마이페이지</a>
          <a href="file:///C:/Users/user/Desktop/123/123/gardener-1.0.0/calender.html" class="nav-item nav-link">캘린더</a>
          <a href="file:///C:/Users/user/Desktop/123/123/gardener-1.0.0/board.html" class="nav-item nav-link">게시판</a>
          <a href="file:///C:/Users/user/Desktop/123/123/gardener-1.0.0/camera_main.html" class="nav-item nav-link">칼로리 분석</a>
        </div>
      </div> -->
    </nav>

    <!-- Page Header -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <!-- Page Header content -->
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">비밀번호 찾기</h1>
        <nav aria-label="breadcrumb animated slideInDown">
        </nav>
      </div>
    </div>

    <!-- Input and Dropdown Section -->
    <div class="container-fluid py-5">
      <div class="container">
        <!-- Input and Dropdown Section content -->
        <div class="row justify-content-center">
          <div class="col-lg-7">
            <div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
              <div class="row g-3">
                <form class="d-flex flex-column">
                  <!-- Form content -->
                  <!-- 아이디 입력 칸 -->
                  <div class="mb-3">
                    <label for="username" class="form-label">아이디</label>
                    <input type="text" class="form-control" id="username" placeholder="아이디를 입력하세요">
                  </div>

                  <!-- 비밀번호 찾기 질문 드롭다운 -->
                  <div class="mb-3">
                    <label for="question" class="form-label">비밀번호 찾기 질문</label>
                    <select class="form-select" id="question">
                      <option selected>질문을 선택하세요</option>
                      <option value="q1">질문 1</option>
                      <option value="q2">질문 2</option>
                      <option value="q3">질문 3</option>
                      <!-- 필요한 만큼 추가 -->
                    </select>
                  </div>

                  <!-- 질문에 대한 답변 입력 칸과 비밀번호 찾기 버튼 -->
                  <div class="mb-3 d-flex align-items-start">
                    <div class="flex-grow-1 me-3">
                      <label for="answer" class="form-label">질문에 대한 답변</label>
                      <input type="text" class="form-control" id="answer" placeholder="질문에 대한 답변을 입력하세요">
                    </div>
                  </div>
                  <button type="button" class="btn btn-primary align-self-center btnall">비밀번호 찾기</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Quote -->
    <!-- Quote content -->
    <!-- ... -->

    <!-- Copyright -->
    <div class="container-fluid copyright py-4">
      <div class="container">
        <!-- Copyright content -->
        <div class="row">
          <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
            &copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
          </div>
          <div class="col-md-6 text-center text-md-end">
            <!-- Copyright disclaimer -->
          </div>
        </div>
      </div>
    </div>

    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>
  </div>
</template>
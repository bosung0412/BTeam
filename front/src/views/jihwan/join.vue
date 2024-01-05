<template>
    <div>
      <!-- 네비게이션 바 시작 -->
      <nav class="navbar navbar-expand-lg navbar-light sticky-top">
        <router-link to="/" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
          <img src="img/samplelogo.png" style="width: 70px; height: 70px; top: 0; left: 10px;" />
        </router-link>
        <button type="button" class="navbar-toggler me-4" @click="toggleNavbar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" :class="{ 'show': isNavbarOpen }">
          <div class="navbar-nav ms-auto p-4 p-lg-0">
            <!-- 네비게이션 링크 추가 -->
            <!-- <router-link to="/mypage" class="nav-item nav-link">마이페이지</router-link>
            <router-link to="/calendar" class="nav-item nav-link">캘린더</router-link>
            <router-link to="/board" class="nav-item nav-link">게시판</router-link>
            <router-link to="/calorie-analysis" class="nav-item nav-link">칼로리 분석</router-link> -->
          </div>
        </div>
      </nav>
      <!-- 네비게이션 바 끝 -->
  
      <!-- 페이지 헤더 시작 -->
      <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
          <h1 class="display-5 text-white mb-4 animated slideInDown">회원가입</h1>
          <nav aria-label="breadcrumb animated slideInDown"></nav>
        </div>
      </div>
      <!-- 페이지 헤더 끝 -->
  
      <!-- 인용구 시작 -->
      <div class="container-fluid py-5">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-7">
              <div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
                <div class="row g-3">
                  <form>
                    <div class="col-sm-8">
                      <label for="id" class="form-label">아이디</label>
                      <div class="input-group">
                        <input type="text" class="form-control" id="id" name="id">
                        <div class="input-group-append">
                          <button type="button" class="btn btn-primary btnall" style="margin-bottom: 0px;">중복확인</button>
                        </div>
                      </div>
  
                      <label for="password" class="form-label">비밀번호</label>
                      <input type="text" class="form-control" id="password" name="password">
  
                      <label for="passwordchk" class="form-label">비밀번호 확인</label>
                      <input type="text" class="form-control" id="passwordchk" name="passwordchk">
  
                      <label for="name" class="form-label">이름</label>
                      <input type="text" class="form-control" id="name" name="name">
  
                      <label for="address" class="form-label">주소</label>
                      <input type="text" class="form-control" id="address" name="address">
  
                      <label for="phoneNumber" class="form-label">휴대전화</label>
                      <input type="text" class="form-control" id="phoneNumber" maxlength="13" @input="formatPhoneNumber">
  
                      <label for="email" class="form-label mt-3">이메일</label>
                      <div class="input-group">
                        <input type="text" class="form-control" id="email" name="email">
                        <div class="input-group-append">
                          <button type="button" class="btn btn-primary btnall" style="margin-bottom: 0px;">중복확인</button>
                        </div>
                      </div>
                    </div>
  
                    <p class="fs-5 fw-bold text-primary">추가 정보</p>
                    <div class="field gender">
                      <b>성별</b>
                      <div class="d-flex">
                        <div class="form-check me-3">
                          <input class="form-check-input" type="checkbox" value="남자" id="man" name="man">
                          <label class="form-check-label" for="man">남자</label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="checkbox" value="여자" id="woman" name="woman" style="order: 1;">
                          <label class="form-check-label" for="woman">여자</label>
                        </div>
                      </div>
                    </div>
                    <div class="col-sm-6">
                      <label for="allergy" class="form-label">질병</label>
                      <input type="text" class="form-control border" id="allergy" name="allergy" @input="updateCheckboxVisibility">
                      <div v-show="showCheckboxContainer" id="checkboxContainer">
                        <input type="checkbox" id="checkbox"> 배탈, 복통
                      </div>
                      <label for="height" class="form-label">키(cm)</label>
                      <input type="text" class="form-control border" id="height" name="height">
                      <label for="weight" class="form-label">몸무게(kg)</label>
                      <input type="text" class="form-control border" id="weight" name="weight">
                    </div>
                    <div class="col-12 text-center">
                      <button @click="submitRegistration" type="button" class="btn btn-primary mx-2 btnall">회원가입</button>
                      <button type="button" class="btn btn-primary mx-2 btnall">취소</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 인용구 끝 -->
  
      <!-- 저작권 표시 시작 -->
      <div class="container-fluid copyright py-4">
        <div class="container">
          <div class="row">
            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
              &copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
            </div>
            <div class="col-md-6 text-center text-md-end">
              <!-- 저작권 표시 -->
            </div>
          </div>
        </div>
      </div>
      <!-- 저작권 표시 끝 -->
  
      <!-- 최상단으로 이동 버튼 -->
      <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isNavbarOpen: false,
        showCheckboxContainer: false,
      };
    },
    methods: {
      toggleNavbar() {
        this.isNavbarOpen = !this.isNavbarOpen;
      },
      formatPhoneNumber() {
        // 휴대전화 번호 포맷팅 로직 추가
        let input = document.getElementById("phoneNumber");
        let phoneNumber = input.value.replace(/\D/g, '');
  
        if (phoneNumber.length > 3 && phoneNumber.length <= 6) {
          phoneNumber = phoneNumber.replace(/(\d{3})(\d{1,4})/, '$1-$2');
        } else if (phoneNumber.length > 6) {
          phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{1,4})/, '$1-$2-$3');
        }
  
        input.value = phoneNumber;
      },
      updateCheckboxVisibility() {
        // 체크박스 가시성 업데이트 로직 추가
        var inputValue = document.getElementById("allergy").value;
        this.showCheckboxContainer = inputValue.includes("배");
      },
      submitRegistration() {
        // 회원가입 제출 로직 추가
        console.log('회원가입 정보를 서버로 전송');
      },
    },
  };
  </script>
  
  <style scoped>
  /* 컴포넌트에 특정한 스타일 추가 */
  </style>
  
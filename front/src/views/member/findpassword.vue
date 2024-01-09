<template>
  <div>
    <!-- Navbar -->
    <Navbar />

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
      <Footer />
    </div>

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
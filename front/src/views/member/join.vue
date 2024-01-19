<template>
  <div>
    <!-- 네비게이션 바 시작 -->
    <Navbar /> 
    <!-- 네비게이션 바 끝 -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
  <div class="container text-center py-3">
    <h3 class="display-5 text-white mb-2 animated slideInDown">회원가입</h3>
  </div>
</div>
      <!-- 페이지 헤더 시작 -->
      <!-- <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">회원가입</h1>
        <nav aria-label="breadcrumb animated slideInDown"></nav>
      </div>
    </div> -->
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
                      <input type="text"  class="form-control" id="id" v-model="id" name="id" placeholder="아이디 입력">
                      <div class="input-group-append">
                        <button type="button" class="btn btn-success btnall" @click="checkDuplicate">중복확인</button>
                      </div>
                    </div>

                    <label for="password" class="form-label mt-2">비밀번호</label>
                    <input type="text" class="form-control" id="password" name="password">

                    <label for="passwordchk" class="form-label mt-2">비밀번호 확인</label>
                    <input type="text" class="form-control" id="passwordchk" name="passwordchk">

                    <label for="name" class="form-label mt-2">이름</label>
                    <input type="text" class="form-control" id="name" name="name">

                    <label for="birth" class="form-label mt-2">생년월일</label>
                    <input type="text" class="form-control" id="birth" name="birth">

                    <label for="email" class="form-label mt-2">주소</label>
                    <div class="input-group">
                      <input type="text" class="form-control" id="email" name="email">
                      <div class="input-group-append">
                        <button type="button" class="btn btn-success btnall" onclick="openAddressPopup()" style="margin-bottom: 0px;">주소찾기</button>

                      </div>
                    </div>

                    <label for="phoneNumber" class="form-label mt-2">휴대전화</label>
                    <input type="text" class="form-control" id="phoneNumber" maxlength="13" @input="formatPhoneNumber">

                    <label for="email" class="form-label mt-2">이메일</label>
                    <div class="input-group">
                      <input type="email" class="form-control" id="email" name="email" v-model="email" placeholder="이메일 주소를 입력하세요">
                      <div class="input-group-append">
                        <button type="button"  class="btn btn-success btnall" @click="requestCertificationNumber" style="margin-bottom: 0px;">인증번호받기</button>
                      </div>
                    </div>
                    <label for="certification" class="form-label mt-2">인증번호</label>
                    <div class="input-group">
                        <input type="certificationnumber" class="form-control" id="certificationnumber" name="certificationnumber" v-model="certificationnumber" placeholder="인증 번호를 입력하세요">
                        <button type="button" class="btn btn-success btnall" @click="verifyCertificationNumber()">인증번호 확인</button>
                    </div>
                    <label for="question" class="form-label mt-2">비밀번호찾기 질문</label>
                    <input type="text" class="form-control" id="question" name="question">


                    <label for="answer" class="form-label mt-2">비밀번호찾기 답변</label>
                    <input type="text" class="form-control" id="answer" name="answer">
                  </div>

                  <p class="fs-5 fw-bold" style="color: rgb(77, 183, 91);">추가 정보</p>
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
                    <label for="allergy" class="form-label mt-2">질병</label>
                    <input type="text" class="form-control border" id="allergy" name="allergy" @input="updateCheckboxVisibility">
                    <div v-show="showCheckboxContainer" id="checkboxContainer">
                    </div>
                    <label for="height" class="form-label mt-2">키(cm)</label>
                    <input type="text" class="form-control border" id="height" name="height">
                    <label for="weight" class="form-label mt-2">몸무게(kg)</label>
                    <input type="text" class="form-control border" id="weight" name="weight">

                  </div>
                  <div class="col-12 text-center mt-4">
                    <button @click="submitRegistration" type="button" class="btn btn-success mx-2 btnall">회원가입</button>
                    <button @click="cancel" type="button" class="btn btn-primary mx-2 btnall">취소</button>
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
    <Footer />
    <!-- 저작권 표시 끝 -->

  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from 'axios';
export default {
  components: {
  Navbar,
  Footer,
},
  data() {
    return {
      isNavbarOpen: false,
      showCheckboxContainer: false,
      email: '', // 이메일 바인딩 데이터 초기화
      id: '', 
      duplicateResult: null,
      certificationnumber: '',
    };
  },
  methods: {
    isValidEmail(email) {
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailRegex.test(email);
    },
    requestCertificationNumber() {
        if (!this.isValidEmail(this.email)) { 
            alert("유효한 이메일 주소를 입력하세요.");
            return;
        }
        axios.post('http://localhost/project/api/v1/auth/email-certification', { 
          id : this.id,
          email: this.email,
        })
            .then(response => {
                
                if (response.data.success) {
                    console.log(" 발송 완료")
                    alert("인증번호가 전송되었습니다 최대 3분이 걸릴 수 있어요~");
                } else {
                    alert("이메일 인증 요청 실패: " + response.data.message);
                }
            })
            .catch(error => {
                console.error('로그인 실패', error);
                alert("서버와 통신 중 오류가 발생했습니다.");
            });
    },
    checkDuplicate() {
            // 서버로 아이디 중복성 체크 요청 보내기
            axios.post('http://localhost/project/api/v1/auth/id-check', { 
              id: this.id 
            })
                .then(response => {
                    // 서버로부터 응답을 받았을 때 처리
                    if (response.data.duplicate) {
                        console.log(" 중복")
                        alert("중복 된 아이디입니다 !! 다른아이디 이용부탁드립니다(합장합장)")
                        this.duplicateResult = false; // 중복된 아이디
                    } else {
                        console.log("사용가능")
                        alert("사용 가능한 아이디입니다 !")
                        this.duplicateResult = true; // 사용 가능한 아이디
                    }
                })
                .catch(error => {
                    alert("중복 확인 실패.");
                    console.error('아이디 중복 확인 실패', error);
                    this.duplicateResult = null; // 오류 발생 시 초기화
                });
    },
     verifyCertificationNumber() {
            // 서버로 인증 번호 확인 요청 보내기
            axios.post('http://localhost/project/api/v1/auth/check-certification', { 
              id : this.id,
              email : this.email,
              certificationnumber: this.certificationnumber 
            })
                .then(response => {
                    // 서버로부터 응답을 받았을 때 처리
                    if (response.data.verified) {
                        alert("인증번호가 일치합니다.");
                        this.certificationnumber = true; // 인증번호 일치
                    } else {
                       alert("인증번호가 불일치합니다.");
                        this.certificationnumber = false; // 인증번호 불일치
                    }
                })
                .catch(error => {
                    console.error('인증번호 확인 실패', error);
                    this.certificationnumber = null; // 오류 발생 시 초기화
                });
              },
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
      console.log('회원가입중 입니다.');
      this.$router.push('/login');
    },
    cancel() {
      this.$router.push('/');
    },
  },

};

</script>

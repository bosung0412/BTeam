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
    <!-- 인용구 시작 -->
    <div class="container-fluid py-5">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-7">
            <div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
              <div class="row g-3">
                <form>
                  <div class="col-sm-9 col-lg-9">
                    <label for="id" class="form-label">아이디</label>
                    <div class="input-group">
                      <input type="text"  class="form-control" id="id" v-model="id" name="id" placeholder="아이디 입력">
                      <div class="input-group-append">
                        <button type="button" class="btn btn-success btnall" @click="checkDuplicate" style="margin-bottom: 0px;" :disabled="!id.trim()">중복확인</button>
                      </div>
                    </div>
                    <label for="password" class="form-label mt-2">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" v-model="password">

                    <label for="passwordchk" class="form-label mt-2">비밀번호 확인</label>
                    <input type="password" class="form-control" id="passwordchk" name="passwordchk" v-model="confirmPassword"  @input="validatePasswordForm">
                    <p v-if="errorMessage" style="color:red;margin: 0px;font-size: 1rem;">{{ errorMessage }}</p>

                    <label for="name" class="form-label mt-2">이름</label>
                    <input type="text" class="form-control" id="name" name="name" v-model="name">

                    <label for="birth" class="form-label mt-2">생년월일</label>
                    <input type="text" class="form-control" id="birth" name="birth" v-model="birth">

                    <label for="address" class="form-label mt-2">주소</label>
                    <div class="input-group">
                      <input type="text" class="form-control" id="address" name="address" v-model="address" placeholder="주소를 입력하세요.">
                      <div class="input-group-append">
                        <button type="button" class="btn btn-success btnall" @click="openAddressPopup" style="margin-bottom: 0px;">주소찾기</button>
                      </div>
                    </div>
                     <!-- 주소찾기 버튼 클릭 시 주소 검색 화면 -->
                    <div ref="wrap" :style="{ display: ishidden}">
                      <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" @click="closeAddress">
                    </div>
                    <div>
                      <label for="phoneNumber" class="form-label mt-2">휴대전화</label>
                    <input type="text" class="form-control" id="phoneNumber" maxlength="13" @input="formatPhoneNumber" v-model="phoneno">

                    <label for="email" class="form-label mt-2">이메일</label>
                    <div class="input-group">
                      <input type="email" class="form-control" id="email" name="email" v-model="email" placeholder="이메일 주소를 입력하세요">
                      <div class="input-group-append">
                        <button type="button"  class="btn btn-success btnall" @click="requestCertificationNumber" style="margin-bottom: 0px;">인증번호받기</button>
                      </div>
                    </div>
                    <label for="certification" class="form-label mt-2">인증번호</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="certificationNumber" name="certificationNumber" v-model="certificationNumber" placeholder="인증 번호를 입력하세요">
                        <div class="input-group-append">
                         <button type="button" class="btn btn-success btnall" @click="verifyCertificationNumber" style="margin-bottom: 0px;">인증번호 확인</button>
                        </div>
                    </div>
                    <label for="question" class="form-label mt-2">비밀번호찾기 질문</label>
                    <select class="form-select" v-model="selectedQuestion" @click="questionCheck">
                      <option disabled value="" v-if="questions.length === 0">선택해주세요</option>
                      <option v-for="item in questions" :key="item.id" >{{ item.question }}</option>
                    </select>
                    <!-- <input type="text" class="form-control" v-model="question"> -->

                    <label for="answer" class="form-label mt-2">비밀번호찾기 답변</label>
                    <input type="text" class="form-control" id="answer" name="answer" v-model="answer">
                  </div>

                  <p class="fs-5 fw-bold" style="color: rgb(77, 183, 91);">추가 정보</p>
                  <div class="field gender">
                    <b>성별</b>
                    <div class="d-flex">
                      <div class="form-check me-3">
                        <input class="form-check-input" type="radio" value="남자" id="man" name="man" v-model="gender">
                        <label class="form-check-label" for="man">남자</label>
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" type="radio" value="여자" id="woman" name="woman" style="order: 1;" v-model="gender">
                        <label class="form-check-label" for="woman">여자</label>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-6">
                    <label for="allergy" class="form-label mt-2">질병</label>
                    <input type="text" class="form-control border" id="allergy" name="allergy" v-model="disease">
                    </div>
                    <label for="height" class="form-label mt-2">키(cm)</label>
                    <input type="text" class="form-control border" id="height" name="height" v-model="height">
                    <label for="weight" class="form-label mt-2">몸무게(kg)</label>
                    <input type="text" class="form-control border" id="weight" name="weight" v-model="weight">
                  <div class="col-12 text-center mt-4">
                    <button @click="submitRegistration" type="button" class="btn btn-success mx-2 btnall">회원가입</button>
                    <button @click="cancel" type="button" class="btn btn-primary mx-2 btnall">취소</button>
                  </div>
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
      // question:'',
      questions:[],
      selectedQuestion:'',
      ishidden:'none',
      id:'', 
      password:'',
      name:'',
      birth:'',
      address:'',
      phoneno:'',
      email: '', 
      answer:'',
      gender:'',
      disease:'',
      height:'',
      weight:'',
      duplicateResult: null,
      certificationNumber: '',
      confirmPassword:'',
      errorMessage:'',
    };
  },
  methods: {
    //비밀번호 질문 
    questionCheck(){
      // if(this.questions.length===0){
       
      // }else{
      //   console.log("seleced: "+this.selectedQuestion);
      // }
      console.log("=============여기 오는거니");
        axios.get('http://localhost/project/api/v1/auth/getquestion')
        .then(response=>{
          this.questions=response.data;
        }).catch(error=>{
          console.error('error: '+error);
        });
    },
    //패스워드 일치 여부 확인
    validatePasswordForm(){
      if(this.password!==this.confirmPassword){
        this.errorMessage = '패스워드가 일치하지 않습니다.';
        return false;
      }
      this.errorMessage='';
      return true;
    },
    //주소창 닫기(x)표시
    closeAddress(){
      this.ishidden='none';
    },
    //주소찾기 버튼 
    openAddressPopup(){
      new window.daum.Postcode({
        oncomplete:(data)=>{
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
            this.closeAddress();
          } else { // 사용자가 지번 주소를 선택했을 경우(J)
                  this.address = data.jibunAddress;
                  this.closeAddress();
          }
        }
      }).embed(this.$refs.wrap);
      this.ishidden='block';
    },
        // 서버로 아이디 중복성 체크 요청 보내기
        checkDuplicate() {
            axios.post('http://localhost/project/api/v1/auth/id-check', { id:this.id})
                .then(response => {
                    // 서버로부터 응답을 받았을 때 처리
                    if (response.status===200) {
                        alert("사용 가능한 아이디입니다.");
                        // this.duplicateResult = true; // 사용 가능한 아이디
                    } 
                })
                .catch(error => {
                  alert("중복 된 아이디입니다. 다른 아이디를 입력해 주세요.");
                    console.error('아이디 중복', error);
                    // this.duplicateResult = null; // 오류 발생 시 초기화
                });
    },
    //이메일 입력 시 검증
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
                if (response.status===200) {
                    console.log(" 발송 완료")
                    alert("인증번호가 전송되었습니다 ");
                } 
            })
            .catch(error => {
                // alert("서버와 통신 중 오류가 발생했습니다.");
                alert("아이디가 입력되지 않아 이메일 발송에 실패했습니다.");
            });
    },
    // 서버로 인증 번호 확인 요청 보내기
     verifyCertificationNumber() {
            axios.post('http://localhost/project/api/v1/auth/check-certification', { 
              id : this.id,
              email : this.email,
              certificationNumber: this.certificationNumber 
            })
                .then(response => {
                    // 서버로부터 응답을 받았을 때 처리
                    if (response.status===200) {
                        alert("인증번호가 일치합니다.");
                    } 
                })
                .catch(error => {
                    console.error('인증번호가 불일치합니다.', error);
                    alert("인증번호가 일치하지 않습니다. 다시 확인해 주세요.");
                    // this.certificationNumber = null; // 오류 발생 시 초기화
                });
      },
    // toggleNavbar() {
    //   this.isNavbarOpen = !this.isNavbarOpen;
    // },
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
    // updateCheckboxVisibility() {
    //   // 체크박스 가시성 업데이트 로직 추가
    //   var inputValue = document.getElementById("allergy").value;
    //   this.showCheckboxContainer = inputValue.includes("배");
    // },
    //대충 넣은 빈값 확인
    checkEmpty(){
      if(!this.id){
        alert("아이디를 입력하세요.");
        return;
      }
      if(!this.password){
        alert("비밀번호를 입력하세요.");
        return;
      }
      if(!this.name){
        alert("이름을 입력하세요.");
        return;
      }
      if(!this.birth){
        alert("생년월일을 입력하세요.");
        return;
      }
      if(!this.address){
        alert("주소를 입력하세요.");
        return;
      }
      if(!this.phoneno){
        alert("휴대전화를 입력하세요.");
        return;
      }
      if(!this.email){
        alert("이메일을 입력하세요.");
        return;
      }
      if(!this.certificationNumber){
        alert("인증번호를 입력하세요.");
        return;
      }
      if(!this.question){
        alert("비밀번호 찾기 질문을 선택해주세요.");
        return;
      }
      if(!this.answer){
        alert("비밀번호 찾기 답변을 입력해주세요.");
        return;
      }
      if(!this.gender){
        alert("성별을 선택하세요.");
        return;
      }
      if(!this.disease){
        alert("질병을 입력하세요.");
        return;
      }
      if(!this.height){
        alert("키를 입력하세요.");
        return;
      }
      if(!this.weight){
        alert("몸무게를 입력하세요.");
        return;
      }
    },
    //회원가입 버튼 
    submitRegistration() {
      const url='http://localhost/project/api/v1/auth/sign-up'
      this.checkEmpty();
      this.form={
        "id":this.id,
        "password":this.password,
        "name":this.name,
        "birth":this.birth,
        "address":this.address,
        "phoneno":this.phoneno,
        "email":this.email,
        "question":this.question,
        "answer":this.answer,
        "gender":this.gender,
        "disease":this.disease,
        "height":this.height,
        "weight":this.weight,
        "certificationNumber":this.certificationNumber
      }
      axios.post(url,this.form)
      .then((res)=>{
        alert("회원가입이 완료되었습니다.")
        this.$router.push('/login');
      })
      .catch((err)=>{
        console.error("============"+err);
      })
    },
    cancel() {
      this.$router.push('/');
    },
  },

};

</script>
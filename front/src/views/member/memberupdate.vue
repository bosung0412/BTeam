<template>
  <div>
        <Navbar />
		<div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-3">
		<h3 class="display-5 text-white mb-2 animated slideInDown">{{userId}}님의 정보를 수정해주세요</h3>
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
                    <label for="Id" class="form-label">아이디</label>
									<div class="input-group">
										<input type="text" class="form-control" id="id" name="id" readonly v-model="userId">
									</div>
                    <label for="password" class="form-label mt-2">비밀번호</label>
                
                    <input type="password" class="form-control" id="password" name="password" v-model="member.password">

                    <label for="passwordchk" class="form-label mt-2">비밀번호 확인</label>
                    <input type="password" class="form-control" id="passwordchk" name="passwordchk" v-model="confirmPassword"  @input="validatePasswordForm">
                    <p v-if="errorMessage" style="color:red;margin: 0px;font-size: 1rem;">{{ errorMessage }}</p>

                    <label for="name" class="form-label mt-2">이름</label>
                    <input type="text" class="form-control" id="name" v-model="member.name">
                    <label for="address" class="form-label mt-2">주소</label>
                    <div class="input-group">
                      <input type="text" class="form-control" id="address" name="address" v-model="member.address" placeholder="주소를 입력하세요.">
                      <div class="input-group-append">
                        <button type="button" class="btn btn-success btnalls" @click="openAddressPopup" style="margin-bottom: 0px;">주소찾기</button>
                      </div>
                    </div>
                     <!-- 주소찾기 버튼 클릭 시 주소 검색 화면 -->
                    <div ref="wrap" :style="{ display: ishidden}">
                      <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" @click="closeAddress">
                    </div>
                    <div>
                      <label for="phoneNumber" class="form-label mt-2">휴대전화</label>
                    <input type="text" class="form-control" id="phoneNumber" maxlength="13" @input="formatPhoneNumber" v-model="member.phoneno">
                  </div>

                  <p class="fs-5 fw-bold" style="color: rgb(77, 183, 91);">추가 정보</p>
                  <div class="col-sm-6">
                    <label for="allergy" class="form-label mt-2">질병</label>
                    <input type="text" class="form-control border" id="allergy" name="allergy" v-model="member.disease">
                    </div>
                    <label for="height" class="form-label mt-2">키(cm)</label>
                    <input type="text" class="form-control border" id="height" name="height" v-model="member.height">
                    <label for="weight" class="form-label mt-2">몸무게(kg)</label>
                    <input type="text" class="form-control border" id="weight" name="weight" v-model="member.weight">
                  <div class="col-12 text-center mt-4">
                    <button @click="memberUpdate" type="button" class="btn btn-success mx-2 btnall">정보수정</button>
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
</div>
</template>
<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '@/components/Footer/Footer.vue';
import axios from 'axios';
export default {
  components: {
    Navbar,
    Footer,
  },
data(){
  return{
    member:{
      name:'',
	    address:'',
      phoneno:'',
      disease:'',
      height:'',
      weight:'',
      password:'',
    },
    userId:'',//id를 나타냄
    ishidden:'none',
      confirmPassword:'',
      errorMessage:'',
  }
},
created() {
  const token = this.$store.getters.getAuthToken;
  console.log("=======token: " + token); // 토큰값 가져옴
	
  const payloadBase64 = token.split('.')[1];
  const decodedToken = JSON.parse(atob(payloadBase64));
  this.userId = decodedToken.sub;

  axios.get(`http://localhost/project/api/v1/auth/getMember/${this.userId}`)
    .then((response) => {
      this.member = response.data;
    })
    .catch((err) => {
      console.error("========error!!!: ", err);
    });
},
methods:{
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
	  //패스워드 일치 여부 확인
	validatePasswordForm(){
      if(this.member.password!==this.confirmPassword){
        this.errorMessage = '패스워드가 일치하지 않습니다.';
        return false;
      }
      this.errorMessage='';
      return true;
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
    memberUpdate(){
      axios.patch(`http://localhost/project/api/v1/auth/update/${this.userId}`,this.member)
    .then(response=>{
      alert("회원 정보 수정이 완료되었습니다.");
      this.$router.push('/login');
		}).catch(error=>{
      alert("회원 정보 수정이 실패되었습니다.")
			console.log("진짜 ....");
		})
	},
	cancel() {
      this.$router.push('/');
    },
	},

  };

</script>
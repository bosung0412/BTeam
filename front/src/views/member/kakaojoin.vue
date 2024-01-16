<template>
    <div class="form" :class="activeForm">
    
      <br/>
      <div>
    </div>
      <div class="form-header">
        <img src='@/assets/img/kakao.png' @click="kakaoLogin" alt="카카오 로그인" style="cursor: pointer;"/>
        </div>
      <div class="form-elements">
        <div class="form-element" style="display: flex;">
          <input v-model="form.email" type="text" style="flex: 1; margin-right: 5px;"
            required disabled />
        </div>
  
             <div style="display: flex;" class="form-element" :disabled="Visible">
          <input type="text" style="flex: 1; margin-right: 5px;" maxlength="11" v-model="phone"
            placeholder="전화번호( - 빼고 입력)">
          <button v-show="phonenumcheck" @click="checkinfo"
            style="font-size : 13px; width: 80px; height:40px;">본인인증</button>
        </div>
  
        <div class="form-element">
          <input type="text" v-model="form.nickname" required disabled />
        </div>
        <div style="margin-top : 10px;">
          <label  class="input-file-wrapper">
            <div class="upload-button-text" style="text-align: left; color : grey;">{{ uploadButtonText }}</div>
            <input type="file" id="img-input-file" class="form-element" style="display :none;" @change="handleFileUpload" />
          </label>
        </div>
  <div class="form-element">
          <button id="submit-btn" @click="onSubmit()">카카오톡으로 회원가입 하기</button>
        </div>
      </div>
      <div class="button-wrapper">
            <router-link to="/" class="custom-link">돌아가기</router-link>
          </div>
        </div>
  
  </template>

<script>
export default {
  name: 'kakaojoin',
  data() {
    return {
      code: this.$route.query.code,
      email: '',
      pwd: '',
      nickname: '',
      phone: '',
      echeck: '',
      img: '',
      form: {
        email: '',
        nickname: '',
        phone: '',
        pwd: '',
        kakaotoken: '',
        message : ''
      },
      show: true,
      uploadButtonText: '프로필 사진 업로드',
      phonenumcheck :true
    };
  },
  created() {
      // code 안에 query로 받아온 값을 저장
      this.code = this.$route.query.code;
      console.log(this.code)
      this.getToken();
  },
  methods: {
    

    handleFileUpload() {
      this.uploadButtonText = '사진이 업로드 되었습니다';
    },

    klogin() { //아이디 저장이 되어 있으면 갈 곳 
      const self = this;
      const loginform = new FormData();
      loginform.append('email', self.form.email)
      loginform.append('pwd', self.form.pwd)
      self.$axios.post('http://localhost:8081/members/login', loginform)
      .then(function (res) { //결과 
          if (res.status == 200) { 
              sessionStorage.setItem('token', res.data.token)            
              sessionStorage.setItem('loginId', res.data.loginId)             
          const addform = new FormData();
          addform.append('email', self.form.email)
          addform.append('token', self.form.kakaotoken)
          console.log(self.form.kakaotoken)
          self.$axios.post('http://localhost:8081/tokensave', addform)
            .then(function (rep) {
              if (rep.status == 200) {
                location.href="/calendar"            
              } else {
                alert ('여기서 오류다~ ')
              }
            });
          }
          })
        },
    getToken() { //토큰 냅다 받아~~ 
      const self = this;
      self.$axios.get('http://localhost:8081/kakaologin/' + self.code)
        .then((res) => {
          console.log(res)
          if(res.status==200){
            if(res.data.message){
              alert(res.data.message);
              location.href = "/"
            } else {
          self.form.email = res.data.email;
          self.form.pwd = res.data.id;
          self.form.nickname = res.data.nickname;
          self.form.kakaotoken = res.data.accessToken;
            }
          self.$axios.get('http://localhost:8081/members/getKakaomember/' + self.form.email)
            .then(function (res) {
              if (res.status == 200) {
                console.log(res.data.flag)
                if (res.data.flag == false) {
                  console.log(res.data.flag)
                } else {
                  self.klogin()
                }
              }
            });
          }
        });
    },
     //본인인증 
     checkinfo() {
      const self = this;
      IMP.init("imp66001065");
      IMP.certification({
        pg: 'MIIiasTest',
        merchant_uid: 'merchant_' + new Date().getTime(),
        m_redirect_url: "http://localhost:8081/kakaojoin"
      }, function (rsp) {
        if (rsp.success) {
          console.log(rsp.imp_uid);
          console.log(rsp.merchant_uid);
          const data = {
            imp_uid: rsp.imp_uid,
            email: self.email
          };
          self.$axios.get("http://localhost:8081/members/certifications/redirect", { params: data })
          .then(function (res) {
              console.log('전화번호빼옴' + res.data.phone);
              console.log('입력한 이름' + self.phone)         
              if (res.data.phone != self.phone) {
                alert('입력하신 전화번호와 다릅니다');
                self.phone = '';
              } else {
                self.Visible = true; //전화번호 수정 못하게 막고 
              self.phonenumcheck = false; //본인인증 버튼 없애고 
              }
            });
        }
      })
    },
    onSubmit() {
      const self = this;
      const form = new FormData();
      
     if(self.phone == null || self.phone ==""){
      alert("휴대폰 본인 인증을 해주세요");
      return;

     } else {
        form.append('phone', self.phone);
      }
      form.append('email', self.form.email);
      form.append('nickname', self.form.nickname);
      form.append('pwd', self.form.pwd)
      form.append('id', 1)
      if (document.getElementById('img-input-file').value !== '') {
        const file = document.getElementById('img-input-file').files[0];
        form.append('f', file);
      }
      self.$axios.post('http://localhost:8081/members', form, { headers: { "Content-Type": "multipart/form-data" } })
        .then(function (res) {
          if (res.status === 200) {
            let dto = res.data.dto;
            console.log(dto);
            self.klogin();
          } else {
            alert('에러코드 :' + res.status);
          }
        })
        .catch(function (error) {
          alert('에러 :' + error);
        });
    },


  }
};
</script>
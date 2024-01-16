<template>
  <div>
    <!-- 네비게이션 바 시작 -->
    <Navbar /> 
    <!-- 네비게이션 바 끝 -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
  <div class="container text-center py-3">
    <h3 class="display-5 text-white mb-2 animated slideInDown">식단 추가</h3>
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
                  <div class="mb-3 mt-3">
                      <label for="name" class="form-label">식단 명:</label>
                      <input type="text" class="form-control" id="name" name="name" ref="name"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="weight" class="form-label">중량:</label>
                      <input type="text" class="form-control" id="weight"  name="weight" ref="weight"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="cal" class="form-label">총 칼로리:</label>
                      <input type="text" class="form-control" id="cal"  name="cal" ref="cal"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="carbo" class="form-label">탄수화물:</label>
                      <input type="text" class="form-control" id="carbo" name="carbo" ref="carbo"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="sugars" class="form-label">당류:</label>
                      <input type="text" class="form-control" id="sugars"  name="sugars" ref="sugars"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="fat" class="form-label">지방:</label>
                      <input type="text" class="form-control" id="fat" name="fat" ref="fat"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="protein" class="form-label">단백질:</label>
                      <input type="text" class="form-control" id="protein"  name="protein" ref="protein"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="fpotasium" class="form-label">칼륨:</label>
                      <input type="text" class="form-control" id="fpotasium" name="fpotasium" ref="fpotasium"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="calcium" class="form-label">칼슘:</label>
                      <input type="text" class="form-control" id="calcium"  name="calcium" ref="calcium"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="phosphorus" class="form-label">인:</label>
                      <input type="text" class="form-control" id="phosphorus" name="phosphorus" ref="phosphorus"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="sodium" class="form-label">나트륨:</label>
                      <input type="text" class="form-control" id="sodium"  name="sodium" ref="sodium"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="potassium" class="form-label">칼륨:</label>
                      <input type="text" class="form-control" id="potassium"  name="potassium" ref="potassium"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="magnesium" class="form-label">마그네슘:</label>
                      <input type="text" class="form-control" id="magnesium"  name="magnesium" ref="magnesium"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="iron" class="form-label">철:</label>
                      <input type="text" class="form-control" id="iron"  name="iron" ref="iron"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="zinc" class="form-label">아연:</label>
                      <input type="text" class="form-control" id="zinc"  name="zinc" ref="zinc"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="cholesterol" class="form-label">콜레스테롤:</label>
                      <input type="text" class="form-control" id="cholesterol"  name="cholesterol" ref="cholesterol"/>
                  </div>
                  <div class="mb-3 mt-3">
                      <label for="transfat" class="form-label">트랜스지방:</label>
                      <input type="text" class="form-control" id="transfat"  name="transfat" ref="transfat"/>
                  </div>
                  <!-- <div class="mb-3 mt-3">
                      <label for="fimage" class="form-label">식단 이미지:</label>
                      <input type="text" class="form-control" id="fimage"  name="fimage" />
                  </div> -->
                  <div class="information">
                      <p>식단 이미지를 업로드하세요.</p>
                      <input type="file" @change="selectFile" multiple accept="image/*" ref="fileRef" />
                  </div>
                  <div class="images" v-if="files.length > 0">
                      {{ files }}
                      <div v-for="file in files" :key="file.name" class="image">
                          <img :src="file.preview" alt="이미지" > {{ file.preview }} / {{ file.name }}
                      </div>
                  </div> 
                  <div class="col-12 text-center mt-4">
                  <button @click="sendData" type="button" class="btn btn-success mx-2 btnall">추가하기</button>
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
import axios from "axios";

export default {
  components: {
  Navbar,
  Footer,
},
  data() {
    return {
      isNavbarOpen: false,
      showCheckboxContainer: false,
      files: [],
      formData: new FormData(),
    };
  },
  methods: {
    toggleNavbar() {
      this.isNavbarOpen = !this.isNavbarOpen;
    },
    updateCheckboxVisibility() {
      // 체크박스 가시성 업데이트 로직 추가
      var inputValue = document.getElementById("allergy").value;
      this.showCheckboxContainer = inputValue.includes("배");
    },
    cancel() {
      this.$router.push('/recommendeddiet');
    },
    selectFile(event) {
      this.files = [];
      const selectedFiles = event.target.files;
      // for (let i = 0; i < selectedFiles.length; i++) {
      //     const file = selectedFiles[i];
      //     const preview = URL.createObjectURL(file);
      //     this.files.push({
      //         name: file.name,
      //         preview: preview,
      //     });
      //     this.formData.append('files', file); // 여러 파일을 배열로 전송
      // }
      for (let i = 0; i < selectedFiles.length; i++) {
        const file = selectedFiles[i];
        // 미리보기 URL 생성
        const preview = URL.createObjectURL(file);
        // 파일 정보와 미리보기 URL을 files 배열에 추가
        this.files.push({
          name: file.name,
          preview: preview,
        });
        // 업로드할 파일은 FormData에 추가
        this.formData.append('files', file);
      }
    },
    async fetchFiles() {
        console.log("fetchFiles!!!!!!!!!!!!!");
    },
    async sendData() {
        // formdata에 name, email을 추가한 후에 이미지도 미리보기에서 가져온다.
      this.formData.append("name", document.getElementById("name").value);
      this.formData.append("weight", document.getElementById("weight").value);
      this.formData.append("cal", document.getElementById("cal").value);
      this.formData.append("carbo", document.getElementById("carbo").value);
      this.formData.append("sugars", document.getElementById("sugars").value);
      this.formData.append("fat", document.getElementById("fat").value);
      this.formData.append("protein", document.getElementById("protein").value);
      this.formData.append("calcium", document.getElementById("calcium").value);
      this.formData.append("phosphorus", document.getElementById("phosphorus").value);
      this.formData.append("sodium", document.getElementById("sodium").value);
      this.formData.append("potassium", document.getElementById("potassium").value);
      this.formData.append("magnesium", document.getElementById("magnesium").value);
      this.formData.append("iron", document.getElementById("iron").value);
      this.formData.append("zinc", document.getElementById("zinc").value);
      this.formData.append("cholesterol", document.getElementById("cholesterol").value);
      this.formData.append("transfat", document.getElementById("transfat").value);

      // 업로드 처리하기
      axios.post("http://192.168.0.88/project/foodadd", this.formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(() => {
          // 업로드가 성공하면 추가한 파일 목록을 서버에서 가져올 수 있도록 fetchFiles 메서드 호출
          this.fetchFiles();
        })
        .catch((error) => {
          console.log(error.message);
        });
      },
    async fetchFiles(){ //파일이 업로드가 마칠때까지 대기 
          console.log("fetchFiles!!!!!!!!!!!!!")
    }
  },
};
</script>

<!-- <script>
  data(){
      //파일 배열 초기화
      return {
          msg:"FileUpForm 연습문제 예제",
          files: [],
      }
  },
  methods: {
      //change 이벤트를 통해서 호출될 selectFile 함수를 정의
      selectFile(event){
      //    console.log("동작확인")
      this.files = [];
      const formData = new FormData();
      const selectedFiles = event.target.files;
      for (let i = 0; i < selectedFiles.length; i++) {
        const file = selectedFiles[i];
        // 미리보기 URL 생성
        const preview = URL.createObjectURL(file);
        // 파일 정보와 미리보기 URL을 files 배열에 추가
        this.files.push({
          name: file.name,
          preview: preview,
        });
        // 업로드할 파일은 FormData에 추가
        formData.append('files', file);
      }
        console.log("=============>"+this.files);
        // post 업로드 처리하기 
        axios.post('http://192.168.0.21/springvue0103/files',formData,{
          headers:{'Content-Type':'multipart/form-data'},
        }).then(() => {
          // 업로드가 성공하면 추가한 파일 목록을 서버에서 가져올 수 있도록 fetchFiles 메서드 호출
          this.fetchFiles();
        })
        .catch((error)=>{
          console.log(error.message);
        })
      },
      async fetchFiles(){ //파일이 업로드가 마칠때까지 대기 
          console.log("fetchFiles!!!!!!!!!!!!!")
      }

  },
}

</script> -->
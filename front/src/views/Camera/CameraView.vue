<template>
	<div>
		<!-- Navbar Start -->
	   <Navbar/>
	
	   <!-- Page Header Start -->
	   <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
		  <div class="container text-center py-3">
			 <h3 class="display-5 text-white mb-2 animated slideInDown">카메라 촬영 페이지</h3>
		  </div>
	   </div>
	   <!-- Page Header End -->
		<div class="container-xxl py-5">
		  <div class="container mt-5">
			 <!-- 첫번째 행 시작 -->
			 <div class="row g-5 align-items-stretch maincol pb-4">
				<div class="col-lg-3 col-md-5 wow fadeInUp d-flex justify-content-center" data-wow-delay="0.1s">
				   주의사항 이미지가 여기 들어감
				   <!--<img class="img-fluid" data-wow-delay="0.1s" src="img/icon/cameraupload.png">-->
				</div>
				<div class="col-lg-6 col-md-7 wow fadeInUp" data-wow-delay="0.3s">
					<div v-show="hasResult" id="result" class="web-camera-container">
						<img id="img_res" ref="img_res" width="450" height="337.5"/>
						<!-- <canvas ref="canvas" width="640" height="480">
							<img id="img-res" width="640" height="480"/>
						</canvas> -->
					</div>

					<div id="app" class="web-camera-container">
						<!-- 카메라 열기닫기 -->
						<div class="camera-button">
							<!-- 버튼을 누르면 toggleCamera메소드 호출하여 카메라 전환-->
							<button v-if="!isMobile" type="button" class="button is-rounded" :class="{ 'is-primary': !isCameraOpen, 'is-danger': isCameraOpen}" @click="toggleCamera">
							<span v-if="!isCameraOpen">Open Camera</span>
							<span v-else>Close Camera</span>
							</button>
							<button v-else type="button" class="button is-rounded" @click="openNewActivity">
							<span>Open Camera app</span>
							</button>
						</div>


						<!-- 카메라가 열리고 로딩중이면 로딩이미지 -->
						<div v-if="isCameraOpen && isLoading" class="camera-loading">
							<ul class="loader-circle">
							<li></li>
							<li></li>
							<li></li>
							</ul>
						</div>
						<!-- 찍힐때 카메라 플래시 효과 -->
						<div v-show="isCameraOpen && !isLoading" class="camera-box" :class="{ 'flash': isShotPhoto }">
							<div class="camera-shutter" :class="{ 'flash': isShotPhoto }"></div>
							<!-- 실시간 카메라 스트림 비디오 엘리먼트 (isPhotoTaken이 false경우에만 표시) --> 
							<video ref="camera" width="450" height="337.5" autoplay></video>
							<!-- <video v-show="!isPhotoTaken" ref="camera" width="640" height="480" autoplay></video> -->
						</div>
						<!-- 카메라가 열려있고 로딩중이 아닌 경우, 사진버튼 -->
						<div v-show="isCameraOpen && !isLoading" class="camera-shoot">
							<!-- 사진찍는 버튼, takePhoto메소드 호출 -->
							<button type="button" class="button" id="sendingPhoto" @click="sendingToServer">
								<img src="https://img.icons8.com/material-outlined/50/000000/camera--v2.png" alt="camera-icon">
							</button>
							<!-- 식단을 등록하는 버튼, 웹 saveToDB_web메소드 호출 -->
							<button v-show="isCameraOpen && !isMobile" type="button" class="button" id="saveToDB_web" @click="saveToDB_web">
								<img src="../../assets/img/camera/icons8-save-64.png" alt="saveToDB-icon">
							</button>
							<!-- 식단을 등록하는 버튼, 안드로이드 saveToDB메소드 호출 -->
							<button v-show="isCameraOpen && isMobile" type="button" class="button" id="saveToDB" @click="saveToDB">
								<img src="../../assets/img/camera/icons8-database-of-an-android-smartphone-operating-system-36.png" alt="saveToDB-icon">
							</button>
						</div>
						<div v-show="isCameraOpen && !isLoading">
							사진 버튼을 클릭하여 사진을 찍으시고 저장 버튼을 클릭하여 식단을 등록하세요.
						</div>

					</div>


					<div class="row mb-3">
						<div class="col">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="breakfast">
								<label class="form-check-label" for="flexCheckDefault1">
									아침
								</label>
							</div>
						</div>
						<div class="col">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="lunch" checked>
								<label class="form-check-label" for="flexCheckChecked">
									점심
								</label>
							</div>
						</div>
						<div class="col">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="dinner">
								<label class="form-check-label" for="flexCheckChecked2">
									저녁
								</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="d-flex justify-content-center align-items-center">
							
							<a class="btn btn-success mx-2 btnall" href="calender.html">업로드</a>
						</div>
						<!-- 파일 업로드를 위한 input 추가 -->
						<input type="file" class="form-control mt-2" id="foodImage" name="foodImage" accept="image/*">
					</div>
					음식 분석 결과 추가 예정
				</div>
				<div class="col-lg-3 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
				   <div class="row g-5">
					  <div class="col-12 col-sm-6 col-lg-12">
						 <div class="border-start ps-4">
									
							<img class="img-fluid" src="../../assets/img/camera/userauth.png" style="width: 100px;height: 100px;">
							<h4 class="mb-3 maintext">사용 인증</h4>
							<span>대한민국에서 활용인증을 받은 프로그램입니다. 개인정보가 유출될 걱정은 없습니다.</span>
						 </div>
					  </div>
					  <div class="col-12 col-sm-6 col-lg-12">
						 <div class="border-start ps-4">
							<img class="img-fluid" src="../../assets/img/camera/userlove.png" style="width: 100px;height: 100px;">
							<h4 class="mb-3 maintext">다양한 사람</h4>
							<span>식단을 하고싶은사람, 다양한 음식의 칼로리를 알고싶은사람 등 많은사람들이 이용합니다.</span>
						 </div>
					  </div>
				   </div>
				</div>
			 </div>
			 <!-- 첫번째 행 끝 -->
			
			 <!-- 아침, 점심, 저녁 공간 시작-->
			 <div class="row g-5 align-items-stretch maincol mt-4 mb-5 pb-5" style="box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3);">
				<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
				   <div class="service-item rounded d-flex h-100">
					  <div class="service-text rounded p-5">
						 <div class="btn-square rounded-circle mx-auto mb-3">
							<img class="img-fluid" src="img/icon/icon-3.png" alt="Icon">
						 </div>
						 <h4 class="mb-3">Breakfast</h4>
						 <p class="mb-4">Calorie</p>
					  </div>
					  <div class="d-flex align-items-center">
						 아침식단
					  </div>
				   </div>
				</div>
				<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
				   <div class="service-item rounded d-flex h-100">
					  <div class="service-text rounded p-5">
						 <div class="btn-square rounded-circle mx-auto mb-3">
							<img class="img-fluid" src="img/icon/icon-3.png" alt="Icon">
						 </div>
						 <h4 class="mb-3">Lunch</h4>
						 <p class="mb-4">Calorie</p>
					  </div>
					  <div class="d-flex align-items-center">
						 점심식단
					  </div>
				   </div>
				</div>
				<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
				   <div class="service-item rounded d-flex h-100">
					  <div class="service-text rounded p-5">
						 <div class="btn-square rounded-circle mx-auto mb-3">
							<img class="img-fluid" src="img/icon/icon-3.png" alt="Icon">
						 </div>
						 <h4 class="mb-3">Dinner</h4>
						 <p class="mb-4">Calorie</p>
					  </div>
					  <div class="d-flex align-items-center">
						 저녁식단
					  </div>
				   </div>
				</div>
			 </div>
			 <!-- 아침, 점심, 저녁 공간 끝-->
			 <canvas v-show=false id="canvas" ref="canvas" width="450" height="337.5"></canvas>
		  </div>
	   </div>
	   <!-- Footer Start -->
	   <Footer /> 
	</div>

</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from "axios";

export default {
    components:{
        Navbar,
        Footer,
    },
    data() {
    return {
      isCameraOpen: false, // 카메라 열림닫힘
      isPhotoTaken: false, // 사진활영 여부
      isShotPhoto: false, // 촬영 시, 플래시 효과
      isLoading: false, // 카메라 로딩 상태
	  isMobile: /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) || /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.platform),
	  hasResult: false,	// 모델 예측 결과 존재 여부
	  stream: null, // 웹캠 스트림

    };
  },
  methods: {
    toggleCamera() { // 열기, 닫기 버튼클릭 시 호출 메소드
      if (this.isCameraOpen) {
        this.closeCamera();
      } else {
        this.openCamera();
      }
    },
    async openCamera() {
	if(typeof window.mobile !== 'undefined'){
		this.isMobile = true;		
	}
      this.isLoading = true;
      try {
        navigator.mediaDevices.getUserMedia({ audio: false, video: true }) // 사용자미디어디바이스 video허용 // 사용자의 미디어 디바이스 엑세스
		.then((stream) => {
			this.stream = stream;
	        this.$refs.camera.srcObject = stream; // 성공시 할당되어 화면에 출력
		})
      } catch (error) {	// 오류 시
        alert("카메라 권한 오류.");
      } finally {
        this.isLoading = false;
        this.isCameraOpen = true;
      }
    },
	sendingToServer(){
		this.isPhotoTaken = true; // ui에 사진 찍힌 상태 표시(캡쳐기능)
		this.isShotPhoto = true; // 찍을 때 플래시
		// 플래시 효과 일정시간 실행 후, false로 전환
		setTimeout(() => {
			this.isShotPhoto = false;
		}, 50);

		const context = this.$refs.canvas.getContext('2d');
		// context.drawImage(this.$refs.camera, 0, 0, 640, 480);
		context.drawImage(this.$refs.camera, 0, 0, 450, 337.5);
		const canvas = this.$refs.canvas.toDataURL("image/jpeg")

		// Send the video to the server
		let formData = new FormData();
		formData.append("org_img", canvas);
		formData.append("id", "1");
		
		axios.post("http://192.168.0.115:9000/food_ai/detectFoodWeb", formData)
		.then((res) => {
			console.log("전송이 성공적으로 완료")
			console.log(res.data);
			this.$refs.img_res.src = res.data.diet_img_pred;
			this.hasResult = true;
		})
		.catch((error) => {
			console.log("Error sending stream:", error);
		});
	},
    closeCamera() {	// 카메라 닫을 때 메소드
      const tracks = this.$refs.camera.srcObject.getTracks();
      tracks.forEach(track => track.stop()); // 카메라 스트림의 트랙을 중지
	  // 관련 플래그 초기화
      this.isCameraOpen = false;
      this.isPhotoTaken = false;
      this.isShotPhoto = false;
    },
	
	openNewActivity() {
      // 안드로이드의 openNewActivity 메소드 호출
      if (typeof window.mobile !== 'undefined') {
        window.mobile.openNewActivity();
      } else {
        console.error('Android object is not defined.');
      }
    },
	saveToDB() {
        // 안드로이드의 saveToDB 메소드 호출
        if (typeof window.mobile !== 'undefined') {
			window.mobile.saveToDB();
        } else {
            console.error('Android object is not defined.');
        }
    },	
	saveToDB_web() {

    },		
  },
}
</script>
<style scoped>
body {
  display: flex;
  justify-content: center;
}

.web-camera-container {
  margin-top: 2rem;
  margin-bottom: 2rem;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 500px;
}

.web-camera-container .camera-button {
  margin-bottom: 2rem;
}

.web-camera-container .camera-box .camera-shutter {
  opacity: 0;
  width: 450px;
  height: 337.5px;
  background-color: #fff;
  position: absolute;
}

.web-camera-container .camera-box .camera-shutter.flash {
  opacity: 1;
}

.web-camera-container .camera-shoot {
  margin: 1rem 0;
  display: flex;
  position: relative;
}

.web-camera-container .camera-shoot button {
  height: 60px;
  width: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 100%;
}

.web-camera-container .camera-shoot button img {
  height: 35px;
  object-fit: cover;
}

.web-camera-container .camera-loading {
  overflow: hidden;
  height: 100%;
  position: absolute;
  width: 100%;
  min-height: 150px;
  margin: 3rem 0 0 -1.2rem;
}

.web-camera-container .camera-loading ul {
  height: 100%;
  position: absolute;
  width: 100%;
  z-index: 999999;
  margin: 0;
}

.web-camera-container .camera-loading .loader-circle {
  display: block;
  height: 14px;
  margin: 0 auto;
  top: 50%;
  left: 100%;
  transform: translateY(-50%);
  transform: translateX(-50%);
  position: absolute;
  width: 100%;
  padding: 0;
}
.web-camera-container .camera-loading .loader-circle li {
  display: block;
  float: left;
  width: 10px;
  height: 10px;
  line-height: 10px;
  padding: 0;
  position: relative;
  margin: 0 0 0 4px;
  background: #999;
  animation: preload 1s infinite;
  top: -50%;
  border-radius: 100%;
}
.web-camera-container .camera-loading .loader-circle li:nth-child(2) {
  animation-delay: .2s;
}
.web-camera-container .camera-loading .loader-circle li:nth-child(3) {
  animation-delay: .4s;
}
.app-camera-button {
  display: none;
}
@keyframes preload {
  0% {
    opacity: 1;
  }
  50% {
    opacity: .4;
  }
  100% {
    opacity: 1;
  }
}
</style>
<style scoped>  
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .web-camera-container {
    margin-top: 2rem;
    margin-bottom: 2rem;
    padding: 2rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 1px solid #ccc;
    border-radius: 4px;
    width: 350px;
  }

  
}

</style>
<template>
	<div>
		<div v-if="isLoadingImg" class="loading-container">
			<Fade-loader />
			<div class="loading">
			<ring-loader :loading="loading" :color="color" :size="size"></ring-loader>
		</div>
	</div>

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
				<div class="col-lg-3 col-md-5 d-flex justify-content-center" data-wow-delay="0.1s">
					<div class="border-start ps-4">
					<img class="img-fluid" src="../../assets/img/camera/icons8-caution-sign-64.png" style="width: 100px;height: 100px;">
					<h4 class="mb-3 maintext">주의사항</h4>
					<span>1. 웹캠과 이미지파일 업로드를 사용하여 AI음식탐지모델 서버에 이미지를 전송할 수 있습니다.</span>
					<br>
					<span>2. 이미지를 전송한 후 음식 탐지 결과를 확인해주세요.</span>
					<br>
					<span>3. 음식을 탐지한 후 아래의 음식 사진을 클릭하여 세부 영양 정보를 확인할 수 있습니다.</span>
					<br>
					<span>4. 음식을 탐지한 후 오른쪽 저장 버튼을 클릭하여 DB에 저장이 가능합니다.</span>
					</div>
				</div>
				<div class="col-lg-6 col-md-7 wow fadeInUp" data-wow-delay="0.3s">
					<div v-show="hasResult_webcam" id="result" class="web-camera-container">
						<img id="img_res" ref="img_res" width="450" height="337.5"/>
					</div>

					<div id="app" class="web-camera-container">
						<!-- 카메라 열기닫기 -->
						<div class="camera-button">
							<!-- 버튼을 누르면 toggleCamera메소드 호출하여 카메라 전환-->
							<button v-if="!isMobile" type="button" class="button is-rounded btn btn-success btnall" :class="{ 'is-primary': !isCameraOpen, 'is-danger': isCameraOpen}" @click="toggleCamera">
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
							<button v-show="isCameraOpen && !isMobile && hasResult_webcam" type="button" class="button" id="saveToDB_web" @click="saveToDB_web">
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

					<div class="row">
						<div class="file-upload-label d-flex justify-content-center align-items-center">
							<button class="is-rounded btn btn-success mx-2 btnall">식단 이미지 파일 업로드</button>
						</div>

						<div v-show="hasResult_upload" id="result" class="web-camera-container">
							<img id="img_res_upload" ref="img_res_upload" width="450" height="337.5"/>
						</div>

						<!-- 파일 업로드를 위한 input 추가 -->
						<div class="d-flex justify-content-center align-items-center">
							<input class="form-control input-lg mt-2" type="file" ref="uploadFile" id="foodImage" name="foodImage" accept="image/*" @change="fileSelect_upload"/>
						</div>

						<div class="file-upload d-flex justify-content-center align-items-center mt-3 mb-3">
							<!-- 이미지를 업로드하는 버튼, uploadToServer_web 메소드 호출 -->
							<button type="button" class="button" id="uploadToServer_web" @click="uploadToServer_web">
								<img src="../../assets/img/camera/icons8-upload.gif" alt="uploadToServer_web-icon">
							</button>
							<!-- 식단을 등록하는 버튼, uploadToDB_web 메소드 호출 -->
							<button v-show="hasUpload" type="button" class="button" id="uploadToDB_web" @click="uploadToDB_web">
								<img src="../../assets/img/camera/icons8-save-64.png" alt="uploadToDB_web-icon">
							</button>
						</div>
					</div>

					<div v-show="hasFoodInfo">
						<div class="row food-activity activity-image">
							<div class="foods align-items-center">
								<template v-for="(info, index) in foodInfo" :key="index">
									<button type="button" :value="index" class="btn button btn-success" @click="selectFood($event)"><img :src="info.img" style="--i: 3" alt="" /></button>
								</template>
							</div>
						</div>
						<div class="food-activity activity-totalCal">
							<div class="foods justify-content-center align-items-center">
								<h3>총 칼로리 : {{totalCal}}kcal</h3>
							</div>
						</div>

						<div class="row food-activity activity-info">
							<div v-show="isSelectFoods" id="food_wrapper">
								<div class="food_item">
									<div class="food_text">1. 음식명</div>
									<div class="food_detail">{{name}}</div>
									<div class="food_text">2. 중량</div>
									<div class="food_detail">{{weight}}g</div>            
									<div class="food_text">3. 칼로리</div>
									<div class="food_detail">{{cal}}kcal</div>            
									<div class="food_text">4. 탄수화물</div>
									<div class="food_detail">{{carbo}}g</div>            
									<div class="food_text">5. 당류</div>
									<div class="food_detail">{{sugars}}g</div>            
									<div class="food_text">6. 지방</div>
									<div class="food_detail">{{fat}}g</div>            
									<div class="food_text">7. 단백질</div>
									<div class="food_detail">{{protein}}g</div>            
									<div class="food_text">8. 칼슘</div>
									<div class="food_detail">{{calcium}}mg</div>            
									<div class="food_text">9. 인</div>
									<div class="food_detail">{{phosphorus}}mg</div>            
									<div class="food_text">10. 나트륨</div>
									<div class="food_detail">{{sodium}}mg</div>            
									<div class="food_text">11. 칼륨</div>
									<div class="food_detail">{{potassium}}mg</div>            
									<div class="food_text">12. 마그네슘</div>
									<div class="food_detail">{{magnesium}}mg</div>            
									<div class="food_text">13. 철</div>
									<div class="food_detail">{{iron}}mg</div>            
									<div class="food_text">14. 아연</div>
									<div class="food_detail">{{zinc}}mg</div>            
									<div class="food_text">15. 콜레스테롤</div>
									<div class="food_detail">{{cholesterol}}mg</div>            
									<div class="food_text">16. 트랜스지방</div>
									<div class="food_detail">{{transfat}}g</div>            
								</div>
							</div>       
						</div>
					</div>
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
import store from '@/store/index.js';
import RingLoader from 'vue-spinner/src/RingLoader.vue'
import Swal from 'sweetalert2'


export default {
    components:{
        Navbar,
        Footer,
		RingLoader,
		// DotLoader,
    },
    data() {
    return {
      isCameraOpen: false, // 카메라 열림닫힘
      isPhotoTaken: false, // 사진활영 여부
      isShotPhoto: false, // 촬영 시, 플래시 효과
      isLoading: false, // 카메라 로딩 상태
	  isLoadingImg: false,
	  isMobile: /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) || /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.platform),
	  uploadFile: null,		// upload 이미지 파일
	  imagePreview: null,	// upload 이미지 프리뷰 URL
	  hasResult_webcam: false,	// webcam을 사용한 모델 예측 결과 존재 여부
	  hasResult_upload: false,	// upload를 사용한 모델 예측 결과 존재 여부
	  hasUpload: false,		// upload 버튼X
	  stream: null, // 웹캠 스트림
	  color: '#71CAA2',
	  size: '300px',
	  user_id: null,	// 사용자 ID 정보
	  foodInfo: null,	// 영양정보 Json
	  totalCal: null,	// 총칼로리
	  hasFoodInfo: false,	// 영양정보 존재 여부
	  isSelectFoods: false,	// 음식 선택 여부
	  name: null,
	  weight: null,
	  cal: null,
	  carbo: null,
	  sugars: null,
	  fat: null,
	  protein: null,
	  calcium: null,
	  phosphorus: null,
	  sodium: null,
	  potassium: null,
	  magnesium: null,
	  iron: null,
	  zinc: null,
	  cholesterol: null,
	  transfat: null,
    };
  },
  created() {
    //메인 컴포넌트를 렌더링하면서 토큰체크
    let token = store.state.authToken;

	if (token === null) {
      this.$router.push({name: 'login'}).catch(() => {});
    } else {
		const payloadBase64 = token.split('.')[1];
		const decodedPayload = JSON.parse(atob(payloadBase64));
		this.user_id = decodedPayload['sub'];
	}
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
	async sendingToServer(){
		this.isPhotoTaken = true; // ui에 사진 찍힌 상태 표시(캡쳐기능)
		this.isShotPhoto = true; // 찍을 때 플래시
		// 플래시 효과 일정시간 실행 후, false로 전환
		setTimeout(() => {
			this.isShotPhoto = false;
		}, 50);

		const context = this.$refs.canvas.getContext('2d');
		context.drawImage(this.$refs.camera, 0, 0, 450, 337.5);
		const canvas = this.$refs.canvas.toDataURL("image/jpeg")

		// Send the image to the server
		let formData = new FormData();
		formData.append("org_img", canvas);
		formData.append("user_id", this.user_id);
		try {
        	this.isLoadingImg = true;
			await axios.post("http://192.168.0.8:9000/food_ai/detectFoodWeb", formData)
			.then((res) => {
				console.log("전송이 성공적으로 완료")
				console.log(res.data);
				this.$refs.img_res.src = res.data.diet_img_pred;
				this.foodInfo = res.data.foodInfo;
				this.totalCal = res.data.totalCal;
				this.showFoods();
				this.hasResult_webcam = true;
			})
			.catch((error) => {
				console.log("Error sending image:", error);
			});
		} catch (err) {
			console.log("Error sending image:", error);
		} finally {
			this.isLoadingImg = false;
		}
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
			let formData = new FormData();
		formData.append("user_id", this.user_id);
		formData.append("meal_time", (new Date()).toString().slice(16,21));
		try {
			this.isLoadingImg = true;
			axios.post("http://192.168.0.8:9000/food_ai/detectFoodWeb_save", formData)
			.then((res) => {
				this.isLoadingImg = false;
				if (res.data.res_code === "1"){
					Swal.fire({
						position: "top-end",
						icon: "success",
						title: res.data.text,
						showConfirmButton: false,
						timer: 1500
					});
				} else {
					Swal.fire({
						icon: "question",
						title: "식단 등록 실패",
						footer: "원인 : " + res.data.text,
					});
				}
			})
			.catch((error) => {
				this.isLoadingImg = false;
				Swal.fire({
					icon: "error",
					title: "식단 등록 실패",
					footer: "원인 : " + error
				});
			});
		} catch (err) {
			this.isLoadingImg = false;
			Swal.fire({
				icon: "error",
				title: "식단 등록 실패",
				footer: "원인 : " + err
			});
		} finally {
			this.isLoadingImg = false;
		}
        } else {
            console.error('Android object is not defined.');
        }
    },	
	saveToDB_web() {
		let formData = new FormData();
		formData.append("user_id", this.user_id);
		formData.append("meal_time", (new Date()).toString().slice(16,21));
		try {
			this.isLoadingImg = true;
			axios.post("http://192.168.0.8:9000/food_ai/detectFoodWeb_save", formData)
			.then((res) => {
				this.isLoadingImg = false;
				if (res.data.res_code === "1"){
					Swal.fire({
						position: "top-end",
						icon: "success",
						title: res.data.text,
						showConfirmButton: false,
						timer: 1500
					});
				} else {
					Swal.fire({
						icon: "question",
						title: "식단 등록 실패",
						footer: "원인 : " + res.data.text,
					});
				}
			})
			.catch((error) => {
				this.isLoadingImg = false;
				Swal.fire({
					icon: "error",
					title: "식단 등록 실패",
					footer: "원인 : " + error
				});
			});
		} catch (err) {
			this.isLoadingImg = false;
			Swal.fire({
				icon: "error",
				title: "식단 등록 실패",
				footer: "원인 : " + err
			});
		} finally {
			this.isLoadingImg = false;
		}
    },
	fileSelect_upload(){
		this.uploadFile = this.$refs.uploadFile.files[0]; // 사용자가 올린 이미지
		if(this.uploadFile !== undefined){
			// URL.createObjectURL로 사용자가 올린 이미지를 URL로 만들어서 화면에 표시할 수 있게 한다. img 태그의 src값에 바인딩해준다
			this.imagePreview = URL.createObjectURL(this.uploadFile);
			this.$refs.img_res_upload.src = this.imagePreview;
			this.hasResult_upload = true;
		} else {
			this.hasUpload = false;
			this.hasResult_upload = false;
		}
	},
	async uploadToServer_web(){
		if(this.uploadFile !== null){
			let formData = new FormData();
			formData.append("mfile", this.uploadFile);
			formData.append("user_id", this.user_id);
			try {
				this.isLoadingImg = true;
				await axios.post("http://192.168.0.8:9000/food_ai/detectFoodWebUpload", formData, {headers:{'Content-Type':'multipart/form-data'}})
				.then((res) => {
					console.log("전송이 성공적으로 완료")
					console.log(res.data);
					this.$refs.img_res_upload.src = res.data.diet_img_upload_pred;
					this.foodInfo = res.data.foodInfo;
					this.totalCal = res.data.totalCal;
					this.showFoods();

					this.hasResult_upload = true;
					this.hasUpload = true;
				})
				.catch((error) => {
					console.log("Error sending image:", error);
				});
			this.isLoadingImg = false;
			} catch (err) {
				this.isLoadingImg = false;
			} finally {
				this.isLoadingImg = false;
			}
		} else {
			Swal.fire({
				icon: "question",
				title: "이미지 전송 실패",
				footer: "원인 : " + "먼저 업로드할 이미지를 선택해서 올려주세요",
			});
		}
	},
	uploadToDB_web(){
		let formData = new FormData();
		formData.append("user_id", this.user_id);
		formData.append("meal_time", (new Date()).toString().slice(16,21));
		try {
			this.isLoadingImg = true;
			axios.post("http://192.168.0.8:9000/food_ai/detectFoodWebUpload_save", formData)
			.then((res) => {
				this.isLoadingImg = false;
				if (res.data.res_code === "1"){
					Swal.fire({
						position: "top-end",
						icon: "success",
						title: res.data.text,
						showConfirmButton: false,
						timer: 1500
					});
				} else {
					Swal.fire({
						icon: "question",
						title: "식단 등록 실패",
						footer: "원인 : " + res.data.text,
					});
				}
			})
			.catch((error) => {
				this.isLoadingImg = false;
				Swal.fire({
					icon: "error",
					title: "식단 등록 실패",
					footer: "원인 : " + error
				});
			});
		} catch (err) {
			this.isLoadingImg = false;
			Swal.fire({
				icon: "error",
				title: "식단 등록 실패",
				footer: "원인 : " + err
			});
		} finally {
			this.isLoadingImg = false;
		}
	},
	showFoods(){
		this.hasFoodInfo = true;
	},
	selectFood(event){
		let idx = Number(event.currentTarget.value);
		this.name = this.foodInfo[idx].name;
		this.weight = this.foodInfo[idx].weight;
		this.cal = this.foodInfo[idx].cal;
		this.carbo = this.foodInfo[idx].carbo;
		this.sugars = this.foodInfo[idx].sugars;
		this.fat = this.foodInfo[idx].fat;
		this.protein = this.foodInfo[idx].protein;
		this.calcium = this.foodInfo[idx].calcium;
		this.phosphorus = this.foodInfo[idx].phosphorus;
		this.sodium = this.foodInfo[idx].sodium;
		this.potassium = this.foodInfo[idx].potassium;
		this.magnesium = this.foodInfo[idx].magnesium;
		this.iron = this.foodInfo[idx].iron;
		this.zinc = this.foodInfo[idx].zinc;
		this.cholesterol = this.foodInfo[idx].cholesterol;
		this.transfat = this.foodInfo[idx].transfat;
		this.isSelectFoods = true;
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

	.web-camera-container .camera-shoot button, .file-upload button {
	height: 60px;
	width: 60px;
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 100%;
	}

	.web-camera-container .camera-shoot button img, .file-upload button img {
	height: 35px;
	object-fit: cover;
	}

	.file-upload-label {
		pointer-events: none;
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
<style>
	.loading {
		z-index: 9999;
		position: fixed;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
</style>


<style scoped>

	.food-activity {
		/* display: grid;
		grid-template-columns: 15% 60% 25%; */
		align-items: center;
		border-radius: 14px;
		margin-bottom: 5px;
		color: #484d53;
		box-shadow: rgba(0, 0, 0, 0.16) 0px 4px 8px;
	}

	.activity-image {
		background-color: rgb(124, 136, 224, 0.5);
		background-image: linear-gradient(
			240deg,
			rgb(124, 136, 224) 0%,
			#c3f4fc 100%
		);
	}

	.activity-info {
		background-color: #ecfcc376;
		background-image: linear-gradient(240deg, #97e7d1 0%, #ecfcc3 100%);
	}

	.activity-totalCal {
		background-color: #e6a7c3b5;
		background-image: linear-gradient(240deg, #fc8ebe 0%, #fce5c3 100%);
	}

	.activity {
		border-left: 3px solid #484d53;
	}

	.foods {
		display: flex;
		margin-left: 20px;
	}

	.foods img {
		width: 70px;
		height: 70px;
		border-radius: 50%;
		z-index: calc(2 * var(--i));
		margin-left: -13px;
		margin-top: -8px;
		box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 3px;
	}

	.foods button {
		width: 70px;
		height: 70px;
		border-radius: 50%;
		z-index: calc(2 * var(--i));
		margin-left: -10px;
		box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 3px;
	}


	.activity h2 {
		margin-left: 10px;
		font-size: 1.25rem;
		font-weight: 600;
		border-radius: 12px;
	}

	.btn {
		display: block;
		font-size: 1.1rem;
		font-weight: 500;
		outline: none;
		text-decoration: none;
		color: #484b57;
		background: rgba(255, 255, 255, 0.9);
		box-shadow: 0 6px 30px rgba(0, 0, 0, 0.1);
		border: 1px solid rgba(255, 255, 255, 0.3);
		border-radius: 25px;
		cursor: pointer;
	}
	.btn:hover{
		background: #3d7c59;
		color: white;
	}

	.btn:hover,
	.btn:focus,
	.btn:active,
	.btn:visited {
		transition-timing-function: cubic-bezier(0.6, 4, 0.3, 0.8);
		animation: gelatine 0.5s 1;
	}

	@keyframes gelatine {
	0%,
	100% {
		transform: scale(1, 1);
	}
	25% {
		transform: scale(0.9, 1.1);
	}
	50% {
		transform: scale(1.1, 0.9);
	}
	75% {
		transform: scale(0.95, 1.05);
	}
}

</style>


<style scoped>
        #food_wrapper {            
            margin: 2rem auto;
            color: #222;
            width: 80%;
            position: relative;
            box-sizing: border-box;
            background: rgb(189, 218, 204);
        }
        .food_item {
            position: relative;
            box-sizing: border-box;
            border-top: 1px solid #ccc;            
            vertical-align: center;
            transition: backgroundColor 0.1s;
            width: 80%;
            background: #f5f5f5;
            color: #000000
        }
        .food_text {          
            display: inline-block;
            padding: 1rem;
			font-weight: 700;                             
            box-sizing: border-box;
            font-size: 1rem;
            background: white;            
            position: relative;
            transition: backgroundColor 0.2s;
        }

        .food_detail {
            vertical-align: top;
            display: inline-block;
			font-weight: 700;
            padding: 1rem;
            color: #000000;
            font-size: 1rem;
            box-sizing: border-box;
            border-left: 0px solid #ddd;           
            height: 100%;
            position: relative;                                    
        }
        
        #food_wrapper {
            width: 50%;                
        }
        .food_item {
            width: 100%;
        }
        .food_text {
            width: 50%;
        }
        .food_detail {
            width: 50%;
        }
        
        @media (min-width: 1081px) {
            body {overflow-x: hidden;}
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
        
        @media (max-width: 1080px) {
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
        @media (max-width: 936px) {
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
        @media (max-width: 864px) {
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
        @media (max-width: 792px) {
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
        @media (max-width: 720px) {
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
        @media (max-width: 648px) {
            #food_wrapper {
                box-shadow: 0px 0px 10px 0px #787878;
            }
            .food_text {
                font-size: 1rem;
            }
            .food_detail {}
        }    
</style>
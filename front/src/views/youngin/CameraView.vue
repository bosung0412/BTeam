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
			<div class="row g-5 align-items-stretch maincol pb-4">
				<div class="col-lg-3 col-md-5 wow fadeInUp d-flex justify-content-center" data-wow-delay="0.1s">
					주의사항 이미지가 여기 들어감
					<!--<img class="img-fluid" data-wow-delay="0.1s" src="img/icon/cameraupload.png">-->
				</div>
				<div class="col-lg-6 col-md-7 wow fadeInUp" data-wow-delay="0.3s">
					<div class="row">
						<!-- 웹캠 촬영을 위한 비디오 엘리먼트 추가 -->
						<div id="webcamContainer" style="position: relative;" class="mb-3">
							<video id="webcam" width="100%" height="auto" autoplay
								style="border: 2px solid #ccc; border-radius: 8px;"></video>
						</div>
					</div>
					<div class="row">
						<div class="d-flex justify-content-center align-items-center">
							<a class="btn btn-success mx-2 btnall" href="#">촬영</a>
						</div>
					</div>
					<div class="row mb-3">
						<div class="col">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault1">
								<label class="form-check-label" for="flexCheckDefault1">
									아침
								</label>
							</div>
						</div>
						<div class="col">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
								<label class="form-check-label" for="flexCheckChecked">
									점심
								</label>
							</div>
						</div>
						<div class="col">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked2">
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
		</div>
	</div>
	<!-- Footer Start -->
	<Footer /> 
</div>
	<!-- Copyright End -->
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
export default {
    components:{
        Navbar,
        Footer,
    },
    data() {
      return {
        targetWeight: null,
      };
    }
}
</script>
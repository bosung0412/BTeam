<template>
    <div>
    <!-- Navbar Start -->
	<Navbar/>

	<!-- Page Header Start -->
	<div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
		<div class="container text-center py-3">
			<h3 class="display-5 text-white mb-2 animated slideInDown">식재료 구매</h3>
		</div>
	</div>
	<!-- Page Header End -->
  	<!-- Projects Start -->
	<div class="container-xxl py-5">
		<div class="container">
			<div class="row wow fadeInUp" data-wow-delay="0.3s">
				<div class="col-12 text-center">
					<ul class="list-inline rounded mb-5" id="portfolio-flters">
						<li class="mx-2 active" data-filter="*">전체</li>
						<li class="mx-2" data-filter=".first">1</li>
						<li class="mx-2" data-filter=".second">2</li>
						<li class="mx-2" data-filter=".third">3</li>
					</ul>
				</div>
			</div>
			<div class="row g-4 portfolio-container">
				<div class="col-lg-4 col-md-6 portfolio-item first wow fadeInUp" data-wow-delay="0.1s">
					<div class="portfolio-inner rounded">
						<img class="img-fluid" src="img/service-1.jpg" alt="">
						<div class="portfolio-text">

							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="img/service-1.jpg"
									data-lightbox="portfolio">
									<!--<i class="fa fa-eye"></i>--></a>
								<!-- <a class="btn btn-lg-square rounded-circle mx-2" href=""><i class="fa fa-link"></i></a>-->
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 portfolio-item second wow fadeInUp" data-wow-delay="0.3s">
					<div class="portfolio-inner rounded">
						<img class="img-fluid" src="img/service-2.jpg" alt="">
						<div class="portfolio-text">
							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="img/service-1.jpg"
									data-lightbox="portfolio">
									<!--<i class="fa fa-eye"></i>--></a>
								<!-- <a class="btn btn-lg-square rounded-circle mx-2" href=""><i class="fa fa-link"></i></a>-->
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 portfolio-item first wow fadeInUp" data-wow-delay="0.5s">
					<div class="portfolio-inner rounded">
						<img class="img-fluid" src="img/service-3.jpg" alt="">
						<div class="portfolio-text">

							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="img/service-3.jpg"
									data-lightbox="portfolio"></a>
								<!--  <a class="btn btn-lg-square rounded-circle mx-2" href=""><i class="fa fa-link"></i></a>-->
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 portfolio-item second wow fadeInUp" data-wow-delay="0.1s">
					<div class="portfolio-inner rounded">
						<img class="img-fluid" src="img/service-4.jpg" alt="">
						<div class="portfolio-text">
							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="img/service-1.jpg"
									data-lightbox="portfolio">
									<!--<i class="fa fa-eye"></i>--></a>
								<!-- <a class="btn btn-lg-square rounded-circle mx-2" href=""><i class="fa fa-link"></i></a>-->
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 portfolio-item first wow fadeInUp" data-wow-delay="0.3s">
					<div class="portfolio-inner rounded">
						<img class="img-fluid" src="img/service-5.jpg" alt="">
						<div class="portfolio-text">
							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="img/service-1.jpg"
									data-lightbox="portfolio">
									<!--<i class="fa fa-eye"></i>--></a>
								<!-- <a class="btn btn-lg-square rounded-circle mx-2" href=""><i class="fa fa-link"></i></a>-->
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 portfolio-item second wow fadeInUp" data-wow-delay="0.5s">
					<div class="portfolio-inner rounded">
						<img class="img-fluid" src="img/service-6.jpg" alt="">
						<div class="portfolio-text">
							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="img/service-1.jpg"
									data-lightbox="portfolio">
									<!--<i class="fa fa-eye"></i>--></a>
								<!-- <a class="btn btn-lg-square rounded-circle mx-2" href=""><i class="fa fa-link"></i></a>-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Projects End -->
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
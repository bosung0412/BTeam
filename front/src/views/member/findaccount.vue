<template>
    <div>
		<Navbar /> 
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
    <div class="container text-center py-3">
      <h3 class="display-5 text-white mb-2 animated slideInDown">아이디 or 비밀번호 찾기</h3>
    </div>
  </div>
  <div class="container-fluid py-5">
		<div class="container text-center">

			<div class="row justify-content-center">
				<div class="col-lg-7">
					<div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
						<div class="row">
							<h3>원하는 서비스를 선택하세요.</h3>
						</div>
				<!--		<div class="col-12 text-center">
						</div>-->
						<div class="container my-5 text-center">
							<button @click="findIdGo" type="button"
								class="btn btn-primary mx-2 btnall">아이디 찾기</button>
							<!-- <button @click="findPassGo" type="button"
								class="btn btn-primary mx-2 btnall">비밀번호 찾기</button> -->
							<!-- <button onclick="window.location.href='findid.html'" type="button"
								class="btn btn-primary mx-2 btnall">아이디 찾기</button>
							<button onclick="window.location.href='findpassword.html'" type="button"
								class="btn btn-primary mx-2 btnall">비밀번호 찾기</button> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
      <Footer />
    </div>
  </template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '@/components/Footer/Footer.vue';
export default {
  components: {
    Navbar,
    Footer,
  },
  data(){
	return{

	};
  },
  methods: {
	findIdGo(){
		this.$router.push('/findid');
	},
	findPassGo(){
		this.$router.push('/findpassword');
	}
  },

};
</script>
<template>
     <section class="u-clearfix u-image u-shading u-section-1" id="sec-오전 1:55 2024-01-053fd3" data-image-width="250" data-image-height="168">
      <div class="u-clearfix u-sheet u-sheet-1">
        <h1 class="u-align-center u-text u-text-body-color u-text-default u-text-1">냉장고든램지</h1>
        <h3 class="u-align-center u-text u-text-body-color u-text-default u-text-2">AI와 함께 음식 칼로리를 쉽게 알 수 있어요</h3>
        <button @click="NextGo" class="u-border-none u-btn u-button-style u-custom-color-3 u-hover-custom-color-5 u-radius u-btn-1" data-animation-name="customAnimationIn" data-animation-duration="1000" data-animation-direction="">Next&nbsp;</button>
      </div>
      <!-- Copyright Start -->
	<div class="container-fluid copyright py-4">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
					&copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
				</div>
						</div>
		</div>
	</div>
    
    <Footer />
	</section>
</template>

<script>
import Footer from '@/components/Footer/Footer.vue';
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
	components:{
    	Footer,
    },
  data(){
	return{

	}
  },
  methods:{
	NextGo(){
		//버튼 클릭 시 메인 페이지로 이동
		this.$router.push('/login');
	}
  }
}
</script>

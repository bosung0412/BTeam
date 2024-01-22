<template>

	<div id="app">
	  <!-- Navbar Start -->
	  <Navbar />
	  <!-- Navbar End -->
  
	  <!-- Page Header Start -->
	  <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
		<div class="container text-center py-5">
		  <h1 class="display-5 text-white mb-4 animated slideInDown">추천 음식 상세 정보 보기</h1>
		  <nav aria-label="breadcrumb" class="animated slideInDown"></nav>
		</div>
	  </div>
	  <!-- Page Header End -->
  
	  <!-- Projects Start -->
<div class="container mt-3">
    <div class="card">
        <div class="card-body">
            <form>

                <div class="row">
                    <input type="hidden" name="nutrient_id" :value="result.nutrient_id" />
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="name" class="form-label">음식 명 :</label>
                        <input type="text" class="form-control" id="name" name="name" readonly :value="result.name" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="cal" class="form-label">칼로리:</label>
                        <input type="text" class="form-control" id="cal" name="cal" readonly :value="result.cal + ' kcal'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="weight" class="form-label">중량 :</label>
                        <input type="text" class="form-control" id="weight" name="weight" readonly :value="result.weight + ' g'" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="carbo" class="form-label">탄수화물 :</label>
                        <input type="text" class="form-control" id="carbo" name="carbo" readonly :value="result.carbo + ' g'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="sugars" class="form-label">당류 :</label>
                        <input type="text" class="form-control" id="sugars" name="sugars" readonly :value="result.sugars + ' g'" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="fat" class="form-label">지방:</label>
                        <input type="text" class="form-control" id="fat" name="fat" readonly :value="result.fat + ' g'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="protein" class="form-label">단백질:</label>
                        <input type="text" class="form-control" id="protein" name="protein" readonly :value="result.protein + ' g'" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="calcium" class="form-label">칼슘:</label>
                        <input type="text" class="form-control" id="calcium" name="calcium" readonly :value="result.calcium + ' mg'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="phosphorus" class="form-label">인:</label>
                        <input type="text" class="form-control" id="phosphorus" name="phosphorus" readonly :value="result.phosphorus + ' mg'" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="sodium" class="form-label">나트륨:</label>
                        <input type="text" class="form-control" id="sodium" name="sodium" readonly :value="result.sodium + ' mg'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="potassium" class="form-label">칼륨:</label>
                        <input type="text" class="form-control" id="potassium" name="potassium" readonly :value="result.potassium + ' mg'" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="magnesium" class="form-label">마그네슘:</label>
                        <input type="text" class="form-control" id="magnesium" name="magnesium" readonly :value="result.magnesium + ' mg'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="iron" class="form-label">철:</label>
                        <input type="text" class="form-control" id="iron" name="iron" readonly :value="result.iron + ' mg'">
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="zinc" class="form-label">아연:</label>
                        <input type="text" class="form-control" id="zinc" name="zinc" readonly :value="result.zinc + ' mg'" />
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="cholesterol" class="form-label">콜레스테롤:</label>
                        <input type="text" class="form-control" id="cholesterol" name="cholesterol" readonly :value="result.cholesterol + ' mg'" />
                    </div>
                    <div class="mb-3 mt-3 col-md-6">
                        <label for="transfat" class="form-label">트랜스지방:</label>
                        <input type="text" class="form-control" id="transfat" name="transfat" readonly :value="result.transfat + ' g'" />
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<!-- Projects End -->

	</div>
  </template>
  
  <script>
  import Navbar from "@/components/Navbar/Navbar.vue";
  import Footer from "@/components/Footer/Footer.vue";
  import axios from "axios";
  
  export default {
	data() {
	  return {
		result: {},
	  };
	},
	created() {
	  axios
		.get("http://192.168.0.230/project/detail?nutrient_id=" + this.$route.params.nutrient_id)
		.then((resp) => {
		  console.log(resp);
		  this.result = resp.data;
		})
		.catch((error) => {
		  console.log(error);
		});
	},
	methods: {
	  fetchData() {},
	},
	components: {
	  Navbar,
	  Footer,
	},
  };
  </script>
<style scoped>
@media screen and (max-width: 1080px) and (max-height: 2220px) {
	.row {
		display: flex;
		justify-content: space-between;
		flex-wrap: wrap;
	}

	.form-label, .form-control, .mb-3, .mt-3 {
		flex-basis: calc(50% - 1rem);
		box-sizing: border-box;
	}
}
</style>






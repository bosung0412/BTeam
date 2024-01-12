<template>
  <div id="app">
    <!-- Navbar Start -->
    <Navbar />
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">추천 식단 상세 정보</h1>
        <nav aria-label="breadcrumb" class="animated slideInDown"></nav>
      </div>
    </div>
    <!-- Page Header End -->

    <!-- Projects Start -->
    <div class="container mt-3">
		<form>
			<input type="hidden" name="fno" :value="result.fno" />
			<div class="mb-3 mt-3">
				<label for="fname" class="form-label">식단 이름 :</label>
				<input type="text" class="form-control" id="fname" name="fname" readonly :value="result.fname" />
			</div>
			<div class="mb-3 mt-3">
				<label for="fimage" class="form-label">식단 사진 :</label>
				<input type="text" class="form-control" id="fimage" name="fimage" readonly :value="result.fimage" />
			</div>
      <div class="mb-3 mt-3">
				<label for="tocal" class="form-label">총 칼로리:</label>
				<input type="text" class="form-control" id="tocal" name="tocal" readonly :value="result.tocal" />
			</div>
			<div class="mb-3 mt-3">
				<label for="fprovince" class="form-label">지방 :</label>
				<input type="text" class="form-control" id="fprovince" name="fprovince" readonly :value="result.fprovince"/>
			</div>
			<div class="mb-3 mt-3">
				<label for="ffprovince" class="form-label">포화 지방:</label>
				<input type="text" class="form-control" id="ffprovince" name="ffprovince" readonly :value="result.ffprovince" />
			</div>
      <div class="mb-3 mt-3">
				<label for="fcholesterol" class="form-label">콜레스테롤:</label>
				<input type="text" class="form-control" id="fcholesterol" name="fcholesterol" readonly :value="result.fcholesterol" />
			</div>
      <div class="mb-3 mt-3">
				<label for="fsalt" class="form-label">나트륨:</label>
				<input type="text" class="form-control" id="fsalt" name="fsalt" readonly :value="result.fsalt" />
			</div>
      <div class="mb-3 mt-3">
				<label for="fpotasium" class="form-label">칼륨:</label>
				<input type="text" class="form-control" id="fpotasium" name="fpotasium" readonly :value="result.fpotasium" />
			</div>
      <div class="mb-3 mt-3">
				<label for="fcarbohydrate" class="form-label">탄수화물:</label>
				<input type="text" class="form-control" id="fcarbohydrate" name="fcarbohydrate" readonly :value="result.fcarbohydrate" />
			</div>
      <div class="mb-3 mt-3">
				<label for="fdietaryfiber" class="form-label">식이섬유:</label>
				<input type="text" class="form-control" id="fdietaryfiber" name="fdietaryfiber" readonly :value="result.fdietaryfiber" />
			</div>
      <div class="mb-3 mt-3">
				<label for="fprotein" class="form-label">단백질:</label>
				<input type="text" class="form-control" id="fprotein" name="fprotein" readonly :value="result.fprotein" />
			</div>

      

		</form>
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
      result: {}, // 추가된 부분
    };
  },
  created() {
    axios
        .get("http://192.168.0.88/project/detail?fno=" + this.$route.params.fno)
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
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
				<label for="fprovince" class="form-label">지방 :</label>
				<input type="text" class="form-control" id="fprovince" name="fprovince" readonly :value="result.fprovince"/>
			</div>
			<div class="mb-3 mt-3">
				<label for="ffprovince" class="form-label">포화 지방:</label>
				<input type="text" class="form-control" id="ffprovince" name="ffprovince" readonly :value="result.ffprovince" />
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
        .get("http://192.168.0.88/project/detail?num=" + this.$route.params.num)
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
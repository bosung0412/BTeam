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
    <form>
        <input type="hidden" name="nutrient_id" :value="result.nutrient_id" />

        <div class="row">
            <!-- 첫 번째 줄 -->
            <div class="col-md-6 mb-3">
                <label for="name" class="form-label">음식 명 :</label>
                <input type="text" class="form-control" id="name" name="name" readonly :value="result.name" />
            </div>
            <div class="col-md-6 mb-3">
                <label for="cal" class="form-label">칼로리:</label>
                <input type="text" class="form-control" id="cal" name="cal" readonly :value="result.cal" />
            </div>
        </div>

        <div class="row">
            <!-- 두 번째 줄 -->
            <div class="col-md-6 mb-3">
                <label for="weight" class="form-label">중량 :</label>
                <input type="text" class="form-control" id="weight" name="weight" readonly :value="result.weight"/>
            </div>
            <div class="col-md-6 mb-3">
                <label for="carbo" class="form-label">탄수화물 :</label>
                <input type="text" class="form-control" id="carbo" name="carbo" readonly :value="result.carbo" />
            </div>
        </div>

        <div class="row">
            <!-- 세 번째 줄 -->
            <div class="col-md-6 mb-3">
                <label for="sugars" class="form-label">당류 :</label>
                <input type="text" class="form-control" id="sugars" name="sugars" readonly :value="result.sugars" />
            </div>
            <div class="col-md-6 mb-3">
                <label for="fat" class="form-label">지방:</label>
                <input type="text" class="form-control" id="fat" name="fat" readonly :value="result.fat" />
            </div>
        </div>

        <div class="row">
            <!-- 네 번째 줄 -->
            <div class="col-md-6 mb-3">
                <label for="protein" class="form-label">단백질:</label>
                <input type="text" class="form-control" id="protein" name="protein" readonly :value="result.protein" />
            </div>
            <div class="col-md-6 mb-3">
                <label for="calcium" class="form-label">칼슘:</label>
                <input type="text" class="form-control" id="calcium" name="calcium" readonly :value="result.calcium" />
            </div>
        </div>

        <!-- 나머지 입력 필드 추가 -->

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

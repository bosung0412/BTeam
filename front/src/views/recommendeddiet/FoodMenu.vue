<template>
  <div id="app">
    <Navbar />

    <!-- 페이지 헤더 시작 -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">추천 식단</h1>
        <nav aria-label="breadcrumb" class="animated slideInDown"></nav>
      </div>
    </div>
    <!-- 페이지 헤더 끝 -->

    <!-- 카드 그리드 시작 -->
    <div class="container mt-3">
      <div class="row">
        <!-- 아침 카드 -->
        <div class="col-md-4">
          <h2 class="text-center">아침</h2>
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">밥or면 :{{ meals.breakfast.name }}</h5>
              <p class="card-text">밥or면: {{ meals.breakfast.calories }}kcal</p>
              <!-- 다른 식사 항목과 칼로리 정보를 추가할 수 있습니다 -->
              <p class="card-text">총 칼로리: {{ meals.breakfast.calories }}kcal</p>
              <button @click="goToDetail(meals.breakfast.id)" class="btn btn-success">자세히 보기</button>
            </div>
          </div>
        </div>

        <!-- 점심 카드 -->
        <div class="col-md-4">
          <h2 class="text-center">점심</h2>
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">{{ meals.lunch.name }}</h5>
              <p class="card-text">밥or면: {{ meals.lunch.calories }}kcal</p>
              <!-- 다른 식사 항목과 칼로리 정보를 추가할 수 있습니다 -->
              <p class="card-text">총 칼로리: {{ meals.lunch.calories }}kcal</p>
              <button @click="goToDetail(meals.lunch.id)" class="btn btn-success">자세히 보기</button>
            </div>
          </div>
        </div>

        <!-- 저녁 카드 -->
        <div class="col-md-4">
          <h2 class="text-center">저녁</h2>
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">{{ meals.dinner.name }}</h5>
              <p class="card-text">밥or면: {{ meals.dinner.calories }}kcal</p>
              <!-- 다른 식사 항목과 칼로리 정보를 추가할 수 있습니다 -->
              <p class="card-text">총 칼로리: {{ meals.dinner.calories }}kcal</p>
              <button @click="goToDetail(meals.dinner.id)" class="btn btn-success">자세히 보기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 카드 그리드 끝 -->

    <Footer />
  </div>
</template>

<script>
import Navbar from "@/components/Navbar/Navbar.vue";
import Footer from "@/components/Footer/Footer.vue";
import axios from "axios";

export default {
  name: "foodlist",
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      meals: {
        breakfast: { name: '', calories: 0, id: null },
        lunch: { name: '', calories: 0, id: null },
        dinner: { name: '', calories: 0, id: null }
      }
    };
  },
  created() {
    this.fetchRandomMeals();
  },
  methods: {
    fetchRandomMeals() {
      axios.get("/api/meals")
        .then((response) => {
          const data = response.data;
          this.meals.breakfast = {
            name: data.breakfast.name,
            calories: data.breakfast.calories,
            id: data.breakfast.id
          };
          this.meals.lunch = {
            name: data.lunch.name,
            calories: data.lunch.calories,
            id: data.lunch.id
          };
          this.meals.dinner = {
            name: data.dinner.name,
            calories: data.dinner.calories,
            id: data.dinner.id
          };
        })
        .catch((error) => {
          console.error(error);
        });
    },
    goToDetail(id) {
      // 여기에 상세 페이지로 이동하는 로직을 추가하세요.
      // 예: this.$router.push({ name: 'DetailPage', params: { id: id } });
    }
  }
};
</script>

<!-- 스타일 -->
<style>
.card {
  border: none;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 1.5em; /* 예시 크기, 조정 가능 */
}

.btn-success {
  background-color: #4CAF50; /* 색상 코드는 변경 가능 */
}

/* 카드 제목 중앙 정렬 */
.text-center {
  text-align: center;
}

/* 반응형 레이아웃을 위한 추가 스타일 */
@media (max-width: 768px) {
  .col-md-4 {
    flex: 0 0 100%;
    max-width: 100%;
  }
}
</style>

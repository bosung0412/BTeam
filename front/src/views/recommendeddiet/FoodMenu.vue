<template>
  <div id="app">
    <!-- 네비게이션 바 컴포넌트 -->
    <Navbar />
    <h2 class="card-text text-center">일일 섭취 칼로리: {{ totalCalories }}kcal</h2>
    <!-- 카드 그리드 -->
    <div class="container mt-3">
      <div class="row g-4">
        <!-- 식사 카드: 아침, 점심, 저녁 -->
        <div class="col-md-4" v-for="(mealItems, mealTime) in meals" :key="mealTime">
          <h2 class="text-center">{{ mealTime }}</h2>
          <div class="card mb-4">
            <div class="card-body">
              <div v-for="item in mealItems" :key="item.id">
                <h5 class="card-title">{{ item.foodtype }}: {{ item.name }}</h5>
              </div>
              <p class="card-text">총 칼로리: {{ calculateTotalCalories(mealItems) }}kcal</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 푸터 컴포넌트 -->
    <Footer />
  </div>
</template>

<script>
import Navbar from "@/components/Navbar/Navbar.vue";
import Footer from "@/components/Footer/Footer.vue";
import axios from "axios";

export default {
  name: "FoodList",
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      user_id: 1, // 가져올 사용자의 ID
      height: null,
      meals: {
        breakfast: [],
        lunch: [],
        dinner: []
      }
    };
  },
  created() {
    this.fetchRandomMeals();
  },
  methods: {
    fetchUserHeight() {
      // 백엔드에서 해당 API 엔드포인트로 요청 보내기
      fetch("http://192.168.0.230/project/api/v1/auth/${this.userId}/height")
        .then(response => response.json())
        .then(data => {
          // 받아온 데이터 사용
          this.userHeight = data.height;
        })
        .catch(error => console.error('Error fetching user height:', error));
    },
  fetchRandomMeals() {
    axios.get("http://192.168.0.230/project/api/v1/auth/randomMeals")
      .then((response) => {
        const mealsData = response.data;
        // 각 식사 시간에 맞춰서 데이터를 할당합니다.
        this.meals.breakfast = mealsData.filter(item => item.foodtype === '밥' || item.foodtype === '면').slice(0, 1)
          .concat(
            mealsData.filter(item => item.foodtype === '국').slice(0, 1),
            mealsData.filter(item => item.foodtype === '반찬1').slice(0, 1),
            mealsData.filter(item => item.foodtype === '반찬2').slice(0, 1),
            mealsData.filter(item => item.foodtype === '반찬3').slice(0, 1)
          );

        this.meals.lunch = mealsData.filter(item => item.foodtype === '밥' || item.foodtype === '면').slice(1, 2)
          .concat(
            mealsData.filter(item => item.foodtype === '국').slice(1, 2),
            mealsData.filter(item => item.foodtype === '반찬1').slice(1, 2),
            mealsData.filter(item => item.foodtype === '반찬2').slice(1, 2),
            mealsData.filter(item => item.foodtype === '반찬3').slice(1, 2)
          );

        this.meals.dinner = mealsData.filter(item => item.foodtype === '밥' || item.foodtype === '면').slice(2, 3)
          .concat(
            mealsData.filter(item => item.foodtype === '국').slice(2, 3),
            mealsData.filter(item => item.foodtype === '반찬1').slice(2, 3),
            mealsData.filter(item => item.foodtype === '반찬2').slice(2, 3),
            mealsData.filter(item => item.foodtype === '반찬3').slice(2, 3)
          );
        
        this.meals.breakfast.forEach(item => item.cal = Math.floor(Math.random() * 101) + 100);
        this.meals.lunch.forEach(item => item.cal = Math.floor(Math.random() * 101) + 100);
        this.meals.dinner.forEach(item => item.cal = Math.floor(Math.random() * 101) + 100);
      })
      .catch((error) => {
        console.error(error);
      });
    },
    calculateTotalCalories(mealItems) {
      return mealItems.reduce((sum, item) => sum + item.cal, 0);
    },
  },
  computed: {
    totalCalories() {
      const breakfastCalories = this.calculateTotalCalories(this.meals.breakfast);
      const lunchCalories = this.calculateTotalCalories(this.meals.lunch);
      const dinnerCalories = this.calculateTotalCalories(this.meals.dinner);
      return breakfastCalories + lunchCalories + dinnerCalories;
    }
  }
};
</script>

<style>
/* 여기에 스타일 코드 */
</style>
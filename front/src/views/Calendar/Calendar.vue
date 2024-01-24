<template>
  <div>
    <!-- Navbar Start -->
    <Navbar />

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-3">
        <h3 class="display-5 text-white mb-2 animated slideInDown">캘린더</h3>
      </div>
    </div>
    <!-- Page Header End -->

    <div class="container-fluid cal">
      <!-- calendar start -->
      <div id="wrap">
        <FullCalendar :options="calendarOptions">
        </FullCalendar>
      </div>
      <!-- calendar end -->

      <!-- diet Detail Start -->
      <div class="detail">
        <div style="display: flex; align-items: center;">
          <img src="../../assets/img/cal_food.png" width="15%" style="margin: 0 1% 10% 5%;">
          <h4 style="width:70%; margin-bottom: 15%;">음식 정보를 확인하세요.</h4>
        </div>

        <input class="foodtitle" type="text" v-model="dietList.created_at" :placeholder="'Today : '+currentDate" readonly>
        <input class="foodtitle" type="text" v-model="dietList.mealtime" readonly>

        <div class="foodList" v-if="foodList.length > 0">
          <p>아래의 음식을 선택하세요!</p>
          <div v-for="(food, index) in foodList" :key="index">
            <a class="food" @click="selectFood(food.nutrient_id, index)">{{ food.name }}</a>
          </div>
        </div>
        <div class="foodList" v-else>캘린더의 식단을 선택하세요!</div>
        <div class="food_detail"><!-- 음식 정보 영역 --></div>
      </div>
    </div>
    <!-- diet Detail End -->
    <!-- Footer Start -->
    <Footer />
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from 'axios';

export default {
  name: 'calendar',
  components: {
    Navbar,
    Footer,
    FullCalendar,
  },
  data() {
    return {
      dietList: [], // 캘린더 이벤트
      foodList: {}, // 식단 정보
      foodDetail: {}, // 영양 정보
      totalCalories: {}, // 총 칼로리
      totalCaloriesByDate: {},
      calendarOptions: { // FullCalendar 기본 설정
        plugins: [dayGridPlugin, timeGridPlugin, listPlugin],
        headerToolbar: {
          left: 'title',
          center: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth',
          right: 'prev,next today',
        },
        editable: Boolean,
        firstDay: 0,
        selectable: true,
        allDaySlot: false,
        events: [],
        eventClick: this.onEventClick,
        eventRender: this.eventRender,
        eventClassNames: (arg) => {
          if (arg.event.extendedProps && arg.event.extendedProps.total_calories) {
            return ['fc-total_calories'];
          }

          return [];
        },
        eventContent: (arg) => {
          const wrapper = document.createElement('div');
          wrapper.classList.add('fc-content');

          // 이벤트의 제목을 표시하는 부분
          const titleContainer = document.createElement('div');
          titleContainer.classList.add('fc-title-container');

          const eventTitle = document.createElement('div');
          eventTitle.className = 'fc-title';
          eventTitle.textContent = arg.event.title;

          // getColorByMealtime 함수를 호출하여 mealtime에 따라 다른 색상을 가져옴
          const eventColor = this.getColorByMealtime(arg.event.extendedProps.mealtime);

          // 이벤트 요소에 색상 적용
          eventTitle.style.backgroundColor = eventColor;

          titleContainer.appendChild(eventTitle);
          wrapper.appendChild(titleContainer);

          return { domNodes: [wrapper] };
        },
      },
      selectedFoodIndex: null, // 선택된 음식 항목의 인덱스를 추적
      nutrientSum: [],
    };
  },
  computed: {
    currentDate() { // 현재시간 포맷팅
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0');
      const day = String(now.getDate()).padStart(2, '0');

      return `${year}-${month}-${day}`;
    },
  },
  mounted() {
    this.loadDietData();
  },
  methods: {
    getCombinedEntries(foodList) { // 각 식단마다 영양정보 합함
      const totalCalories = foodList.reduce((sum, food) => sum + food.cal, 0.0);
      const totalCarbo = foodList.reduce((sum, food) => sum + food.carbo, 0.0);
      const totalSugars = foodList.reduce((sum, food) => sum + food.sugars, 0.0);
      const totalFat = foodList.reduce((sum, food) => sum + food.fat, 0.0);
      const totalProtein = foodList.reduce((sum, food) => sum + food.protein, 0.0);
      const totalCalcium = foodList.reduce((sum, food) => sum + food.carcium, 0.0);
      const totalPhosphorus = foodList.reduce((sum, food) => sum + food.phosphorus, 0.0);
      const totalSodium = foodList.reduce((sum, food) => sum + food.sodium, 0.0);
      const totalPotassium = foodList.reduce((sum, food) => sum + food.potassium, 0.0);
      const totalMagnesium = foodList.reduce((sum, food) => sum + food.magnesium, 0.0);
      const totalIron = foodList.reduce((sum, food) => sum + food.iron, 0.0);
      const totalZinc = foodList.reduce((sum, food) => sum + food.zinc, 0.0);
      const totalCholesterol = foodList.reduce((sum, food) => sum + food.cholesterol, 0.0);
      const totalTransfat = foodList.reduce((sum, food) => sum + food.transfat, 0.0);

      return {
        cal: totalCalories,
        carbo : totalCarbo,
        sugars: totalSugars,
        fat: totalFat,
        protein: totalProtein,
        calcium: totalCalcium,
        phosphorus: totalPhosphorus,
        sodium: totalSodium,
        potassium: totalPotassium,
        magnesium: totalMagnesium,
        iron: totalIron,
        zinc: totalZinc,
        cholesterol: totalCholesterol,
        transfat: totalTransfat,
      };
    },
    getColorByMealtime(mealtime) {
      // dietList.mealtime 값에 따라 다른 색상을 반환
      if (mealtime === '조식') {
        return '#D6BBFF';
      } else if (mealtime === '석식') {
        return '#9CE8EE';
      } else if (mealtime === '야식') {
        return '#FFCAF8';
      } else {
        // 기본값 또는 다른 mealtime 값에 대한 처리
        return '';
      }
    },
    loadDietData() {
      axios.get("http://localhost/project/api/v1/auth/selectdiet")
        .then((resp) => {
          console.log("-----dietList-----");
          console.log(resp.data);
          this.totalCaloriesByDate = {}; // 총 칼로리 초기화
          this.dietList = resp.data;
          
          this.dietList.forEach(diet => {
            const dateWithoutTime = new Date(diet.created_at);
            dateWithoutTime.setHours(0, 0, 0, 0);
            const date = dateWithoutTime.toLocaleDateString();
            this.totalCaloriesByDate[date] = (this.totalCaloriesByDate[date] || 0) + diet.total_calories;
          });

          const transformedEvents = this.dietList.map(({ mealtime, created_at, total_calories, diet_id }, index) => ({
            title: mealtime,
            start: new Date(created_at),
            allDay: false,
            extendedProps: { mealtime, created_at, total_calories, diet_id },
          })).concat(Object.keys(this.totalCaloriesByDate).map(date => ({
            title: `Total : ${this.totalCaloriesByDate[date].toString()} kcal`,
            start: new Date(date),
            allDay: false,
            backgroundColor: 'white',
            total_calories: this.totalCaloriesByDate[date],
            classNames: ['celestial-calories'],
          })));
          
          this.calendarOptions.events = transformedEvents;
        })
        .catch((error) => {
          console.error('식단 데이터를 가져오는 동안 오류가 발생했습니다.', error);
        });
    },
    onEventClick(info) { // 캘린더 이벤트 클릭
      this.dietList = info.event.extendedProps;
      this.loadFoodData();
    },
    loadFoodData() { // 식단 클릭 시 음식 데이터
      axios.get(`http://localhost/project/api/v1/auth/selectdietinfo?diet_id=${this.dietList.diet_id}`)
        .then((resp) => {
          console.log("-----foodList-----");
          console.log(resp.data);
          this.foodList = resp.data;
          this.selectedFoodIndex = null;

          this.$nextTick(() => {
            this.updateFoodListContent();
          })
        })
        .catch((error) => {
          console.log(error);
        });
    },
    selectFood(nutrient_id, index) { // 음식 선택 시
      this.selectedFoodIndex = index; // 선택된 음식 인덱스 업데이트
      axios.get(`http://localhost/project/api/v1/auth/selectfoodinfo?nutrient_id=${nutrient_id}`)
        .then((resp) => {
          console.log("-----foodDetail-----");
          console.log(resp.data);
          this.foodDetail = resp.data;

          this.$nextTick(() => {
            this.updateFoodDetailContent();
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateFoodDetailContent() { // 음식 클릭 시 데이터 변경
      const foodDetailElement = this.$el.querySelector('.food_detail');

      if (foodDetailElement) {
        foodDetailElement.innerHTML = '';

        const div = document.createElement('div');
        div.className = 'detail_diet';

        const h4 = document.createElement('h4');
        h4.style.margin = '3%';
        h4.textContent = '음식 정보';
        div.appendChild(h4);

        if (this.foodDetail && this.foodDetail.frimg_filename) {
          const img = document.createElement('img');
          img.id = 'foodimg';
          img.src = require(`../../assets/img/${this.foodDetail.frimg_filename}`);
          div.appendChild(img);
        }

        const table = document.createElement('table');
        table.className = 'nutrient';
        const tbody = document.createElement('tbody');

        for (const [key, value] of Object.entries(this.foodDetail).slice(5, 21)) {
          const dietTr = document.createElement('tr');
          dietTr.className = 'diet_';

          const tdKey = document.createElement('td');
          tdKey.textContent = this.getCustomLabel(key) + ' : ';
          dietTr.appendChild(tdKey);

          const tdVal = document.createElement('td');
          tdVal.id = 'nutrient_val';
          tdVal.textContent = value;
          dietTr.appendChild(tdVal);

          tbody.appendChild(dietTr);
        }
        table.appendChild(tbody);
        div.appendChild(table);
        foodDetailElement.appendChild(div);
      }
    },
    updateFoodListContent() { // 식단 클릭 시 데이터 변경
      const foodDetailElement = this.$el.querySelector('.food_detail');

      if (foodDetailElement) {
        foodDetailElement.innerHTML = '';

        const div = document.createElement('div');
        div.className = 'detail_diet';

        const h4 = document.createElement('h4');
        h4.style.margin = '3%';
        h4.textContent = '음식 정보 합계';
        div.appendChild(h4);

        if (this.foodList && this.foodList.length > 0 && this.foodList[0].img_filename) {
          const img = document.createElement('img');
          img.id = 'foodimg';
          img.src = require(`../../assets/img/${this.foodList[0].img_filename}`);
          div.appendChild(img);
        }

        const table = document.createElement('table');
        table.className = 'nutrient';
        const tbody = document.createElement('tbody');

        for (const [key, value] of Object.entries(this.getCombinedEntries(this.foodList))) {
          const dietTr = document.createElement('tr');
          dietTr.className = 'diet_';

          const tdKey = document.createElement('td');
          tdKey.textContent = this.getCustomLabel(key) + ' : ';
          dietTr.appendChild(tdKey);

          const tdVal = document.createElement('td');
          tdVal.id = 'nutrient_val';
          tdVal.textContent = value;
          dietTr.appendChild(tdVal);

          tbody.appendChild(dietTr);
        }
        table.appendChild(tbody);
        div.appendChild(table);
        foodDetailElement.appendChild(div);
      }
    },
    getCustomLabel(key) { // 영문으로 된 라벨을 한글로 변환
      const labelMappings = {
        name: '음식명',
        weight: '중량(g)',
        cal: '칼로리(kcal)',
        carbo: '탄수화물(g)',
        sugars: '당류(g)',
        fat: '지방(g)',
        protein: '단백질(g)',
        calcium: '칼슘(mg)',
        phosphorus: '인(mg)',
        sodium: '나트륨(mg)',
        potassium: '칼륨(mg)',
        magnesium: '마그네슘(mg)',
        iron: '철(mg)',
        zinc: '아연(mg)',
        cholesterol: '콜레스테롤(mg)',
        transfat: '트랜스지방(g)'
      };
      return labelMappings[key] || key;
    },
  },
};
</script>

<style>
  body {text-align: center; font-size: 14px;}
  .cal {display: flex; padding: 5% 10% 10% 10%; min-height: 100%;}

  /* Calendar CSS */
  #wrap {position: relative; margin: 0 5% 0 0; width: 65%; height: 100%; border: 1px solid white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); padding: 1%; border-radius: 10px;}
  .fc-day {background-color: #FFFFFF; font-size: 15px;}
  .fc .fc-button-primary {background-color: #A5D299; border: none;}
  .fc .fc-button-primary:not(:disabled).fc-button-active{background-color: #397329; color: white;}
  .fc-toolbar-title {width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;}
  .u-overlap.u-overlap-transparent:not(.u-overlap-contrast) .u-header :not(.u-nav-item) > a, 
    .u-gradient > .u-container-layout > a, .u-image:not(.u-shading) > .u-container-layout > a, a {color: black;}
  .fc-dayGridMonth-view {border: 3px solid #A5D299;}.fc-day-sun a {color: red; text-decoration: none;}
  .fc .fc-daygrid-day-top {float: left;}.fc .fc-daygrid-day-number {line-height: 20px;}
  .fc .fc-daygrid-day.fc-day-today {background-color: #ffffff;}
  .fc .fc-daygrid-day.fc-day-today .fc-daygrid-day-number {background-color: #f0768b; border-radius: 20px; height: 25px; }
  .fc-daygrid-event-dot {color:#f0768b;}
  .celestial-calories {cursor: none; pointer-events: none; font-size: 0.9rem; background-color: transparent;}
  .fc-content {font-size: 0.9rem; width: 100%;} .fc-title {padding-left: 3%;}
  a {cursor: pointer;}
  
  /* Food info CSS */
  .detail {position: relative; width: 30%;  margin-bottom: 100%;  border-radius: 5px;  display: flex; flex-direction: column; }
  .foodtitle {margin: 2%; text-align: center; font-size: 25px; font-weight: bold; border: none; background-color: transparent;}
  .foodList {margin-top: 5%; padding: 1% 3% 7% 3%; font-size: 20px; margin-bottom: 5%; border-radius: 80px; border: 2px solid #f0768b; box-shadow: 0 0 10px rgba(240, 118, 139, 0.7);}
  .food {float: left; text-align: center; margin: 1%; border-radius: 30px; background-color: #A5D299; font-size: 90%; font-weight: bold; cursor: pointer;}
  #foodimg {border: 3px solid #f0768b; width: 90%; margin: 1% 1% 5% 0; height: 200px;}
  .nutrient {display: flex; justify-content: center; font-size: 17px;}
  .detail_diet {padding-top: 3%; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); border-radius: 10px;}

</style>
<style scoped>
  .fc .fc-daygrid-event-harness-abs {position: absolute; margin-top: 0px; right: 0px; border: none;}
  @media screen and (max-width: 1080px) and (max-height: 2220px) {
    .cal {display: flex; padding: 1%; width: 390px;}
    #wrap {margin: 1px; width: 100%; height: 100%; border: 1px solid white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
      border-radius: 10px; overflow-x: auto; white-space: nowrap;}
    .fc-media-screen {width: 100%; height: 700px;}
    .fc-day {width: 90px; background-color: #FFFFFF; display: inline-block;}
  }
</style>
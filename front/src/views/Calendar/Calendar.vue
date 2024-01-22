<template>
  <div>
    <!-- Navbar Start -->
    <Navbar/>

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
        <FullCalendar :options="calendarOptions"></FullCalendar>
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
        <!-- for문 돌리기 -->
        <div class="foodList" v-if="foodList.length > 0">
          <div v-for="(food, index) in foodList" :key="index">
            <a class="food" @click="selectFood(food.nutrient_id, index)">{{ food.name }}</a>
          </div>
        </div>
        <div class="foodList" v-else>캘린더의 식단을 선택하세요!</div>
      
        <div class="food_detail">
          <!-- foodDetail 내용 표시 -->
          <template v-if="foodList.length > 0">
            <div class="detail_diet">
              <h4 style="margin: 3%">음식 정보 합계</h4>
              <img id="foodimg" v-if="foodList && foodList.length > 0 && foodList[0].img_filename" 
                :src="require(`../../assets/img/${ foodList[0].img_filename }`)">
                <table>
                  <tbody style="text-align: center;">
                    <tr class="diet_" v-for="(value, key, index) in getCombinedEntries(foodList)" :key="index">
                      <td>{{ getCustomLabel(key) }}</td>
                      <td id="nutrient" type="text">{{value}}</td>
                    </tr>
                  </tbody>
                </table>
            </div>
          </template>
        </div>
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
      dietList: {}, // 캘린더 이벤트
      foodList: {}, // 식단 정보
      foodDetail: {}, // 영양 정보
      calendarOptions: { // FullCalendar 기본 설정
        plugins: [dayGridPlugin, timeGridPlugin, listPlugin],
        headerToolbar: {
          left: 'title',
          center: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth',
          right: 'prev,next today',
        },
        editable: false,
        firstDay: 0,
        selectable: true,
        defaultView: 'month',
        axisFormat: 'h:mm',
        columnFormat: {
          month: 'ddd',
          week: 'ddd d',
          day: 'dddd M/d',
          agendaDay: 'dddd d',
        },
        titleFormat: {
          month: 'long',
          year: 'numeric',
        },
        allDaySlot: false,
        events: [],
        eventClick: this.onEventClick,
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
      const totalCarbo = foodList.reduce((sum, food) => sum + food.carbo, 0);
      const totalSugars = foodList.reduce((sum, food) => sum + food.sugars, 0);
      const totalFat = foodList.reduce((sum, food) => sum + food.fat, 0);
      const totalProtein = foodList.reduce((sum, food) => sum + food.protein, 0);
      const totalCalcium = foodList.reduce((sum, food) => sum + food.carcium, 0);
      const totalPhosphorus = foodList.reduce((sum, food) => sum + food.phosphorus, 0);
      const totalSodium = foodList.reduce((sum, food) => sum + food.sodium, 0);
      const totalPotassium = foodList.reduce((sum, food) => sum + food.potassium, 0);
      const totalMagnesium = foodList.reduce((sum, food) => sum + food.magnesium, 0);
      const totalIron = foodList.reduce((sum, food) => sum + food.iron, 0);
      const totalZinc = foodList.reduce((sum, food) => sum + food.zinc, 0);
      const totalCholesterol = foodList.reduce((sum, food) => sum + food.cholesterol, 0);
      const totalTransfat = foodList.reduce((sum, food) => sum + food.transfat, 0);

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
    loadDietData() { // 캘린더의 식단 정보 데이터
      axios.get("http://localhost/project/selectdiet")
        .then((resp) => {
          console.log("-----dietList-----");
          console.log(resp.data);
          this.dietList = resp.data;
          const transformedEvents = this.dietList.map(diet => {
            return {
              title: diet.mealtime,
              start: new Date(diet.created_at),
              extendedProps: diet,
            };
          });
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
      axios.get(`http://localhost/project/selectdietinfo?diet_id=${this.dietList.diet_id}`)
        .then((resp) => {
          console.log("-----foodList-----");
          console.log(resp.data);
          this.foodList = resp.data;
          this.selectedFoodIndex = null;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    selectFood(nutrient_id, index) { // 음식 선택 시
      this.selectedFoodIndex = index; // 선택된 음식 인덱스 업데이트
      axios.get(`http://localhost/project/selectfoodinfo?nutrient_id=${nutrient_id}`)
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
        div.id = 'detail_diet';

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

        for (const [key, value] of Object.entries(this.foodDetail).slice(5, 21)) {
          const dietDiv = document.createElement('div');
          dietDiv.className = 'diet_';

          const label = document.createElement('label');
          label.textContent = this.getCustomLabel(key) + ' : ';
          dietDiv.appendChild(label);

          const input = document.createElement('input');
          input.id = 'nutrient';
          input.type = 'text';
          input.value = value;
          input.readOnly = true;
          dietDiv.appendChild(input);

          div.appendChild(dietDiv);
        }
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
  #wrap {margin: 0 5% 0 0; width: 65%; height: 100%; border: 1px solid white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); padding: 1%; border-radius: 10px;}
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

  /* Food info CSS */
  .diet {width: auto; border: none; background-color: transparent;}
  .foodtitle {margin: 2%; text-align: center; font-size: 25px; font-weight: bold; border: none; background-color: transparent;}
  .detail {position: relative; width: 30%;  margin-bottom: 100%;  border-radius: 5px;  display: flex; flex-direction: column; }
  .foodList {margin-top: 5%; padding: 7% 1% 7% 1%; font-size: 20px; margin-bottom: 5%; border-radius: 80px; border: 2px solid #f0768b; box-shadow: 0 0 10px rgba(240, 118, 139, 0.7);}
  .food {float: left; text-align: center; margin: 1%; border-radius: 30px; background-color: #A5D299; font-size: 90%; font-weight: bold; cursor: pointer;}
  #diet_date {width: 90%; border: none; border-bottom: 1px solid gray; font-size: 1rem; font-weight: bold; text-align: center; margin: 0 0 3% 5%; background-color: transparent; display:flex;}
  #foodimg {border: 3px solid #f0768b; width: 90%; margin: 1% 1% 5% 0; height: 200px;}
  .diet_ {font-size: 17px; text-align: center;}
  
  @media(max-width: 768px){.container-fluid{flex-direction: column; padding: 0;} #wrap{width: 100%;} .detail{width: 100%;}}
</style>
    
<style scoped>
  .cal {display: flex; flex-direction: row; height: 300%; padding-left: 10%; padding-right: 10%;}
  body {background-color: #F5F6FA;}

  .plan {margin: 5%; border: 3px solid #A5D299; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); border-radius: 5px; overflow: hidden; width: 50%; text-align: center; float: left; background-color: #FFFFFF;}
  h3 {color: #333;}
  .calendar {display: grid; grid-template-columns: repeat(7, 1fr); margin: 10px; border: 2px solid #A5D299; border-radius: 5px; overflow: hidden;}
  .day {padding: 15px; text-align: left; cursor: pointer; height: 130px; border: 1px solid #A5D299; border-bottom: 1px solid #A5D299; overflow: hidden;}
  .day:last-child {border-right: 2.5px solid #A5D299;}
  .fixed-day {background-color: #A5D299; font-weight: bold; text-align: center; height: 40px;}
  .event-list {margin-top: 10px; list-style: none; padding: 0;}
  .event-list li {margin-bottom: 5px;}
  .event-val {overflow: hidden;}
  button {background-color: white; color: #A5D299; border: none; padding: 5px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 20px; margin: 3px 20px; cursor: pointer; border-radius: 5px;}
  button:hover {border: 2px solid #A5D299;}
  #eventInput {padding: 10px; margin: 5px; border-radius: 5px; border: 1px solid #ccc;}

  .detail {position: relative; width: 35%; height: 350px; margin-top: 6%; border: 1px solid gray; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); display: flex; flex-direction: column; background: linear-gradient(rgba(255, 255, 255, 0.7), rgba(255, 255, 255, 0.7)), url(../../assets/img/foodimg1.png) center/cover; color: black;}
  #diet_date {width: 90%; border: none; border-bottom: 1px solid gray; font-size: 1rem; font-weight: bold; text-align: center; margin-bottom: 3%; margin-left: 5%; background-color: transparent; display:flex;}
  #foodimg {margin-top: 10%; border: 3px solid #f0768b; width: 75%; margin-left: 5%;}
  .diet_ {width: 50%; float: left; height: 280px;}
  #foodname, #calorie {border: none; width: 10rem; font-size: 20px; background-color: transparent; text-align: center;}
  #foodinfo {font-size: 1rem; width: 40%; overflow: scroll; word-wrap: break-word; background-color: transparent; border: none;}

  .chart {height: 650px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); margin-top: 10%; margin-bottom: 30%; padding-top: 1%; background-color: white;}
  #activityChart, #weightChart {margin: 7%; width: 85%; border: 2px solid silver; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); background-color: #fff5f6;}
  h5 {margin: 3%;}
  .chartbtn button {background-color: transparent; font-size: 15px; color: gray; margin: 0; float: right;}
  .chartbtn button:hover {text-decoration: underline; border: none;}
  @media(max-width: 768px){.container-fluid{flex-direction: column;} .plan{width: 100%;} .detail{width: 100%;}}
</style>

<template>
  <div>
    <Navbar />
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
        <div class="container text-center py-3">
          <h3 class="display-5 text-white mb-2 animated slideInDown">캘린더</h3>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Calendar Start -->
    <div class="container-fluid cal">
      <div class="plan">
        <h3 style="margin-top: 20px;">식단을 확인하세요!</h3>
        <div style="height:5%">
          <button @click="changeMonth(-1)">이전</button>
            {{ currentMonth.format('MMMM YYYY') }}
          <button @click="changeMonth(1)">다음</button>
        </div>
        <div class="calendar">
          <div v-for="weekday in weekdays" :key="weekday" class="day fixed-day">{{ weekday }}</div>
          <div v-for="day in calendar" :key="day.dateKey" class="day" @click="showEventModal(day.date)">
            {{ day.date ? day.date.date() : '' }}
            <ul class="event-list">
              <li class="event-val" v-for="event in day.events" :key="event" 
                :style="{ backgroundColor: event.color }">{{ event.event }}</li>
            </ul>
          </div>
        </div>
        <div v-if="isEventModalOpen">
          <h5>{{ selectedDate.format('YYYY-MM-DD') }} 식단 추가</h5>
          <ul class="event-list">
            <li v-for="event in getEventsForDate(selectedDate)" :key="event">{{ event }}</li>
          </ul>
          <label for="eventInput">Add Event:</label>
          <input ref="eventInput" type="text" v-model="newEvent" id="eventInput">
          <button @click="addEvent">Add</button>
          <button @click="closeEventModal">Close</button>
        </div>
      </div>
      <!-- Calendar End -->
    
      <!-- diet Detail Start -->
      <div class="detail">
        <h4 style="margin: 3%">음식 정보</h4>
        <div>
          <div class="diet_">
            <input type="text" id="diet_date" value="2023-12-29 breakfast">
            <img id="foodimg" src="../../assets/img/chicken.png">
          </div>
          <div class="diet_">
            닭가슴살 <input type="text" id="foodinfo" value="245.9 kcal"><br>
            지방 <input type="text" id="foodinfo" value="12g"><br>
            포화지방 <input type="text" id="foodinfo" value="3.3g"><br>
            콜레스테롤 <input type="text" id="foodinfo" value="87mg"><br>
            나트륨 <input type="text" id="foodinfo" value="77mg"><br>
            칼륨 <input type="text" id="foodinfo" value="239mg"><br>
            탄수화물 <input type="text" id="foodinfo" value="1.8g"><br>
            식이섬유 <input type="text" id="foodinfo" value="0.1g"><br>
            단백질 <input type="text" id="foodinfo" value="30g">
          </div>
        </div>
        <div>
          <div class="chart">
	          <div id="chart_1">
	            <h5>월별 성공/실패율을 확인하세요!</h5>
              <canvas id="activityChart"></canvas>
	          </div>
	          <hr>
	          <div>
	            <h5>몸무게가 어떻게 변화 되었는지 확인하세요!</h5>
	            <div class="chartbtn">
	              <button id="w_year">연별</button>
	              <button id="w_month">월별</button>
                <button id="w_day">일별</button>
	            </div>
	            <canvas id="weightChart"></canvas>
	          </div>
          </div>
        </div>
      </div>
      <!-- diet Detail End -->
    </div>
    <!-- Footer Start -->
    <Footer />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
  export default({      
    data() {
      return{
        currentMonth: moment(), // 현재 표시되는 월
        calendar: [], // 월별 달력 데이터 저장
        newEvent: '', // 추가되는 이벤트 저장
        selectedDate: null, // 선택한 날짜
        isEventModalOpen: false, // 이벤트 모달창 오픈 여부
        events: [], // 모든 이벤트 목록 저장
        weekdays: moment.weekdaysShort(), // 요일 이름 배열
      }
    },
    components:{
        Navbar,
        Footer,
    },
    methods: {
      generateCalendar() { // 현재 월의 각 날짜 배열
        const startOfMonth = this.currentMonth.clone().startOf('month');
        const endOfMonth = this.currentMonth.clone().endOf('month');
        const firstDayOfWeek = startOfMonth.day();

        const daysInMonth = endOfMonth.date();

        let calendar = [];

        for (let i = 1; i <= daysInMonth; i++) {
          const currentDate = moment([this.currentMonth.year(), this.currentMonth.month(), i]);
          calendar.push({ date: currentDate, dateKey: currentDate.format('YYYY-MM-DD'), events: this.getEventsForDate(currentDate) });
        }

        for (let i = 0; i < firstDayOfWeek; i++) {
          calendar.unshift({ date: null, dateKey: `empty-${i}`, events: [] });
        }

        this.calendar = calendar;
      },

      changeMonth(step) { // 월 변경
        if (!this.isEventModalOpen) {
          this.currentMonth = this.currentMonth.clone().add(step, 'month');
          this.generateCalendar();
        }
      },

      showEventModal(date) { // 선택한 날짜의 이벤트 모달 오픈
        this.selectedDate = date;
        this.isEventModalOpen = true;
        this.$nextTick(() => {
          this.$refs.eventInput.focus();
        });
      },

      closeEventModal() { // 이벤트 모달 닫기
        this.isEventModalOpen = false;
        this.selectedDate = null;
        this.newEvent = '';
      },

      addEvent() { // 해당 날짜, 목록에 이벤트 추가
        const eventColors = {
          event1: '#FED2B5',
          event2: '#ADDCC8',
          event3: '#ADCDFF',
        }
        if (this.selectedDate) {
          const selectedDay = this.calendar.find(day => day.date && day.date.isSame(this.selectedDate, 'day'));

          if (selectedDay) {
            const eventId = `event${selectedDay.events.length + 1}`;
            const eventColor = eventColors[eventId] || '#CCCCCC';

            selectedDay.events.push({ event: this.newEvent, color: eventColor });

            this.events.push({ date: this.selectedDate, event: this.newEvent, color: eventColor });
          }
        }
        this.newEvent = '';
        this.closeEventModal();
      },

      getEventsForDate(date) { // 이벤트 목록 반환
        return this.events
        .filter(event => event.date.isSame(date, 'day'))
        .map(event => event.event);
      },
    },

    computed: {
      selectedDateEvents() { // 현재 선택된 날짜의 이벤트 목록 반환
        return this.getEventsForDate(this.selectedDate);
      },
    },

    mounted() { // 달력 생성
      this.generateCalendar();
    },
});
  // activity Chart
  document.addEventListener('DOMContentLoaded', function () {
    var ctx = document.getElementById('activityChart').getContext('2d');

    var chartData = {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      datasets: [{
        label: 'Activity Data',
        borderColor: 'rgba(255, 105, 180, 1)',
        borderWidth: 2,
        data: [65, 59, 80, 81, 56, 55, 40, 30, 20, 50, 70, 60],
      }]
    };

    var activityChart = new Chart(ctx, {
      type: 'line',
      data: chartData,
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  });
  // weight Chart
  document.addEventListener('DOMContentLoaded', function () {
    var ctx = document.getElementById('weightChart').getContext('2d');

    var chartData = {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      datasets: [{
        label: 'weight Data',
        borderColor: 'rgba(55, 105, 180, 1)',
        borderWidth: 2,
        data: [67, 63, 59, 59, 60, 55, 53, 52, 54, 51, 52, 52],
      }]
    };

    var weightChart = new Chart(ctx, {
      type: 'line',
      data: chartData,
      options: {
        scales: {
          y: {
            beginAtZero: false,
	          min: 40
          }
        }
      }
    });
  });
</script>

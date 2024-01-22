<template>
  <div>
    <!-- Navbar Start -->
    <Navbar/>

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-3">
        <h3 class="display-5 text-white mb-2 animated slideInDown">게시글 상세보기</h3>
      </div>
    </div>
    <!-- Page Header End -->

    <div class="container-fluid cal">
      <!-- calendar start -->
      <div id="wrap">
        <FullCalendar :options="calendarOptions"></FullCalendar>
        <div style="clear:both"></div>
      </div>
      <!-- calendar end -->

      <!-- diet Detail Start -->
      <div class="detail">
        
        <div class="detail_diet">
          <h4 style="margin: 3%">음식 정보</h4>
          <div class="diet_">
            <input type="text" id="diet_date" value="2023-12-29 breakfast">
            <img id="foodimg" src="../../assets/img/chicken.png">
          </div>
          <div class="diet_">
            이벤트<input type="text" id="foodinfo"><br>
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
  import { ref } from 'vue';
  import FullCalendar from '@fullcalendar/vue3';
  import dayGridPlugin from '@fullcalendar/daygrid';
  import timeGridPlugin from '@fullcalendar/timegrid';
  import listPlugin from '@fullcalendar/list';
  import Navbar from '@/components/Navbar/Navbar.vue';
  import Footer from '../../components/Footer/Footer.vue';

  export default {
    name: 'calendar',
    components: {
      Navbar,
      Footer,
      FullCalendar,
    },
    setup() {
      const y = new Date().getFullYear(); // 현재 년도
      const m = new Date().getMonth(); // 현재 월
      const d = new Date().getDay(); // 현재 일
      const calendarOptions = ref({
        plugins: [dayGridPlugin, timeGridPlugin, listPlugin],
        headerToolbar: {
          left: 'title',
          center: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth',
          right: 'prev,next today',
        },
        editable: true,
        firstDay: 1,
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
        selectHelper: true,
        select: (start, end, allDay) => {
          const title = prompt('Event Title:');
          if (title) {
            // 여기서 FullCalendar 객체의 `addEvent` 메소드를 사용해 이벤트를 추가합니다.
            calendarOptions.value.events.push({
              title: title,
              start: start,
              end: end,
              allDay: allDay,
            });
          }
        },
        droppable: true,
        drop: (info) => {
          // 드래그 앤 드롭 로직 추가
        },
        
        events: [
          {
            title: 'All Day Event',
            start: new Date(y, m, 1),
          },
          {
            id: 999,
            title: "Repeating Event",
            start: new Date(y, m, d - 3, 16, 0),
            allDay: false,
            className: "info",
          },
          {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d + 4, 16, 0),
            allDay: false,
            className: 'info',
          },
          {
            title: 'Meeting',
            start: new Date(y, m, d, 10, 30),
            allDay: false,
            className: 'important',
          },
          {
            title: 'Lunch',
            start: new Date(y, m, d, 12, 0),
            end: new Date(y, m, d, 14, 0),
            allDay: false,
            className: 'important',
          },
          {
            title: 'happy',
            start: new Date(y, m, d, 12, 0),
            allDay: true,
            className: 'important',
          },
          {
            title: 'Birthday Party',
            start: new Date(y, m, d + 1, 19, 0),
            end: new Date(y, m, d + 1, 22, 30),
            allDay: false,
          },
          {
            title: 'Click for Google',
            start: new Date(y, m, 28),
            end: new Date(y, m, 29),
            url: 'http://google.com/',
            className: 'success',
          },
        ]
      });
      return { 
        calendarOptions,
      };
    }
  };
</script>
    
<style>
  body {text-align: center; font-size: 14px;}
  .cal {display: flex; padding: 70px; height: auto;}

  #wrap {margin: 0 5% 0 5%; width: 55%; height: 100%; border: 1px solid white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); padding: 1%; border-radius: 10px;}
  .fc-day {margin: 0 auto; width: 90px; background-color: #FFFFFF;}
  .fc .fc-button-primary {background-color: #A5D299; border: none;}
  .fc .fc-button-primary:not(:disabled).fc-button-active{background-color: #397329; color: white;}
  .fc-toolbar-title {width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;}
  .u-overlap.u-overlap-transparent:not(.u-overlap-contrast) .u-header :not(.u-nav-item) > a, 
      .u-gradient > .u-container-layout > a, .u-image:not(.u-shading) > 
      .u-container-layout > a, a {color: black;}
  .fc .fc-daygrid-day-number {float: left;}
  .fc-dayGridMonth-view {border: 3px solid #A5D299;}

  .detail {position: relative; width: 30%;  margin-bottom: 100%;  border-radius: 5px;  display: flex; flex-direction: column; }
  #diet_date {width: 90%; border: none; border-bottom: 1px solid gray; font-size: 1rem; font-weight: bold; text-align: center; margin: 0 0 3% 5%; background-color: transparent; display:flex;}
  #foodimg {border: 3px solid #f0768b; width: 70%; margin: 1% 5% 5% 0;}
  .detail_diet {height: 80%; border: 1px solid gray; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); border-radius: 5px; background: linear-gradient(rgba(255, 255, 255, 0.7), rgba(255, 255, 255, 0.7)), url(../../assets/img/foodimg1.png) center/cover; color: black;}
  .diet_ {width: 50%; float: left;}
  #foodname, #calorie {border: none; width: 10rem; font-size: 20px; background-color: transparent; text-align: center;}
  #foodinfo {font-size: 1rem; width: 43%; overflow: scroll; word-wrap: break-word; background-color: transparent; border: none;}

  .chart {height: 80%; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); margin: 10% 0 30% 0; padding-top: 1%; background-color: white;}
  #activityChart, #weightChart {margin: 7%; width: 85%; border: 2px solid silver; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); background-color: #fff5f6;}
  h5 {margin: 3%;}
  .chartbtn button {background-color: transparent; font-size: 15px; color: gray; margin: 0; float: right; border: none;}
  .chartbtn button:hover {text-decoration: underline;}
  @media(max-width: 768px){.container-fluid{flex-direction: column; padding: 0;} #wrap{width: 100%;} .detail{width: 100%;} .chart{width: 100%;}}
</style>
<style scoped>
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .cal {
    display: flex;
    padding: 1%;
    width: 390px;
  }

  #wrap {
    margin: 1px;
    width: 100%;
    height: 100%;
    border: 1px solid white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    border-radius: 10px;
    overflow-x: auto; /* 좌우 스크롤을 추가합니다. */
    white-space: nowrap; /* 각 날짜가 다음 줄로 넘어가지 않도록 설정합니다. */
  }

 .fc-media-screen {
    width: 100%;
    height: 700px;
  }

  .fc-day {
    width: 90px; /* 각 날짜의 너비를 조절합니다. */
    background-color: #FFFFFF;
    display: inline-block; /* 각 날짜를 가로로 배열합니다. */
  }
}
</style>








    
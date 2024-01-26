<template>
  <div>
  <!-- 네비게이션 바 -->
  <Navbar />
    <div class="container-fluid pt-4" style="width: 80%;">
      <div class="row g-4">
        <div class="col-sm-6 col-xl-3">
          <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
            <img src="../assets/img/가병이.png" class="img-fluid rounded-circle"
              style="margin-left: 30%; width: 90px; height: 90px;/* border: 3px solid #d7d7d7; */">
            <!-- <div class="ms-3">
              <h6 class="mb-0 wetop">{{ this.memberinfo.name }}</h6>
            </div> -->
          </div>
        </div>
        <div class="col-sm-6 col-xl-3">
          <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
            <img src="../assets/img/main/weighticon.png" class="img-fluid" style="width: 90px;height: 90px;">
            <div class="ms-3">
              <p class="mb-2 wetop">{{ targetWeight }} kg</p>
              <h6 class="mb-0">목표 몸무게</h6>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3">
          <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
            <img src="../assets/img/main/kcalicon.png" class="img-fluid " style="width: 90px;height: 90px;">
            <div class="ms-3">
              <p class="mb-2 wetop">{{ totalCal }} kcal</p>
              <h6 class="mb-0">섭취 칼로리</h6>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3">
          <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
            <img src="../assets/img/main/kcalicon.png" class="img-fluid " style="width: 90px;height: 90px;">
            <div class="ms-3">
              <p class="mb-2 wetop">{{ targetCal }} Kcal</p>
              <h6 class="mb-0">추천 칼로리</h6>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 메인 배너 end -->

    <!-- 메인 컨텐츠 Start -->
    <div class="container-fluid pt-4 px-4 my-5" style="width: 80%;">
      <div class="row g-5 maincol pt-5 pb-5">
        <div class="col-lg-3 fadeInUp border-customend mainevent" data-wow-delay="0.1s">
          <div style="text-align: center;" class="mainline">
            <h4 class="mb-3 maintext">식단 관리</h4>
            <button @click="cameraGo" class="btnmain">촬영 및 기록</button>
            <!--   <a href="file:///C:/Users/user/Desktop/123/123/gardener-1.0.0/foodmemo.html"><button type="button"
                        class="btnmain">업로드</button></a>-->
          </div>
          <div style="text-align: center">
            <div class="d-flex flex-column align-items-center">
              <h4 @click="recommendeddietGO" class="mt-5 mb-3 maintext">추천 식단</h4>
              <button @click="foodmenu" type="button" class="btnmain">추천 식단 보기</button>
              <button @click="foodlist" type="button" class="btnmain">음식 리스트 보기</button>
            </div>
            <br>
            <div>
              <div class="d-flex flex-column align-items-center ">
                <h4 class="mt-5 mb-3 maintext">나만의 냉장고</h4>
                <button @click="refriGo" type="button" class="btnmain">냉장고 확인</button>
                <button @click="ingrePlusGo" type="button" class="btnmain">식재료 추가</button>
                <div class="mt-3 mx-5 d-flex align-items-center"
                  style="border: 2px solid #d7d7d7;border-radius: 8px;">
                  <img src="../assets/img/main/refrigerator.png" class="img-fluid"
                    style="margin: 0 auto; width: 200px; height: 180px;">
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--메인 컨텐츠 가운데 start-->
        <div class="col-lg-5 fadeInUp border-customend mainevent" data-wow-delay="0.3s">
          <div class="row">
            <div class="chart">
              <p class="highCal"> > 가장 높게 섭취한 칼로리</p>
              <div>
                <button class="datebtn" @click="changeTimeInterval('year')">연별</button>
                <button class="datebtn" @click="changeTimeInterval('month')">월별</button>
                <button class="datebtn" @click="changeTimeInterval('day')">일별</button>
              </div>
              <canvas ref="lineChart" id="LineChart"></canvas>
            </div>
          </div>
          <div class="row">
            <div>
              <div class="mt-3 d-flex flex-column align-items-center ">
                <!--<i class="fa fa-users fa-3x text-primary mb-3"></i>-->
                <p class="mb-2">kcal</p>
                <p class="mb-2">남은 칼로리</p>
                <button @click="calendarGo" type="button" class="btnmain" style="height: 50px;width: 150px;">캘린더가기</button>
                <button @click="boardGo" type="button" class="btnmain" style="height: 50px;width: 150px;">게시판가기</button>
              </div>
            </div>
          </div>
        </div>
        <!--메인 컨텐츠 가운데 end-->
        <!--메인 컨텐츠 프사 start-->
        <div class="col-lg-4 fadeInUp mainevent" data-wow-delay="0.5s">
          <div class="border-custom g-5 mt-2">
            <div class="col-12 col-sm-6 col-lg-12">
              <div class="ps-4 pb-3 d-flex align-items-center">
                <img src="../assets/img/가병이.png" class="img-fluid rounded-circle me-3"
                  style="width: 90px;height: 90px;/* border: 3px solid #d7d7d7; */">
                <div class="d-flex justify-content-center align-items-center">
                  <h4 class="maintext mb-0 me-4">히히히</h4>
                  <button @click="achievableGo" type="button" class="btnpromain" style="margin-bottom: 0px; min-width: 80px;">목표설정</button>
                </div>
                <!-- <div class="ms-3 d-flex flex-column">
                      <span>현재 체중: </span>
                      <span>목표 체중: </span>
                    </div> -->
              </div>
              <div class="mx-4 mb-4">
                <button @click="logoutGo" type="button" class="btnpromain" style="margin-right: 10px;" v-if="hasToken()">로그아웃</button>
                <button @click="memberUpdateGo" type="button" class="btnpromain" style="margin-right: 10px;">회원정보수정</button>
              </div>
            </div>
            <div class="col-12 col-sm-6 col-lg-12">
              <div class="mb-4 d-flex flex-column">
                <h4 class="mb-3 maintext" style="text-align: left;">Weight</h4>
                <img src="../assets/img/graex1.png" class="img-fluid">
              </div>
            </div>
            <div class="col-12 col-sm-6 col-lg-12">
              <div class="mb-4 d-flex flex-column">
                <h4 class="mb-3 maintext" style="text-align: left;">Kcal</h4>
                <img src="../assets/img/graex2.png" class="img-fluid">
              </div>
            </div>
          </div>
        </div>
      </div>
    <Footer />
    </div>
  </div>
</template>
<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../components/Footer/Footer.vue';
import axios from 'axios';
import Chart from 'chart.js/auto';
import moment from 'moment';
import 'chartjs-adapter-moment';

export default {
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      targetWeight: [], // 회원 몸무게(목표몸무게)
      totalCalList: [], // 총 칼로리(섭취칼로리)
      totalCal: [], // 당일 총 칼로리(섭취칼로리)
      targetCal: [], // 회원 키(cm)(추천칼로리)
      memberinfo: [],
      userId: '',
      member_no: '',
      lineChart: null,
      timeInterval: 'day',
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
  mounted(){
    const storedToken = localStorage.getItem('authToken');
    if (storedToken) {
      this.$store.commit('setAuthToken', storedToken);
    }
    if(this.hasToken()){
      console.log("토큰: " +this.$store.state.authToken);
    }else{
      this.$router.push('/');
    }
    const payloadBase64 = storedToken.split('.')[1];
    const decodedPayload = JSON.parse(atob(payloadBase64));
    this.userId = decodedPayload.sub;
    this.getMemberInfo(); // 회원 정보
    this.getHistWeight(); // 회원 몸무게
    this.getCalories(); // 당일 총 칼로리
    this.changeTimeInterval('day');
  },
  methods: {
    // store에 토큰 여부 확인
    hasToken(){ return this.$store.state.authToken !==null;},
    // 버튼 클릭 시 camera 페이지로 이동
    cameraGo() { 
      this.$router.push('/camera');
    },
    achievableGo() { // 목표설정 페이지로 이동
      this.$router.push('/achievable');
    },
    refriGo() { // 냉장고 식재료 페이지로 이동
      this.$router.push('/refrigeratorcheck');
    },
    ingrePlusGo() { // 식재료 추가 페이지로 이동
      this.$router.push('/ingredientsplus');
    },
    logoutGo() { // 로그아웃으로 이동
      this.$store.dispatch('logout');
      this.$router.push('/');
      console.log("logout 토큰: " + this.$store.state.authToken);
    },
    memberUpdateGo() { // 회원정보 수정 페이지로 이동
      this.$router.push('/memberupdate');
    },
    calendarGo() { // 캘린더 페이지로 이동
      this.$router.push('/calendar');
    },
    boardGo() { //게시판 페이지로 이동
      this.$router.push('/boardList');
    },
    recommendeddietGO() { // 추천음식 페이지로 이동
      this.$router.push('/recommendeddiet');
    },
    showFood() { // 식단 페이지 이동
      this.$router.push('/foodmenu');
    },
    foodmenu() { // 추천 식단 페이지 이동
      this.$router.push('/foodmenu');
    },
    foodlist() { // 음식 리스트 페이지 이동
      this.$router.push('/recommendeddiet');
    },
    getMemberInfo() {
      axios.get(`http://localhost/project/api/v1/auth/getmemberinfo?user_id=${this.userId}`)
      .then((resp) => {
        this.memberinfo = resp.data;
        this.targetCal = (this.memberinfo.height - 100) * 0.9 * 33;
        this.member_no = this.memberinfo.member_no;
      })
      .catch((error) => {
        console.log(error);
      })
    },
    getHistWeight() { // 회원 몸무게
      axios.get(`http://localhost/project/api/v1/auth/gethistweight?member_no=1`)
        .then((resp) => {
          const getweight = resp.data;
          console.log('-----getweight-----');
          console.log(getweight);
          this.targetWeight = getweight.hist_futureweight - getweight.hist_currentweight;
        })
        .catch((error) => {
          console.log(error);
        })
    },
    getCalories() { // 당일 총 칼로리
      const nowDate = this.currentDate;
      axios.get(`http://localhost/project/api/v1/auth/totalCalbyId?user_id=${this.userId}`)
        .then((resp) => {
          this.totalCalList = resp.data;
          console.log(this.totalCalList);
          let foundTotalCal = null;
          for (const item of this.totalCalList) {
            const extractedDate = moment(item.created_at).format('YYYY-MM-DD');
            if (extractedDate === nowDate) {
              foundTotalCal = item.total_calories;
              break;
            }
          }
          if (foundTotalCal !== null) {
            this.totalCal = foundTotalCal;
          } else {
            this.totalCal = 0;
          }

          const labels = this.totalCalList.map(item => moment(item.created_at).format('YYYY-MM-DD'));
          const data = this.totalCalList.map(item => item.total_calories);
          this.createLineChart(labels, data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    changeTimeInterval(interval) {
      this.timeInterval = interval;
      this.updateChart();
    },
    updateChart() {
      const labels = this.totalCalList.map(item => moment(item.created_at).format('YYYY-MM-DD'));
      const data = this.totalCalList.map(item => item.total_calories);
      const unitMapping = {
        year: 'year',
        month: 'month',
        day: 'day',
      };
      const timeUnit = unitMapping[this.timeInterval];
      this.createLineChart(labels, data, timeUnit);
    },
    createLineChart(labels, data, timeUnit) {
      this.$nextTick(() => {
        const ctx = this.$refs.lineChart.getContext('2d');

        if (this.lineChart) {
          this.lineChart.destroy();
        }
        const gradient = ctx.createLinearGradient(0, 0, 0, this.$refs.lineChart.clientHeight);
        gradient.addColorStop(0, 'rgba(75, 192, 192, 1)');
        gradient.addColorStop(1, 'rgba(255, 255, 255, 0.5)');

        this.lineChart = new Chart(ctx, {
          type: 'line',
          data: {
            labels: labels,
            datasets: [{
              label: 'Total Calories',
              borderColor: 'rgba(75, 192, 192, 1)',
              backgroundColor: gradient,
              data: data,
              lineTension: 0.3,
              fill: true,
            }],
          },
          options: {
            responsive: true,
            scales: {
              x: {
                type: 'time',
                time: {
                  tooltipFormat: 'll',
                  unit: this.timeInterval,
                  displayFormats: {
                    year: 'YYYY',
                    month: 'MMM YYYY',
                    day: 'll',
                  },
                },
              },
              y: {
                beginAtZero: true,
                grid: {
                  display: false,
                },
              },
            },
            elements: {
              line: {
                tension: 0.3,
              },
            },
          },
        });  
      });
    }
  },
}
</script>

<style scoped>
.wetop {font-size: 20px; font-weight: bold;}
.highCal {margin-bottom: 0; text-align: left;}
.datebtn {float: right; border: 1px solid #A5D299; border-radius: 10px; background-color: transparent;}
.datebtn:hover {color: #A5D299;}
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .btnpromain{
    margin-left: 20px;
  }
}
</style>
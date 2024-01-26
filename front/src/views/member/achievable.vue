<template>
  <div class="maincontainer">
    <Navbar />
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
      <div class="container text-center py-3">
        <h3 class="display-5 text-white mb-2 animated slideInDown">{{ id }}님의 목표 설정</h3>
      </div>
    </div>
    <div class="container-xxl py-5">
      <div class="container-1 mt-5">
        <div class="row g-5 align-items-stretch maincol pb-4 col-5 d-flex justify-content-center">
          <div class="col-12 text-center">
            <div>
              <p>현재 몸무게: {{ weight }} kg</p>
              <p>목표 몸무게: {{ futureweight }} kg</p>
              <p :class="{'green-text': weightDifference >= 0, 'red-text': weightDifference < 0}">
                목표까지 : {{ weightDifference }} kg
                <span v-if="weightDifference !== 0" class="arrow">{{ weightDifference > 0 ? '↑' : '↓' }}</span>
              </p>
            </div>
            <div class="d-flex align-items-center mb-3">
              <div class="input-group">
                <input v-model="currentweight" type="number" class="form-control" placeholder="현재 몸무게(kg)">
                <input v-model="futureweight" type="number" class="form-control" placeholder="목표 몸무게(kg)">
              </div>
            </div>
            <button @click="Save" type="submit" class="btn btn-primary mx-2 btnall">저장하기</button>
          </div>
        </div>
      </div>
      <div class="weight-chart-container">
        <canvas ref="lineChartCanvas"></canvas>
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from 'axios';
import { Chart, registerables } from 'chart.js';

export default {
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      weight: 0,
      currentweight: null,
      futureweight: null,
      id: 'test',
      chart: null // 차트 인스턴스
    };
  },
  computed: {
    weightDifference() {
      return this.futureweight - this.currentweight;
    },
  },
  mounted() {
    Chart.register(...registerables);
    this.searchWeight();
    this.searchfweight();
    this.fetchGraphData();
  },
  methods: {
    searchWeight() {
      axios.get('http://localhost/project/api/v1/auth/weight', {
        params: {
          member_no: 1
        }
      })
      .then((response) => {
        this.weight = response.data.weight;
      })
      .catch((error) => {
        console.error('데이터 가져오기 실패:', error);
      });
    },
    searchfweight() {
      axios.get('http://localhost/project/api/v1/auth/fweight', {
        params: {
          member_no: 1
        }
      })
      .then((response) => {
        this.futureweight = response.data.futureweight;
      })
      .catch((error) => {
        console.error('데이터 가져오기 실패:', error);
      });
    },
    Save() {
      axios.get('http://localhost/project/api/v1/auth/lastWeightEntry', {
        params: {
          member_no: 1
        }
      })
      .then((response) => {
        const lastEntryDate = new Date(response.data.wdate);
        const currentDate = new Date();
        const oneMonthAgo = new Date();
        oneMonthAgo.setMonth(currentDate.getMonth() - 1);
        if (lastEntryDate > oneMonthAgo) {
          alert('한 달 이내에 이미 데이터가 입력되어 있습니다.');
        } else {
          this.updateweight();
          axios.post('http://localhost/project/api/v1/auth/upweight', {
            member_no: 1,
            currentweight: this.currentweight,
            futureweight: this.futureweight,
          })
          .then((res) => {
            console.log('데이터 저장 성공:', res.data);
          })
          .catch(error => {
            console.error('데이터 저장 실패:', error);
          });
        }
      })
      .catch((error) => {
        console.error('마지막 입력 날짜 확인 실패:', error);
      });
    },
    fetchGraphData() {
      axios.get('http://localhost/project/api/v1/auth/logweight', {
        params: {
          member_no: 1
        }
      })
      .then((response) => {
        console.log(response.data)
    const logData = response.data;
    // 현재 몸무게의 날짜 배열을 생성합니다.
    const labels = logData.map(log => log.change_date);

    // 현재 몸무게와 목표 몸무게의 데이터를 생성합니다.
    // 현재 몸무게의 날짜에 맞춰 목표 몸무게 데이터를 조정합니다.
    const currentWeights = logData.map(log => ({
      x: log.change_date,
      y: log.hist_currentweight
    }));
    const futureWeights = logData.map(log => ({
      x: log.change_date,
      y: log.previous_futureweight 
    }));

    const chartData = {
      labels,
      datasets: [
        {
          label: '현재 몸무게',
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          data: currentWeights,
          fill: false,
        },
        {
          label: '목표 몸무게',
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          data: futureWeights,
          fill: false,
        }
      ]
    };

        const chartOptions = {
          responsive: true,
          maintainAspectRatio: false
        };

        if (this.chart) {
          this.chart.destroy();
        }

        this.chart = new Chart(this.$refs.lineChartCanvas, {
          type: 'line',
          data: chartData,
          options: chartOptions
        });
      })
      .catch((error) => {
        console.error('차트 데이터 가져오기 실패:', error);
      });
    },
    Cancel() {
      console.log('취소 버튼이 클릭되었습니다.');
    },
    updateweight() {
      axios.post('http://localhost/project/api/v1/auth/updateweight', {
        member_no: 1,
        currentweight: this.currentweight,
      })
      .then((res) => {
        console.log('데이터 저장 성공:', res.data);
      })
      .catch(error => {
        console.error('데이터 저장 실패:', error);
      });
    }
  }
};
</script>

<style scoped>
.green-text {
  color: green;
}

.red-text {
  color: red;
}

.arrow {
  font-size: 0.8em;
  margin-left: 5px;
}

.container-1 {
  justify-content: center;
  display: flex;
}

@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .container-1 {
    display: block;
  }
  .container-xxl {
    width: 850px;
    height: auto;
    margin-left: 36px;
  }
  .maincontainer {
    overflow: hidden;
  }
}

.weight-chart-container {
  height: 400px;
}
</style>
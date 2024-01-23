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
            <p>현재 몸무게: {{ currentWeight }} kg</p>
            <p>목표 몸무게: {{ futureWeight }} kg</p>
            <p :class="{ 'green-text': weightDifference >= 0, 'red-text': weightDifference < 0 }">
              목표까지 : {{ weightDifference }} kg
              <span v-if="weightDifference !== 0" class="arrow">{{ weightDifference > 0 ? '↑' : '↓' }}</span>
            </p>
          </div>
          <div class="d-flex align-items-center mb-3">
            <div class="input-group">
              <input v-model="targetWeight" type="number" class="form-control" placeholder="월 목표 몸무게(kg)">
              <input v-model="targetWeight" type="number" class="form-control" placeholder="현재 몸무게(kg)">
            </div>
          </div>
          <button @click="Save" type="submit" class="btn btn-primary mx-2 btnall">저장하기</button>
                    <!-- cors통신 확인용-->
          <button @click="upload" type="button" class="btn btn-primary mx-2 btnall">불러오기</button>
          <button @click="Cancel" type="button" class="btn btn-primary mx-2 btnall">취소</button>
        </div>
      </div>
    </div>
  </div>
  <Footer /> 
  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from 'axios';

export default {
  components: {
    Navbar,
    Footer,
  },
  // 컴포넌트 데이터 속성 정의 및 초기화
  data() {
    return {
      currentWeight: '50',
      futureWeight: '60',
      id: 'a',
    };
  },
  // 동적 데이터 업데이트
  computed: {
    weightDifference() {
      return this.futureWeight - this.currentWeight;
    },
  },
  // 컴포넌트가 마운트된 후 호출
  created() {
    this.getDataForm();
  },
  // 버튼 클릭 이벤트
  methods: {
    // 업로드이건 그냥 cors통신 확인용
    upload(){
      axios.get("http://192.168.0.4/project/api/v1/auth/weightlist",{
        id : this.id,
        currentWeight: this.currentWeight,
        futureWeight: this.futureWeight,
      })
      .then((res) => {
        console.log('불러오기:', res.data);
        this.id = res.data.id
        this.currentWeight = res.data.currentWeight
        this.futureWeight = res.data.futureWeight
      })
      .catch(error => {
        console.error('불러오기 실패:', error);
      });
    },
    // 여기까지 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    Save() {
      axios.post('http://192.168.0.4/project/api/v1/auth/upweight', {
        id : this.id,
        currentWeight: this.currentWeight,
        futureWeight: this.futureWeight,
      })
      .then((res) => {
        console.log('데이터 저장 성공:', res.data);
        this.id = res.data.id
        this.currentWeight = res.data.currentWeight
        this.futureWeight = res.data.futureWeight
      })
      .catch(error => {
        console.error('데이터 저장 실패:', error);
      });
    },
    Cancel() {
      console.log('취소 버튼이 클릭되었습니다.');
    },
    getDataForm() {
      axios.get('http://192.168.0.4/project/api/v1/auth/weightlist')
      .then((res) => {
        this.currentWeight = res.data.currentWeight;
        this.futureWeight = res.data.futureWeight;
        this.id = res.data.id;
      })
      .catch(error => {
        console.error('데이터 가져오기 실패:', error);
      });
    },
  },
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
.container-1{
    justify-content: center;
    display: flex;
  }
</style>

<style scoped>  
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .container-1{
    display: block;
  }
  .container-xxl {
    width: 850px;
    height: auto;
    margin-left: 36px;
    
  }
  .maincontainer{
    overflow: hidden ; /* 세로 스크롤이 생기지 않도록 함 */
  }
}

</style>
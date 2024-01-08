<template>
  <div>
  <Navbar /> 
  <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
  <div class="container text-center py-3">
    <h3 class="display-5 text-white mb-2 animated slideInDown">빵빵이님의 목표 설정</h3>
  </div>
</div>
  <div class="container-xxl py-5">
    <div class="container mt-5 d-flex justify-content-center">
      <div class="row g-5 align-items-stretch maincol pb-4 col-5 d-flex justify-content-center">
        <div class="col-12 text-center">
          <img src="../../assets/img/icon/achieset.png" class="img-fluid mb-3" />
          <div class="d-flex align-items-center mb-3">
            <div class="input-group">
              <input v-model="targetWeight" type="number" class="form-control" placeholder="월 목표 몸무게(kg)">
              <div class="input-group-append">
                <button @click="Change" type="button" class="btnall" style="margin-bottom: 0px;">변경</button>
              </div>
            </div>
          </div>
          <button @click="Save" type="submit" class="btn btn-primary mx-2 btnall">저장하기</button>
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

export default {
  components:{
      Navbar,
      Footer,
  },
  data() {
    return {
      targetWeight: null,
    };
  },
  methods: {
    Save() {
      console.log('저장하기 버튼이 클릭되었습니다. 목표 몸무게:', this.targetWeight);
    },
    Cancel() {
      console.log('취소 버튼이 클릭되었습니다.');
    },
    Change() {
      console.log('변경 버튼이 클릭되었습니다. 목표 몸무게:', this.targetWeight);
},
  },
};
</script>
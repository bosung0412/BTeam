<template>
    <div>
            <!-- 네비게이션 바 시작 -->
            <Navbar />
    <!-- 네비게이션 바 끝 -->
        <div class="container-fluid pt-4" style="width: 80%;">
            <div class="row g-4">
                <div class="col-sm-6 col-xl-3">
                    <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
                        <img src="../assets/img/pork.jpg" class="img-fluid rounded-circle"
                            style="width: 90px;height: 90px;/* border: 3px solid #d7d7d7; */">
                        <div class="ms-3">
                            <p class="mb-2">이달의 BMI 랭킹?</p>
                            <h6 class="mb-0">닉네임</h6>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-xl-3">
                    <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
                        <img src="../assets/img/main/weighticon.png" class="img-fluid" style="width: 90px;height: 90px;">
                        <div class="ms-3">
                            <p class="mb-2">-5kg</p>
                            <h6 class="mb-0">목표 몸무게</h6>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-xl-3">
                    <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">

                        <img src="../assets/img/main/kcalicon.png" class="img-fluid " style="width: 90px;height: 90px;">
                        <div class="ms-3">
                            <p class="mb-2">444Kal</p>
                            <h6 class="mb-0">섭취 칼로리</h6>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-xl-3">

                    <div class="maintopcol rounded d-flex align-items-center justify-content-between p-4">
                        <img src="../assets/img/main/kcalicon.png" class="img-fluid " style="width: 90px;height: 90px;">
                        <div class="ms-3">
                            <p class="mb-2">3000Kal</p>
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
                        <div class="d-flex flex-column mainline">

                            <h4 @click="recommendeddietGO" class="mb-3 maintext">추천 식단 정보</h4>
                            <p  class="mb-2 maintext">1.추천식단</p>
                            <p class="mb-2 maintext">2.추천식단</p>
                            <p class="mb-2 maintext">3.추천식단</p>
                            <div class="mt-2 mx-5 d-flex align-items-center"
                                style="border: 2px solid #d7d7d7;border-radius: 8px;">
                                <img src="../assets/img/food3.png" class="img-fluid"
                                style="margin: 0 auto;width: 100px;height: 100px;">
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="d-flex flex-column align-items-center ">
                            <h4 class="mb-3 maintext">나만의 냉장고</h4>
                            <button @click="refriGo" type="button" class="btnmain">냉장고 확인</button>
                            <button @click="ingrePlusGo" type="button" class="btnmain">식재료 추가</button>
                            <div class="mt-3 mx-5 d-flex align-items-center"
                                style="border: 2px solid #d7d7d7;border-radius: 8px;">
                                <img src="../assets/img/main/refrigerator.png" class="img-fluid"
                                    style="margin: 0 auto;width: 200px;height: 180px;">
                            </div>
                        </div>
                    </div>
                </div>
                <!--메인 컨텐츠 가운데 start-->
                <div class="col-lg-5 fadeInUp border-customend mainevent" data-wow-delay="0.3s">
                <div class="row">
                        <div id="datepicker">
                            <img src="../assets/img/calex.png" class="img-fluid"
                            style="width: 90%;box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);" />
                        </div>
                    </div>
                    <div class="row">
                        <div>
                            <div class="mt-3 d-flex flex-column align-items-center ">
                                <!--<i class="fa fa-users fa-3x text-primary mb-3"></i>-->
                                <p class="mb-2">kcal</p>
                                <p class="mb-2">남은 칼로리</p>

                                    <button @onclick="calendarGo" type="button" class="btnmain" style="height: 50px;width: 150px;">캘린더가기</button>
            
                                    <button @onclick="boardGo" type="button" class="btnmain" style="height: 50px;width: 150px;">게시판가기</button>
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
                                <img src="../assets/img/pork.jpg" class="img-fluid rounded-circle me-3"
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
        </div>
    <Footer />
    </div>
</template>
<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../components/Footer/Footer.vue';
export default {
    comments: {
        Navbar,
        Footer,
    },
    data() {
        return {
            
        };
    },
    mounted(){
       if(this.hasToken()){
         console.log("토큰: " +this.$store.state.authToken);
       }else{
        this.$router.push('/');
       }
    },
    methods: {
        //store에 토큰 여부 확인
        hasToken(){
            return this.$store.state.authToken !==null;
        },
        cameraGo() {
            //버튼 클릭 시 camera 페이지로 이동
            this.$router.push('/camera');
        },
        achievableGo(){
            //목표설정 페이지로 이동
            this.$router.push('/achievable');
        },
        //냉장고 식재료 페이지로 이동
        refriGo() {
            this.$router.push('/refrigeratorcheck');
        },
        //식재료 추가 페이지로 이동
        ingrePlusGo() {
            this.$router.push('/ingredientsplus');
        },
        //로그아웃으로 이동
        logoutGo(){
            this.$store.dispatch('logout');
            this.$router.push('/');
            console.log("logout 토큰: " + this.$store.state.authToken);
        },
        //회원정보 수정 페이지로 이동
        memberUpdateGo(){
            this.$router.push('/memberupdate');
        },
        //캘린더 페이지로 이동
        calendarGo(){
            this.$router.push('');
        },
        //게시판 페이지로 이동
        boardGo(){
            this.$router.push('');
        },
        //추천식단 페이지로 이동
        recommendeddietGO(){
            this.$router.push('/recommendeddiet');
        }
    },
}
</script>

<style scoped>  
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .btnpromain{
    margin-left: 20px;
  }
}

</style>
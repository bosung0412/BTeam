<template>
    <div>
        <Navbar />
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
        <div class="container text-center py-3">
            <h3 class="display-5 text-white mb-2 animated slideInDown">게시글 수정</h3>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Projects Start -->
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <div class="bg-light rounded p-4">
                    <div id="app">
                        <label for="username" class="form-label">작성자 아이디</label>
                        <input type="text" class="form-control mb-3" id="username" name="username" value="사용자 아이디" readonly>

                        <label for="title" class="form-label">글 제목</label>
                        <input type="text" class="form-control mb-3" id="title" name="title" placeholder="글 제목을 입력하세요">

                <label for="title" class="form-label">글 부제목</label>
                        <input type="text" class="form-control mb-3" id="title" name="title" placeholder="글 부제목을 입력하세요">

                        <label for="content" class="form-label">글 내용</label>
                        <textarea class="form-control" id="content" name="content" rows="5" placeholder="글 내용을 입력하세요"></textarea>

                        <button onclick="window.location.href='boarddetail.html'" type="button" class="btn btn-primary mt-3">글 수정</button>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
    <!-- Projects End -->
    <!-- Footer Start -->
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
}
</script>
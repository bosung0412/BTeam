<template>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gardener - Gardening Website Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <div id="app">
        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg navbar-light sticky-top">
            <router-link to="/" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <img src="img/samplelogo.png" style="width: 70px;height: 70px;top: 0;left: 10px;" />
            </router-link>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse"
                data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <router-link to="/calendar" class="nav-item nav-link">캘린더</router-link>
                    <router-link to="/board" class="nav-item nav-link">게시판</router-link>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <!-- Page Header Start -->
        <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
            <div class="container text-center py-5">
                <h1 class="display-5 text-white mb-4 animated slideInDown">냉장고 식재료</h1>
                <nav aria-label="breadcrumb animated slideInDown"></nav>
            </div>
        </div>
        <!-- Page Header End -->

        <!-- Projects Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="row wow fadeInUp" data-wow-delay="0.3s">
                    <div class="col-12 text-center">
                        <ul class="list-inline rounded mb-5" id="portfolio-flters">
                            <li class="mx-2 active" data-filter="*">전체</li>
                            <li class="mx-2" data-filter=".vegetable">야채</li>
                            <li class="mx-2" data-filter=".meat">육류</li>
                            <li class="mx-2" data-filter=".fruit">과일</li>
                            <li class="mx-2" data-filter=".nuts">견과류</li>
                        </ul>
                    </div>
                </div>

                
            </div>
        </div>
        <!-- Projects End -->

        <div class="container">
            <div class="row mt-4">
                <div class="col-12 text-center">
                    <p id="category-count"></p>
                </div>
            </div>
        </div>
        <!-- Copyright Start -->
        <div class="container-fluid copyright py-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        &copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        <!-- You can keep the credit link here if required -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->
    </div>
</body>
</template>
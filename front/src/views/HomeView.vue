<template>
  <div class="app">
    <section class="u-clearfix u-image u-shading u-section-1" id="sec-오전 1:55 2024-01-053fd3" data-image-width="250" data-image-height="168">
      <div class="u-clearfix u-sheet u-sheet-1">
        <h1 class="u-align-center u-text u-text-body-color u-text-default u-text-1">냉장고든램지</h1>
        <h3 class="u-align-center u-text u-text-body-color u-text-default u-text-2">AI와 함께 음식 칼로리를 쉽게 알 수 있어요</h3>
        <button @click="NextGo" class="u-border-none u-btn u-button-style u-custom-color-3 u-hover-custom-color-5 u-radius u-btn-1" data-animation-name="customAnimationIn" data-animation-duration="1000" data-animation-direction="">Next&nbsp;</button>
      </div>
    </section>
    
    <Footer />
  </div>
</template>

<script>
import Footer from '@/components/Footer/Footer.vue';

export default {
  components: {
    Footer,
  },
  methods: {
    NextGo() {
      this.$router.push('/login');
    },
  },
};
</script>

<style scoped>
.app {
  display: flex;
  flex-direction: column;
  min-height: 100vh; /* Set the minimum height of the app to fill the entire viewport */
}

.u-clearfix {
  flex: 1; /* Make the section take up the remaining space */
}

/* Add any additional styling for .u-clearfix if needed */

</style>

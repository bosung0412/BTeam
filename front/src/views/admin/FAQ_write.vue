<template>
  <div>
    <Navbar />
    <div class="faq-write-container">
      <h2>Write FAQ</h2>
      <form @submit.prevent="submitFAQ">
        <div class="form-group">
          <label for="faqName">FAQ Name:</label>
          <input v-model="faqData.oname" type="text" id="faqName" class="form-control" required />
        </div>

        <div class="form-group">
          <label for="faqContent">Content:</label>
          <textarea v-model="faqData.ocontent" id="faqContent" class="form-control" required></textarea>
        </div>

        <div class="form-group">
          <label for="faqCategory">Category:</label>
          <select v-model="faqData.ocategory" id="faqCategory" class="form-control" required>
            <option value="" disabled selected>카테고리를 선택해주세요</option>
            <option value="web">Web</option>
            <option value="app">App</option>
            <option value="user">User</option>
          </select>
        </div>

        <button type="submit" class="btn btn-primary">Submit FAQ</button>
      </form>
    </div>
    <Footer />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '@/components/Footer/Footer.vue';
import axios from 'axios'; 

export default {
  data() {
    return {
      faqData: {
        oname: "",
        ocontent: "",
        ocategory: "", // Default to empty string
      },
    };
  },
  methods: {
    async submitFAQ() {
      try {
        console.log('Submitting FAQ:', this.faqData);

        const response = await axios.post('http://localhost/project/api/admin/faqboard', this.faqData);

        console.log('FAQ created:', response.data);

        this.faqData = {
          oname: "",
          ocontent: "",
          ocategory: "", // Reset category to empty string
        };

        this.$router.push('/adminpage');
      } catch (error) {
        console.error('Error creating FAQ:', error);
      }
    },
  },
  components: {
    Navbar,
    Footer,
  },
};
</script>

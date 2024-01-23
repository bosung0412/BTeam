<template>
    <div>
      <h1>FAQ List</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Member</th>
            <th>Name</th>
            <th>Content</th>
            <th>Reg Date</th>
            <th>Category</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="faq in faqs" :key="faq.id">
            <td>{{ faq.id }}</td>
            <td>{{ faq.member.name }}</td>
            <td>{{ faq.name }}</td>
            <td>{{ faq.content }}</td>
            <td>{{ faq.regDate }}</td>
            <td>{{ faq.category }}</td>
            <td>
              <router-link :to="{ name: 'faq', params: { id: faq.id }}">View</router-link>
              <button @click="deleteFAQ(faq.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
      <router-link to="/create-faq">Create New FAQ</router-link>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        faqs: [],
      };
    },
    mounted() {
      this.fetchFAQs();
    },
    methods: {
      fetchFAQs() {
        axios.get('http://192.168.0.4/project/api/faqs')
          .then(response => {
            this.faqs = response.data;
          })
          .catch(error => {
            console.error('Error fetching FAQs', error);
          });
      },
      deleteFAQ(id) {
        axios.delete(`http://192.168.0.4/project/api/faqs/${id}`) // Spring Boot API 경로에 맞게 수정
          .then(() => {
            this.fetchFAQs();
          })
          .catch(error => {
            console.error(`Error deleting FAQ ${id}`, error);
          });
      },
    },
  };
  </script>
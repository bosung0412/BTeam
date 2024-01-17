import axios from 'axios';

// axios 인스턴스 생성
const instance = axios.create({
  baseURL: '', // API 서버의 기본 URL
  timeout: 1000,
  // 기타 필요한 설정
});

// 요청 인터셉터
instance.interceptors.request.use(function (config) {
  // 요청이 전송되기 전에 일어날 작업
  const token = sessionStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, function (error) {
  // 요청 에러 처리
  return Promise.reject(error);
});

// 응답 인터셉터
instance.interceptors.response.use(function (response) {
  // 응답 데이터를 처리
  return response;
}, function (error) {
  // 응답 에러 처리
  return Promise.reject(error);
});

export default instance;
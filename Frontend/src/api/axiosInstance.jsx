import axios from 'axios';

// Prefer ending VITE_API_BASE_URL with NO trailing slash for consistency
const BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

const axiosInstance = axios.create({
  baseURL: BASE_URL, // e.g. http://localhost:8081
  headers: {
    'Content-Type': 'application/json',
  },
});

// Always attach token if present
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('auth_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;

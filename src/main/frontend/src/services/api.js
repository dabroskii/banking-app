import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080',
});

// Add JWT token to requests if available and not for login/register
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('jwt');
    if (token && !config.url.includes('/auth/')) { // Exclude auth endpoints
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default api;

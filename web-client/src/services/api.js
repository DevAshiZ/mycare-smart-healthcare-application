import axios from "axios";

// Create an Axios instance
const api = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-Type": "application/json",
  },
});

// Add a request interceptor to attach the token
api.interceptors.request.use(
    (config) => {
      // Get the persisted root from localStorage
      const persistedState = localStorage.getItem('persist:root');

      if (persistedState) {
        // Parse the persisted state
        const authState = JSON.parse(persistedState).auth;

        // If the authState exists, parse it to get the token
        if (authState) {
          const auth = JSON.parse(authState); // auth contains user, token, and role

          // Attach the token to the Authorization header if available
          const token = auth.token;
          if (token) {
            config.headers.Authorization = `Bearer ${token}`;
          }
        }
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
);

export default api;

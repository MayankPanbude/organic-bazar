// src/api/authApi.js

import axiosInstance from "./axiosInstance";

export const loginUser = async (email, password) => {
  const response = await axiosInstance.post("api/users/auth/login", {
    email,
    password,
  });

  return response.data;
};


export const registerUser = async (data) => {
  const res = await axiosInstance.post("/api/users/auth/register", data);
  return res.data;
};

// src/api/userApi.js

import axiosInstance from "./axiosInstance";

export const getAllUsers = async () => {
  const res = await axiosInstance.get("/api/users");
  return res.data;
};

export const deleteUser = async (id) => {
  await axiosInstance.delete(`/api/users/${id}`);
};

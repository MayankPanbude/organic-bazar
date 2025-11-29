// src/api/productApi.js

import axiosInstance from "./axiosInstance";

export const getAllProducts = async () => {
  const res = await axiosInstance.get("/api/products");
  return res.data;
};

export const addProduct = async (data) => {
  const res = await axiosInstance.post("/api/products", data);
  return res.data;
};

export const getMyProducts = async () => {
  const res = await axiosInstance.get("/api/products/my");
  return res.data;
};

export const deleteProduct = async (id) => {
  await axiosInstance.delete(`/api/products/${id}`);
};

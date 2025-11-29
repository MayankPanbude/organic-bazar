// src/api/orderApi.js

import axiosInstance from "./axiosInstance";

export const getCustomerOrders = async () => {
  const res = await axiosInstance.get("/api/orders/customer");
  return res.data;
};

export const getFarmerOrders = async () => {
  const res = await axiosInstance.get("/api/orders/farmer/orders");
  return res.data;
};

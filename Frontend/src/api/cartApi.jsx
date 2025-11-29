// src/api/cartApi.js

import axiosInstance from "./axiosInstance";

export const getCartItems = async () => {
  const res = await axiosInstance.get("/api/cart");
  return res.data;
};

export const addToCart = async (productId, quantity = 1) => {
  const res = await axiosInstance.post("/api/cart", {
    productId,
    quantity,
  });
  return res.data;
};

export const removeFromCart = async (productId) => {
  await axiosInstance.delete(`/api/cart/${productId}`);
};

export const placeOrder = async () => {
  const res = await axiosInstance.post("/api/cart/place-order");
  return res.data;
};

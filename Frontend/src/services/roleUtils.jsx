// src/services/roleUtils.js

import { getUserFromToken } from "./authService";

export const getRole = () => {
  const user = getUserFromToken();
  return user?.role || null;
};

export const isAdmin = () => getRole() === "ADMIN";
export const isFarmer = () => getRole() === "FARMER";
export const isCustomer = () => getRole() === "CUSTOMER";

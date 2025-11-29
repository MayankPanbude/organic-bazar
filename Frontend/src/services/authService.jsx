// src/services/authService.js

import { getToken } from "./tokenService";

export const parseJwt = (token) => {
  try {
    return JSON.parse(atob(token.split(".")[1]));
  } catch (e) {
    return null;
  }
};

export const getUserFromToken = () => {
  const token = getToken();
  if (!token) return null;

  const payload = parseJwt(token);
  if (!payload) return null;

  return {
    email: payload.sub,
    role: payload.role,
    exp: payload.exp,
  };
};

export const isLoggedIn = () => {
  const user = getUserFromToken();
  return !!user && Date.now() < user.exp * 1000;
};

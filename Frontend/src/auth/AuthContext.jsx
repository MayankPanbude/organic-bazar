// src/auth/AuthContext.jsx

import React, { createContext, useEffect, useState } from "react";
import { setToken, getToken, removeToken } from "../services/tokenService";
import { getUserFromToken } from "../services/authService";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [token, setAuthToken] = useState(getToken());
  const [user, setUser] = useState(getUserFromToken());

  useEffect(() => {
    if (token) {
      setUser(getUserFromToken());
    }
  }, [token]);

  const login = (jwt) => {
    setToken(jwt);
    setAuthToken(jwt);
    setUser(getUserFromToken());
  };

  const logout = () => {
    removeToken();
    setAuthToken(null);
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ token, user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

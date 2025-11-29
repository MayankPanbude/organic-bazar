// src/auth/PrivateRoute.jsx

import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "./AuthContext";

const PrivateRoute = ({ children, allowedRoles }) => {
  const { token, user } = useContext(AuthContext);

  if (!token) return <Navigate to="/login" />;
  if (!allowedRoles.includes(user?.role)) return <Navigate to="/not-found" />;

  return children;
};

export default PrivateRoute;

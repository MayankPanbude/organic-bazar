// src/App.jsx

import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

import Home from "./pages/common/Home";
import Login from "./pages/common/Login";
import Register from "./pages/common/Register";
import NotFound from "./pages/common/NotFound";

import AdminDashboard from "./pages/admin/AdminDashboard";
import ManageUsers from "./pages/admin/ManageUsers";
import AllProducts from "./pages/admin/AllProducts";

import FarmerDashboard from "./pages/farmer/FarmerDashboard";
import AddProduct from "./pages/farmer/AddProduct";
import MyProducts from "./pages/farmer/MyProducts";
import FarmerOrders from "./pages/farmer/Orders";

import CustomerDashboard from "./pages/customer/CustomerDashboard";
import Cart from "./pages/customer/Cart";
import CustomerOrders from "./pages/customer/Orders";

import PrivateRoute from "./auth/PrivateRoute";
import ManageCategories from "./pages/admin/ManageCategory";
import UpdateProduct from "./pages/farmer/UpdateProduct";
import PaymentDemo from "./pages/customer/PaymentDemo";

const App = () => {
  return (
    <Router>
      <Navbar />
      <main className="min-h-screen p-4">
        <Routes>
          {/* Common */}
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          {/* Admin */}
          <Route
            path="/admin/dashboard"
            element={
              <PrivateRoute allowedRoles={["ADMIN"]}>
                <AdminDashboard />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/users"
            element={
              <PrivateRoute allowedRoles={["ADMIN"]}>
                <ManageUsers />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/products"
            element={
              <PrivateRoute allowedRoles={["ADMIN"]}>
                <AllProducts />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/categories"
            element={
              <PrivateRoute allowedRoles={["ADMIN"]}>
                <ManageCategories />
              </PrivateRoute>
            }
          />

          {/* Farmer */}
          <Route
            path="/farmer/dashboard"
            element={
              <PrivateRoute allowedRoles={["FARMER"]}>
                <FarmerDashboard />
              </PrivateRoute>
            }
          />
          <Route
            path="/farmer/add-product"
            element={
              <PrivateRoute allowedRoles={["FARMER"]}>
                <AddProduct />
              </PrivateRoute>
            }
          />
          <Route
            path="/farmer/my-products"
            element={
              <PrivateRoute allowedRoles={["FARMER"]}>
                <MyProducts />
              </PrivateRoute>
            }
          />
          <Route
            path="/farmer/orders"
            element={
              <PrivateRoute allowedRoles={["FARMER"]}>
                <FarmerOrders />
              </PrivateRoute>
            }
          />
          <Route
            path="/farmer/update-product/:id"
            element={
              <PrivateRoute allowedRoles={["FARMER"]}>
                <UpdateProduct />
              </PrivateRoute>
            }
          />

          {/* Customer */}
          <Route
            path="/customer/dashboard"
            element={
              <PrivateRoute allowedRoles={["CUSTOMER"]}>
                <CustomerDashboard />
              </PrivateRoute>
            }
          />
          <Route
            path="/customer/cart"
            element={
              <PrivateRoute allowedRoles={["CUSTOMER"]}>
                <Cart />
              </PrivateRoute>
            }
          />
          <Route
            path="/customer/orders"
            element={
              <PrivateRoute allowedRoles={["CUSTOMER"]}>
                <CustomerOrders />
              </PrivateRoute>
            }
          />
          <Route
            path="/payment/:orderId/:customerId/:totalAmount"
            element={
              <PrivateRoute allowedRoles={["CUSTOMER"]}>
                <PaymentDemo />
              </PrivateRoute>
            }
          />

          {/* Fallback */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
      <Footer />
    </Router>
  );
};

export default App;



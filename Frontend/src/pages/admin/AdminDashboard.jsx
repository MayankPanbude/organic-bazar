import React from "react";
import { Link } from "react-router-dom";

const AdminDashboard = () => {
  return (
    <div className="max-w-4xl mx-auto mt-16 px-6">
      <h1 className="text-4xl font-extrabold mb-10 text-gray-800 text-center">
        Admin Dashboard
      </h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 gap-8">
        <Link
          to="/admin/users"
          className="flex flex-col items-center justify-center bg-blue-50 hover:bg-blue-100 transition rounded-xl shadow-lg p-8 cursor-pointer"
        >
          <span className="text-6xl mb-4 select-none">👥</span>
          <span className="text-xl font-semibold text-blue-700">
            Manage Users
          </span>
          <p className="mt-2 text-center text-blue-600 text-sm">
            Add, edit, or remove users and manage their roles.
          </p>
        </Link>

        <Link
          to="/admin/products"
          className="flex flex-col items-center justify-center bg-green-50 hover:bg-green-100 transition rounded-xl shadow-lg p-8 cursor-pointer"
        >
          <span className="text-6xl mb-4 select-none">🛒</span>
          <span className="text-xl font-semibold text-green-700">
            View All Products
          </span>
          <p className="mt-2 text-center text-green-600 text-sm">
            Browse, update, or delete products listed on the platform.
          </p>
        </Link>

        <Link
          to="/admin/categories"
          className="flex flex-col items-center justify-center bg-yellow-50 hover:bg-yellow-100 transition rounded-xl shadow-lg p-8 cursor-pointer"
        >
          <span className="text-6xl mb-4 select-none">📂</span>
          <span className="text-xl font-semibold text-yellow-700">
            Manage Categories
          </span>
          <p className="mt-2 text-center text-yellow-600 text-sm">
            Organize product categories for better navigation.
          </p>
        </Link>
      </div>
    </div>
  );
};

export default AdminDashboard;

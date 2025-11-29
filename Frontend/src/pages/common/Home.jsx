import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div className="max-w-4xl mx-auto mt-10 text-center">
      <h1 className="text-4xl font-bold mb-4">Welcome to Organic Bazar 🌱</h1>
      <p className="mb-6 text-gray-600">Shop fresh, organic, and local produce directly from farmers.</p>

      <div className="space-x-4">
        <Link to="/login" className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">
          Login
        </Link>
        <Link to="/register" className="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400">
          Register
        </Link>
      </div>
    </div>
  );
};

export default Home;

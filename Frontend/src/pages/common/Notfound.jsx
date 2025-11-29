import React from "react";
import { Link } from "react-router-dom";

const NotFound = () => {
  return (
    <div className="min-h-screen flex items-center justify-center flex-col text-center">
      <h1 className="text-5xl font-bold mb-4">404</h1>
      <p className="text-gray-600 mb-6">Oops! Page not found.</p>
      <Link to="/" className="text-blue-600 underline">Go back to Home</Link>
    </div>
  );
};

export default NotFound;

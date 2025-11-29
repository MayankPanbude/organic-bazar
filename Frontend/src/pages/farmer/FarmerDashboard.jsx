import React from "react";
import { Link } from "react-router-dom";

const FarmerDashboard = () => {
  const menuItems = [
    {
      label: "➕ Add Product",
      link: "/farmer/add-product",
      gradient: "from-green-400 to-green-600",
    },
    {
      label: "📦 My Products",
      link: "/farmer/my-products",
      gradient: "from-blue-400 to-blue-600",
    },
    {
      label: "📑 Orders",
      link: "/farmer/orders",
      gradient: "from-yellow-400 to-yellow-600",
      colSpan: "col-span-2",
    },
  ];

  return (
    <div className="max-w-4xl mx-auto mt-12 px-4 text-center">
      <h1 className="text-4xl font-extrabold mb-10 text-gray-800">
        Farmer Dashboard 👨‍🌾
      </h1>

      <div className="grid grid-cols-2 gap-8">
        {menuItems.map((item, idx) => (
          <Link
            key={idx}
            to={item.link}
            className={`relative block rounded-2xl shadow-lg transform hover:scale-105 transition duration-300 ${item.colSpan || ""
              }`}
          >
            <div
              className={`p-8 rounded-2xl bg-gradient-to-r ${item.gradient} text-white font-semibold text-lg shadow-inner`}
            >
              {item.label}
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default FarmerDashboard;

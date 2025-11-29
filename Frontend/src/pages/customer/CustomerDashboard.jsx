import React, { useEffect, useState } from "react";
import { getAllProducts } from "../../api/productApi";
import ProductCard from "../../components/ProductCard";

const CustomerDashboard = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getAllProducts().then(setProducts);
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-green-50 to-white py-12 px-6">
      <div className="max-w-7xl mx-auto">
        <h2 className="text-4xl font-extrabold text-green-800 mb-8 text-center drop-shadow-md">
          Explore Fresh Organic Products
        </h2>

        {products.length === 0 ? (
          <p className="text-center text-gray-500 text-lg mt-20">
            No products available at the moment.
          </p>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8">
            {products.map((product) => (
              <div
                key={product.id}
                className="bg-white rounded-lg shadow-lg hover:shadow-2xl transition-shadow duration-300"
              >
                <ProductCard product={product} showAddToCart />
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default CustomerDashboard;

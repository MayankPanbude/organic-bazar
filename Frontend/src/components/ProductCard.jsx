import React, { useState } from "react";
import { addToCart } from "../api/cartApi";

const ProductCard = ({ product, showAddToCart = false }) => {
  const [quantity, setQuantity] = useState(1); // New state for quantity

  const handleAddToCart = async () => {
    try {
      if (quantity < 1 || quantity > product.stock) {
        alert("Please enter a valid quantity.");
        return;
      }

      await addToCart(product.id, quantity);
      alert(`${product.name} added to cart!`);
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Failed to add product to cart.");
    }
  };

  return (
    <div className="border rounded p-4 shadow hover:shadow-md">
      <h3 className="text-lg font-semibold mb-2">{product.name}</h3>
      <p className="text-sm text-gray-500 mb-1">{product.categoryName}</p>
      <p className="text-gray-700 mb-2">{product.description}</p>
      <p className="font-bold text-green-700 mb-2">₹{product.price}</p>
      <p className="text-sm text-gray-600 mb-2">In Stock: {product.stock}</p>

      {showAddToCart && (
        <>
          <div className="mb-2">
            <label className="text-sm font-medium mr-2">Quantity:</label>
            <input
              type="number"
              value={quantity}
              onChange={(e) => setQuantity(Number(e.target.value))}
              min={1}
              max={product.stock}
              className="border rounded px-2 py-1 w-20"
            />
          </div>
          <button
            onClick={handleAddToCart}
            className="bg-green-600 text-white px-3 py-1 rounded hover:bg-green-700"
          >
            Add to Cart
          </button>
        </>
      )}
    </div>
  );
};

export default ProductCard;

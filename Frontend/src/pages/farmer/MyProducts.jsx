import React, { useEffect, useState } from "react";
import { getMyProducts, deleteProduct } from "../../api/productApi";
import { useNavigate } from "react-router-dom";

const MyProducts = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const loadProducts = () => {
    getMyProducts()
      .then(setProducts)
      .catch((err) => {
        console.error(err);
        setError("Failed to fetch your products.");
      });
  };

  useEffect(() => {
    loadProducts();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this product?")) return;
    try {
      await deleteProduct(id);
      loadProducts(); // Refresh list after delete
    } catch (err) {
      console.error(err);
      alert("Failed to delete product.");
    }
  };

  const handleUpdate = (id) => {
    navigate(`/farmer/update-product/${id}`); // Ensure this route exists
  };

  return (
    <div className="max-w-7xl mx-auto mt-12 px-4">
      <h2 className="text-3xl font-extrabold mb-6 text-gray-800">My Products</h2>

      {error && (
        <p className="mb-4 text-red-600 bg-red-100 p-3 rounded">{error}</p>
      )}

      {products.length === 0 ? (
        <p className="text-gray-600 text-center mt-20 text-lg">
          You have not added any products yet.
        </p>
      ) : (
        <div className="overflow-x-auto shadow-md rounded-lg border border-gray-200">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-green-100">
              <tr>
                {["Name", "Price", "Stock", "Category", "Actions"].map((title) => (
                  <th
                    key={title}
                    scope="col"
                    className="px-6 py-3 text-left text-xs font-semibold text-green-700 uppercase tracking-wider"
                  >
                    {title}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {products.map((p, idx) => (
                <tr
                  key={p.id}
                  className={idx % 2 === 0 ? "bg-green-50" : ""}
                >
                  <td className="px-6 py-4 whitespace-nowrap text-gray-800 font-medium">
                    {p.name}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-gray-700">
                    ₹{p.price}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-gray-700">
                    {p.stock}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-gray-700">
                    {p.categoryName}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap space-x-3">
                    <button
                      onClick={() => handleUpdate(p.id)}
                      className="px-4 py-2 bg-blue-600 hover:bg-blue-700 transition text-white rounded-md font-semibold"
                    >
                      Update
                    </button>
                    <button
                      onClick={() => handleDelete(p.id)}
                      className="px-4 py-2 bg-red-600 hover:bg-red-700 transition text-white rounded-md font-semibold"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default MyProducts;

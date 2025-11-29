import React, { useEffect, useState } from "react";
import { getCartItems, placeOrder } from "../../api/cartApi";
import { useNavigate } from "react-router-dom";
import { ShoppingCart, IndianRupee, Trash2 } from "lucide-react";

const Cart = () => {
  const [items, setItems] = useState([]);
  const [total, setTotal] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {
    try {
      const data = await getCartItems();
      setItems(data || []);
      const calculatedTotal = data?.reduce(
        (sum, item) => sum + (item.product?.price || 0) * item.quantity,
        0
      );
      setTotal(calculatedTotal || 0);
    } catch (error) {
      console.error("Failed to load cart:", error);
      setItems([]);
      setTotal(0);
    }
  };

  const handlePlaceOrder = async () => {
    try {
      const response = await placeOrder();
      const { id: orderId, customerId, totalAmount } = response;
      navigate(`/payment/${orderId}/${customerId}/${totalAmount}`);
    } catch (error) {
      console.error("Error placing order:", error);
    }
  };

  return (
    <div className="max-w-4xl mx-auto mt-10 px-4">
      <h2 className="text-3xl font-bold text-green-700 mb-8 flex items-center justify-center gap-2">
        <ShoppingCart className="w-8 h-8" /> Your Cart
      </h2>

      {items.length === 0 ? (
        <div className="bg-white shadow rounded-lg p-8 text-center">
          <ShoppingCart className="mx-auto w-12 h-12 text-gray-400 mb-3" />
          <p className="text-gray-600 text-lg">Your cart is empty.</p>
        </div>
      ) : (
        <>
          <ul className="divide-y bg-white shadow rounded-lg mb-6">
            {items.map((item) => (
              <li
                key={item.id}
                className="flex justify-between items-center p-4 hover:bg-gray-50 transition"
              >
                <div>
                  <p className="font-semibold text-gray-800">
                    {item.product?.name || "Unknown"}
                  </p>
                  <p className="text-sm text-gray-500">
                    ₹{item.product?.price?.toFixed(2) || "0.00"} x {item.quantity}
                  </p>
                </div>
                <div className="flex items-center gap-4">
                  <p className="font-semibold text-green-700">
                    ₹{((item.product?.price || 0) * item.quantity).toFixed(2)}
                  </p>
                  {/* <button className="text-red-500 hover:text-red-700">
                    <Trash2 className="w-5 h-5" />
                  </button> */}
                </div>
              </li>
            ))}
          </ul>

          <div className="bg-white shadow rounded-lg p-4 flex justify-between items-center">
            <p className="flex items-center font-bold text-lg text-gray-800">
              <IndianRupee className="w-5 h-5 mr-1" />
              Total: ₹{total.toFixed(2)}
            </p>
            <button
              onClick={handlePlaceOrder}
              className="bg-green-600 hover:bg-green-700 text-white px-6 py-2 rounded-lg shadow transition"
            >
              Place Order & Pay
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;

import React, { useEffect, useState } from "react";
import { getCustomerOrders } from "../../api/orderApi";
import { Package, IndianRupee, CalendarDays } from "lucide-react";

const Orders = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    getCustomerOrders().then((data) => {
      if (Array.isArray(data)) {
        setOrders(data);
      } else {
        console.error("Expected an array, got:", data);
        setOrders([]);
      }
    });
  }, []);

  return (
    <div className="max-w-5xl mx-auto mt-10 px-4">
      <h2 className="text-3xl font-bold text-green-700 mb-8 text-center">
        My Orders
      </h2>

      {orders.length === 0 ? (
        <div className="text-center bg-white shadow rounded-lg p-8">
          <Package className="mx-auto w-12 h-12 text-gray-400 mb-3" />
          <p className="text-gray-600 text-lg">
            You have not placed any orders yet.
          </p>
        </div>
      ) : (
        <div className="space-y-6">
          {orders.map((order) => (
            <div
              key={order.id}
              className="bg-white border border-gray-200 rounded-xl shadow hover:shadow-lg transition-shadow p-6"
            >
              {/* Order Header */}
              <div className="flex justify-between items-center mb-4">
                <p className="text-lg font-semibold text-green-700">
                  Order #{order.id}
                </p>
                <span
                  className={`px-3 py-1 rounded-full text-sm font-medium ${
                    order.status === "COMPLETED"
                      ? "bg-green-100 text-green-700"
                      : order.status === "PENDING"
                      ? "bg-yellow-100 text-yellow-700"
                      : "bg-red-100 text-red-700"
                  }`}
                >
                  {order.status}
                </span>
              </div>

              {/* Order Info */}
              <div className="flex items-center text-gray-500 text-sm mb-4">
                <CalendarDays className="w-4 h-4 mr-1" />
                {new Date(order.orderDate).toLocaleDateString()}
              </div>

              {/* Items */}
              <ul className="bg-gray-50 p-4 rounded-lg shadow-inner mb-4">
                {order.orderItems.map((item) => (
                  <li
                    key={item.id}
                    className="flex justify-between py-1 border-b last:border-none"
                  >
                    <span>Product #{item.productId}</span>
                    <span>x {item.quantity}</span>
                  </li>
                ))}
              </ul>

              {/* Total */}
              <div className="flex justify-between items-center mt-2">
                <span className="flex items-center font-semibold text-gray-700">
                  <IndianRupee className="w-4 h-4 mr-1" />
                  Total:
                </span>
                <span className="text-lg font-bold text-green-700">
                  ₹{order.totalAmount}
                </span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Orders;

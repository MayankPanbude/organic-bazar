import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { initiatePayment } from "../../api/paymentApi";
import { ArrowLeft, CreditCard } from "lucide-react";

const PaymentDemo = () => {
  const { orderId, customerId, totalAmount } = useParams();
  const navigate = useNavigate();

  const handlePay = async () => {
    try {
      const parsedOrderId = parseInt(orderId, 10);
      const parsedCustomerId = parseInt(customerId, 10);
      const parsedAmount = parseFloat(totalAmount);

      await initiatePayment(parsedOrderId, parsedCustomerId, parsedAmount);

      alert("Payment Successful!");
      navigate("/customer/dashboard");
    } catch (err) {
      console.error("Payment error:", err);
      alert("Payment failed. Please try again.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-green-50 to-green-100 px-4">
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md border border-gray-200">
        {/* Back Button */}
        <button
          onClick={() => navigate(-1)}
          className="flex items-center text-sm text-gray-500 hover:text-green-600 mb-4"
        >
          <ArrowLeft className="w-4 h-4 mr-1" /> Back
        </button>

        {/* Title */}
        <h2 className="text-2xl font-bold text-center text-green-700 mb-2">
          Payment Confirmation
        </h2>
        <p className="text-center text-gray-600 mb-6">
          You’re about to pay for <span className="font-semibold">Order #{orderId}</span>
        </p>

        {/* Order Details Card */}
        <div className="bg-gray-50 p-4 rounded-lg shadow-inner mb-6">
          <div className="flex justify-between mb-2">
            <span className="text-gray-600">Customer ID:</span>
            <span className="font-medium">{customerId}</span>
          </div>
          <div className="flex justify-between mb-2">
            <span className="text-gray-600">Total Amount:</span>
            <span className="font-bold text-green-700">₹{totalAmount}</span>
          </div>
          <div className="flex justify-between">
            <span className="text-gray-600">Payment Method:</span>
            <span className="font-medium">Demo Card</span>
          </div>
        </div>

        {/* Pay Now Button */}
        <button
          onClick={handlePay}
          className="w-full flex items-center justify-center gap-2 bg-green-600 text-white py-3 rounded-lg font-semibold shadow-md hover:bg-green-700 transition-all"
        >
          <CreditCard className="w-5 h-5" />
          Pay Now
        </button>

        {/* Footer Note */}
        <p className="text-xs text-center text-gray-500 mt-4">
          This is a demo payment page. No real transaction will occur.
        </p>
      </div>
    </div>
  );
};

export default PaymentDemo;

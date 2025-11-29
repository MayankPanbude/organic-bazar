import axiosInstance from "./axiosInstance";

export const initiatePayment = async (orderId, customerId, amount) => {
  const res = await axiosInstance.post(`/api/payments/${orderId}`, {
    customerId,
    amount,
    method: "ONLINE",
  });
  return res.data;
};

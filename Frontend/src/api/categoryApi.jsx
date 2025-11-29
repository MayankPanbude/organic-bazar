import axiosInstance from "./axiosInstance";

export const getAllCategories = async () => {
  const res = await axiosInstance.get("/api/categories");
  return res.data;
};

export const addCategory = async (category) => {
  const res = await axiosInstance.post("/api/categories", category);
  return res.data;
};

export const deleteCategory = async (id) => {
  const res = await axiosInstance.delete(`/api/categories/${id}`);
  return res.data;
};

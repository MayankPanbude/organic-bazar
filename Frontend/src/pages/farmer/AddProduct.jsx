import React, { useEffect, useState } from "react";
import { addProduct } from "../../api/productApi";
import { getAllCategories } from "../../api/categoryApi";

const AddProduct = () => {
  const [form, setForm] = useState({
    name: "",
    description: "",
    price: "",
    stock: "",
    categoryId: "",
  });

  const [categories, setCategories] = useState([]);

  useEffect(() => {
    getAllCategories().then(setCategories);
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addProduct({ ...form, categoryId: Number(form.categoryId) });
    alert("Product added successfully!");
    setForm({ name: "", description: "", price: "", stock: "", categoryId: "" });
  };

  return (
    <div className="max-w-xl mx-auto mt-10">
      <h2 className="text-2xl font-bold mb-4">Add Product</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input name="name" value={form.name} onChange={handleChange} placeholder="Name" required className="w-full p-2 border rounded" />
        <textarea name="description" value={form.description} onChange={handleChange} placeholder="Description" required className="w-full p-2 border rounded" />
        <input type="number" name="price" value={form.price} onChange={handleChange} placeholder="Price" required className="w-full p-2 border rounded" />
        <input type="number" name="stock" value={form.stock} onChange={handleChange} placeholder="Stock" required className="w-full p-2 border rounded" />
        <select name="categoryId" value={form.categoryId} onChange={handleChange} required className="w-full p-2 border rounded">
          <option value="">-- Select Category --</option>
          {categories.map((cat) => (
            <option key={cat.id} value={cat.id}>
              {cat.name}
            </option>
          ))}
        </select>
        <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded">
          Submit
        </button>
      </form>
    </div>
  );
};

export default AddProduct;

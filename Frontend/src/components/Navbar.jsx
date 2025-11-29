import React, { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../auth/AuthContext";
import { getRole } from "../services/roleUtils";

const Navbar = () => {
  const { token, logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const role = getRole();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <nav className="bg-green-600 text-white p-4 flex justify-between items-center">
      <Link to="/" className="text-xl font-bold">Organic Bazar</Link>
      <div className="space-x-4">
        {!token ? (
          <>
            <Link to="/login" className="hover:underline">Login</Link>
            <Link to="/register" className="hover:underline">Register</Link>
          </>
        ) : (
          <>
            {role === "ADMIN" && <Link to="/admin/dashboard">Dashboard</Link>}
            {role === "FARMER" && <Link to="/farmer/dashboard">Dashboard</Link>}
            {role === "CUSTOMER" && (
              <>
                <Link to="/customer/dashboard">Shop</Link>
                <Link to="/customer/cart">Cart</Link>
                <Link to="/customer/orders">Orders</Link>
              </>
            )}
            <button onClick={handleLogout} className="bg-white text-green-600 px-2 py-1 rounded ml-2">
              Logout
            </button>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;

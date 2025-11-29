import React from "react";

const Loader = () => {
  return (
    <div className="text-center my-10">
      <div className="loader border-4 border-green-600 border-t-transparent rounded-full w-10 h-10 mx-auto animate-spin"></div>
      <p className="mt-2 text-gray-600">Loading...</p>
    </div>
  );
};

export default Loader;

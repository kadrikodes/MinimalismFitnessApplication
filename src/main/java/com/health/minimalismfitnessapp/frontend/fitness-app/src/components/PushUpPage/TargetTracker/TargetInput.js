import React from "react";
import "./TargetInput.css";

const TargetInput = ({ goal, setGoal }) => {
  const handleChange = (e) => {
    const newGoal = parseInt(e.target.value);
    setGoal(newGoal);
  };

  return (
    <input
      type="number"
      value={goal}
      onChange={handleChange}
    />
  );
};

export default TargetInput;

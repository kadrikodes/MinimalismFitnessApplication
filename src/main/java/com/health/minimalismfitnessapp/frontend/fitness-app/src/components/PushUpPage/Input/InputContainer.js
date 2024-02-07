import "./InputContainer.css";
import { useState } from "react";

const InputContainer = () => {
  const [goal, setGoal] = useState(0);

  const handleChange = (e) => {
    const newGoal = (e.target.value);
    setGoal(newGoal);
  };

  return (
    <div className="input-container">
      <label htmlFor="goalInput">Enter Goal:</label>
      <input
        id="goalInput"
        type="number"
        value={goal}
        onChange={handleChange}
        placeholder="Enter your goal"
      />
    </div>
  );
};

export default InputContainer;

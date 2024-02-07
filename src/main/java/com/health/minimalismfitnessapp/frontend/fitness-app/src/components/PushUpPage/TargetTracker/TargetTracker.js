import React, { useState } from "react";
import TargetInput from "./TargetInput";

const TargetTracker = () => {
  const [goal, setGoal] = useState(0);
  const [count, setCount] = useState(0);
  const [savedCount, setSavedCount] = useState(0);

  const handleIncrement = () => {
    setCount(count + 1);
  };

  const handleDecrement = () => {
    if (count > 0) {
      setCount(count - 1);
    }
  };

  const handleSave = () => {
    setSavedCount(savedCount + count);
    setCount(0); 
  };

  const handleReset = () => {
    setSavedCount(0); 
  };

  return (
    <div className="desktop">
      <div className="target-header">
        <h2>Target Tracker</h2>
      </div>
      <div className="goal-box">
        <TargetInput goal={goal} setGoal={setGoal} />
      </div>

      <div className="column">
        <div className="row">
          <div className="button-column">
            <button className="increment-button" onClick={handleDecrement}>-</button>
            <button className="increment-button" onClick={handleIncrement}>+</button>
          </div>
        </div>
        <div className="row">
          <div className="button-column">
            <button className="action-button" onClick={handleSave}>Save</button>
            <button className="action-button" onClick={handleReset}>Reset</button>
          </div>
        </div>
        <div className="target-tracker">
          <h3>Current: {count}</h3>
          <h3>Target: {goal}</h3>
          <h3>Total: {savedCount}</h3>
        </div>
            </div>
      </div>
  );
};



export default TargetTracker;


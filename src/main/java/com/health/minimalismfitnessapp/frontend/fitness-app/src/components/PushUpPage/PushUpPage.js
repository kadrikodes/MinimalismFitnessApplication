import React, { useState, useEffect } from "react";
import "./PushUpPage.css";
import PushUpContainer from "../HomePage/PushUpSummary/PushUpContainer";
const PushUpPage = () => {

  const [count, setCount] = useState(0);
  const [savedCount, setSavedCount] = useState(0);
  const [pushUpData, setPushUpData] = useState(null);
  const [goal, setGoal] = useState(0);
  useEffect(() => {
    const pushUpAPI = 'http://localhost:8080/pushups/1';
    fetch(pushUpAPI)
      .then((response) => response.json())
      .then((data) => setPushUpData(data));
  }, []);
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
  const handleChange = (e) => {
    const newGoal = parseInt(e.target.value);
    setGoal(newGoal);
  };
  return (
    <div className="desktop">
      <div className="push-up-page-container">
        <div className="left-box">
          <PushUpContainer pushUpData={pushUpData} />
        </div>
        <div className="middle-box desktop">
          <h2>Pushup Counter: </h2>
          <div className="target-header"></div>
          <div className="button-row">
            <button className="increment-button" onClick={handleDecrement}>-</button>
            <button className="increment-button" onClick={handleIncrement}>+</button>
          </div>
          <div>
            <h3>Current: {count}</h3>
            <button className="action-button" onClick={handleSave}>Save</button>
            <button className="action-button" onClick={handleReset}>Reset</button>
          </div>
        </div>
        <div className="right-box">
          <h2>Target Tracker:</h2>
          <div className="input-container">
            <label htmlFor="goalInput">Enter target</label>
            <input
              id="goalInput"
              type="number"
              value={goal}
              onChange={handleChange}
              placeholder="Enter your goal"
            />
          </div>
          <div className="counter-container">
            <div className="target-tracker">
              <h3>Total: {savedCount}</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PushUpPage;
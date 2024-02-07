import React, { useState } from "react";
import InputContainer from "../Input/InputContainer";
import "./TrackerContainer.css";
import CounterContainer from "../Counter/CounterContainer";


const TrackerContainer = () => {
    const [goal, setGoal] = useState(0);
    const [count, setCount] = useState(0);
    const [savedCount, setSavedCount] = useState(0);
  
    // const handleIncrement = () => {
    //   setCount(count + 1);
    // };
  
    // const handleDecrement = () => {
    //   if (count > 0) {
    //     setCount(count - 1);
    //   }
    // };
  
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
          <h2>Push up Counter:</h2>
        </div>
        {/* <InputContainer goal={goal} setGoal={setGoal} />
        <CounterContainer
          goal={goal}
          count={count}
          savedCount={savedCount}
        /> */}
        <div className="column">
          <div className="row">
            <div className="row">
              {/* <button className="increment-button" onClick={handleDecrement}>-</button>
              <button className="increment-button" onClick={handleIncrement}>+</button> */}
            </div>
          </div>
          <div className="row">
            <div className="button-column">
              <button className="action-button" onClick={handleSave}>Save</button>
              <button className="action-button" onClick={handleReset}>Reset</button>
            </div>
          </div>
        </div>
      </div>
    );
  };

export default TrackerContainer;
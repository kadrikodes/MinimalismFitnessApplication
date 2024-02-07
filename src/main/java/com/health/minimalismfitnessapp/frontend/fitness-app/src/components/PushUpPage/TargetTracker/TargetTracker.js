import React, { useState } from "react";
import Counter from "../Counter/Counter";

const TargetTracker = () => {
  const [goal, setGoal] = useState(0);
  const [pushupsDone, setPushupsDone] = useState(0);
  const [totalCount, setTotalCount] = useState(0); // Lifted state

  const handleGoalChange = (e) => {
    const newGoal = parseInt(e.target.value);
    setGoal(newGoal);
  };

  const handlePushupsDoneChange = (newPushupsDone) => {
    setPushupsDone(newPushupsDone);
  };

  const handleSubmitTotal = (currentCount) => { // Pass the current count to calculate total
    setTotalCount(totalCount + currentCount); // Update total count
    setPushupsDone(pushupsDone + currentCount); // Update pushupsDone
  };

  return (
    <div>
      <h2>Push-up Goal for Today:</h2>
      <input
        type="number"
        value={goal}
        onChange={handleGoalChange}
        placeholder="Enter your goal"
      />
      <Counter onChange={handlePushupsDoneChange} onSubmitTotal={handleSubmitTotal} />
      <div>
        <h3>Push-ups Done:</h3>
        <p>{pushupsDone}</p>
        <h3>Target:</h3>
        <p>{goal}</p>
        <button onClick={({totalCount}) => handleSubmitTotal(0)}>Submit Total </button> 

        <button className="savebutton" onClick={handleSave}>Save</button>
      </div>
    </div>
  );
};

export default TargetTracker;

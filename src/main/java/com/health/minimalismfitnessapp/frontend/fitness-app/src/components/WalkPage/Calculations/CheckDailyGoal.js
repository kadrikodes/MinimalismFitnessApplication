import React, { useState } from 'react';

const CheckDailyGoal = () => {
    const [stepsTaken, setStepsTaken] = useState('');
    const [goalMessage, setGoalMessage] = useState('');

    const checkGoal = async () => {
        const checkDailyGoalAPI = `http://localhost:8080/walking/hasAchievedDailyGoal?stepsTaken=${stepsTaken}`;

        try {
            const response = await fetch(checkDailyGoalAPI);
            if (!response.ok) throw new Error("Failed to check goal");

            const hasAchieved = await response.json();
            setGoalMessage(hasAchieved ? "You have achieved your daily goal!" : "Daily goal not achieved yet.");
        } catch (error) {
            console.error(error);
            setGoalMessage("Failed to check daily goal!");
        }
    };

    return (
        <div>
            <label>
                Check if daily goal achieved:
            <input
                type="number"
                value={stepsTaken}
                onChange={e => setStepsTaken(e.target.value)}
                placeholder="Enter steps taken"
            />
            </label>
            <button onClick={checkGoal}>Check Daily Goal</button>
            {goalMessage && <p>{goalMessage}</p>}
        </div>
    );
};
export default CheckDailyGoal;
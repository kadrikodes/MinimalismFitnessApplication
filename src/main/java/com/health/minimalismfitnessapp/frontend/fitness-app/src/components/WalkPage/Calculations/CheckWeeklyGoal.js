import React, { useState } from 'react';

const CheckWeeklyGoal = () => {
    const [stepsTaken, setStepsTaken] = useState('');
    const [goalMessage, setGoalMessage] = useState('');

    const checkGoal = async () => {
        const checkWeeklyGoalAPI = `http://localhost:8080/walking/hasAchievedWeeklyGoal?stepsTaken=${stepsTaken}`;

        try {
            const response = await fetch(checkWeeklyGoalAPI);
            if (!response.ok) throw new Error("Failed to check goal");

            const hasAchieved = await response.json();
            setGoalMessage(hasAchieved ? "You have achieved your weekly goal!" : "Weekly goal not achieved yet.");
        } catch (error) {
            console.error(error);
            setGoalMessage("Failed to check weekly goal!");
        }
    };

    return (
        <div>
            <label>
                Check if weekly goal achieved:
            <input
                type="number"
                value={stepsTaken}
                onChange={e => setStepsTaken(e.target.value)}
                placeholder="Enter steps taken"
            />
            </label>
            <button onClick={checkGoal}>Check</button>
            {goalMessage && <p>{goalMessage}</p>}
        </div>
    );
};
export default CheckWeeklyGoal;
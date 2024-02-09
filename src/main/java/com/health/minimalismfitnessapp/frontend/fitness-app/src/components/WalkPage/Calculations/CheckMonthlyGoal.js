import React, { useState } from 'react';

const CheckMonthlyGoal = () => {
    const [stepsTaken, setStepsTaken] = useState('');
    const [goalMessage, setGoalMessage] = useState('');

    const checkGoal = async () => {
        const checkMonthlyGoalAPI = `http://localhost:8080/walking/hasAchievedMonthlyGoal?stepsTaken=${stepsTaken}`;

        try {
            const response = await fetch(checkMonthlyGoalAPI);
            if (!response.ok) throw new Error("Failed to check goal");

            const hasAchieved = await response.json();
            setGoalMessage(hasAchieved ? "You have achieved your monthly goal!" : "Monthly goal not achieved yet.");
        } catch (error) {
            console.error(error);
            setGoalMessage("Failed to check monthly goal!");
        }
    };

    return (
        <div>
            <label>
                Check if monthly goal achieved:
            <input
                type="number"
                value={stepsTaken}
                onChange={e => setStepsTaken(e.target.value)}
                placeholder="Enter steps taken"
            />
            </label>
            <button onClick={checkGoal}>Check Monthly Goal</button>
            {goalMessage && <p>{goalMessage}</p>}
        </div>
    );
};
export default CheckMonthlyGoal;
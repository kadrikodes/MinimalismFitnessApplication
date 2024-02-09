import React, { useState } from 'react';

const CalculateStepsToBurnCalories = ({ props }) => {
    const [walkMessage, setWalkMessage] = useState("");
    const [calories, setCalories] = useState('');

    const calculateSteps = async (e) => {
        e.preventDefault();

        const calculateStepsToBurnCaloriesAPI = `http://localhost:8080/walking/calculateStepsToBurnCalories?caloriesToBurn=${calories}`;

        try {
            const response = await fetch(calculateStepsToBurnCaloriesAPI);
            if (!response.ok) throw new Error("Failed to fetch steps");

            const steps = await response.json(); 
            setWalkMessage(`You will need to complete ${steps} steps to burn ${calories} calories.`);
        } catch (error) {
            console.error(error);
            setWalkMessage("Failed to Calculate steps!");
        }
    };

    return (
        <div>
            <form onSubmit={calculateSteps}>
                <label>
                    Enter calories to Burn to calculate steps needed:
                    <input
                        type="number"
                        value={calories}
                        onChange={e => setCalories(e.target.value)}
                        placeholder="Enter calories"
                    />
                </label>
                <button type="submit">Calculate Steps</button>
            </form>
            {walkMessage && <p>{walkMessage}</p>} {/* Display the walkMessage */}
        </div>
    );
};

export default CalculateStepsToBurnCalories;

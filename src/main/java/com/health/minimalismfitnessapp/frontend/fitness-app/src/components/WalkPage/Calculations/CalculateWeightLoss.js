import React, { useState } from 'react';

const CalculateWeightLoss = () => {
    const [stepsTaken, setStepsTaken] = useState('');
    const [weightLossMessage, setWeightLossMessage] = useState('');

    const handleCalculateWeightLoss = async (e) => {
        e.preventDefault();
        const calculateWeightLossAPI = `http://localhost:8080/walking/calculateWeightLoss?stepsTaken=${stepsTaken}`;

        try {
            const response = await fetch(calculateWeightLossAPI);
            if (!response.ok) throw new Error("Failed to fetch weight loss data");

            const weightLoss = await response.json();
            setWeightLossMessage(`Weight loss for ${stepsTaken} steps: ${weightLoss.toFixed(2)} kg.`);
        } catch (error) {
            console.error(error);
            setWeightLossMessage("Failed to calculate weight loss!");
        }
    };

    return (
        <div>
            <form onSubmit={handleCalculateWeightLoss}>
                <label>
                    Enter steps taken to calculate how much weight you have lost:
                    <input
                        type="number"
                        value={stepsTaken}
                        onChange={e => setStepsTaken(e.target.value)}
                        placeholder="Enter steps taken"
                    />
                </label>
                <button type="submit">Calculate Weight Loss</button>
            </form>
            {weightLossMessage && <p>{weightLossMessage}</p>}
        </div>
    );
};
export default CalculateWeightLoss;
import React, { useState } from 'react';

const GetTotalCaloriesBurned = () => {
    const [walkingId, setWalkingId] = useState('');
    const [totalCaloriesMessage, setTotalCaloriesMessage] = useState('');

    const fetchTotalCalories = async () => {
        const getTotalCaloriesAPI = `http://localhost:8080/walking/calories/${walkingId}`;

        try {
            const response = await fetch(getTotalCaloriesAPI);
            if (!response.ok) throw new Error("Failed to fetch total calories");

            const totalCalories = await response.json();
            setTotalCaloriesMessage(`Total calories burned for walking ID ${walkingId}: ${totalCalories}`);
        } catch (error) {
            console.error(error);
            setTotalCaloriesMessage("Failed to fetch total calories burned!");
        }
    };

    return (
        <div>
            <label>
               Enter Walking ID to get total calories burned:
                <input
                    type="text"
                    value={walkingId}
                    onChange={e => setWalkingId(e.target.value)}
                    placeholder="Enter Walking ID"
                />
            </label>
            <button onClick={fetchTotalCalories}>Get Total</button>
            {totalCaloriesMessage && <p>{totalCaloriesMessage}</p>}
        </div>
    );
};

export default GetTotalCaloriesBurned;

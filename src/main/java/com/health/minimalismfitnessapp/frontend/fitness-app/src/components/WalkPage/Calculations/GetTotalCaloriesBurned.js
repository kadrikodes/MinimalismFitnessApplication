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
                Walking ID:
                <input
                    type="text"
                    value={walkingId}
                    onChange={e => setWalkingId(e.target.value)}
                    placeholder="Enter Walking ID"
                />
            </label>
            <button onClick={fetchTotalCalories}>Get Total Calories Burned</button>
            {totalCaloriesMessage && <p>{totalCaloriesMessage}</p>}
        </div>
    );
};

export default GetTotalCaloriesBurned;

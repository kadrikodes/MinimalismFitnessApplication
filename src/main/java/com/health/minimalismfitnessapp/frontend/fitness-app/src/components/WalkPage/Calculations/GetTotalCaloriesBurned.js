import React, { useState } from 'react';

const GetTotalCaloriesBurned = ({ walkingId }) => {
    const [totalCaloriesMessage, setTotalCaloriesMessage] = useState('');

    const fetchTotalCalories = async () => {
        const getTotalCaloriesAPI = `http://localhost:8080/walking/calories/${walkingId}`;

        try {
            const response = await fetch(getTotalCaloriesAPI);
            if (!response.ok) throw new Error("Failed to fetch total calories");

            const totalCalories = await response.json();
            setTotalCaloriesMessage(`Total calories burned: ${totalCalories}`);
        } catch (error) {
            console.error(error);
            setTotalCaloriesMessage("Failed to fetch total calories burned!");
        }
    };

    return (
        <div>
            <button onClick={fetchTotalCalories}>Get Total Calories Burned</button>
            {totalCaloriesMessage && <p>{totalCaloriesMessage}</p>}
        </div>
    );
};
export default GetTotalCaloriesBurned;
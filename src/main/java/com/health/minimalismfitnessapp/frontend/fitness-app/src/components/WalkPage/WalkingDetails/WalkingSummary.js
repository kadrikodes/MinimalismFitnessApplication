import React, { useState, useEffect } from 'react';

const WalkingSummary = () => {
  const [summary, setSummary] = useState({
    totalSteps: 0,
    totalDistance: 0,
    caloriesBurned: 0,
    averageSpeed: 0,
  });

  useEffect(() => {
    const fetchWalkingData = async () => {
      try {
        const response = await fetch('http://localhost:8080/walking/allWalkingData');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const walkingData = await response.json();

        // Aggregate data
        const totals = walkingData.reduce((acc, curr) => {
          acc.totalSteps += curr.steps;
          acc.totalDistance += curr.distance;
          acc.caloriesBurned += curr.caloriesBurned;
          acc.totalDuration += curr.duration; // Assuming duration is in minutes
          return acc;
        }, { totalSteps: 0, totalDistance: 0, caloriesBurned: 0, totalDuration: 0 });

        const averageSpeed = totals.totalDistance / (totals.totalDuration / 60); // Average speed in km/h

        setSummary({
          totalSteps: totals.totalSteps,
          totalDistance: totals.totalDistance,
          caloriesBurned: totals.caloriesBurned,
          averageSpeed: averageSpeed.toFixed(2),
        });

      } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
      }
    };

    fetchWalkingData();
  }, []);

  return (
    <div className="WalkingSummary">
      <h2>Walking Summary</h2>
      <div className="SummaryMetric">Total Steps: {summary.totalSteps}</div>
      <div className="SummaryMetric">Distance: {summary.totalDistance.toFixed(2)} km</div>
      <div className="SummaryMetric">Calories: {summary.caloriesBurned}</div>
      <div className="SummaryMetric">Average Speed: {summary.averageSpeed} km/h</div>
    </div>
  );
};

export default WalkingSummary;

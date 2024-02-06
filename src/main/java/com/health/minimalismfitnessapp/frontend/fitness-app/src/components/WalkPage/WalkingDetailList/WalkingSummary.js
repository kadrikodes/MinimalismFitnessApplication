import React, { useState, useEffect } from 'react';

const WalkingSummary = () => {
  const [summary, setSummary] = useState({
    totalSteps: 0,
    totalDistance: 0,
    caloriesBurned: 0,
    averageSpeed: 0,
  });

  useEffect(() => {
    // Placeholder for data fetching
    // This is where you would call your API to get the walking summary data
    const fetchWalkingSummary = async () => {
      // const response = await fetch('API_ENDPOINT_HERE');
      // const data = await response.json();
      const data = { // Mock data
        totalSteps: 10000,
        totalDistance: 7.5, // in kilometers
        caloriesBurned: 350,
        averageSpeed: 5.2, // in km/h
      };
      setSummary(data);
    };

    fetchWalkingSummary();
  }, []);

  return (
    <div className="WalkingSummary">
      <h2>Walking Summary</h2>
      <div className="SummaryMetric">Total Steps: {summary.totalSteps}</div>
      <div className="SummaryMetric">Distance: {summary.totalDistance} km</div>
      <div className="SummaryMetric">Calories: {summary.caloriesBurned}</div>
      <div className="SummaryMetric">Average Speed: {summary.averageSpeed} km/h</div>
      {/* Optional: Add a progress bar or chart here */}
    </div>
  );
};

export default WalkingSummary;

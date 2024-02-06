import React, { useState, useEffect } from 'react';
import WalkingSummary from './WalkingSummary';
import WalkingDetailList from './WalkingDetailList';

const WalkingTracker = () => {
  const [walkingData, setWalkingData] = useState([]);
  const [summary, setSummary] = useState({
    totalSteps: 0,
    totalDistance: 0,
    caloriesBurned: 0,
    averageSpeed: 0,
  });

  useEffect(() => {
    // Example function to calculate summary based on walkingData
    const calculateSummary = () => {
      const totalSteps = walkingData.reduce((acc, curr) => acc + curr.steps, 0);
      const totalDistance = walkingData.reduce((acc, curr) => acc + curr.distance, 0);
      const caloriesBurned = walkingData.reduce((acc, curr) => acc + curr.caloriesBurned, 0);
      const averageSpeed = totalDistance / walkingData.length || 0;

      setSummary({ totalSteps, totalDistance, caloriesBurned, averageSpeed });
    };

    calculateSummary();
  }, [walkingData]);

  return (
    <div>
      <WalkingSummary summary={summary} />
      <WalkingDetailList walkingData={walkingData} setWalkingData={setWalkingData} />
    </div>
  );
};

export default WalkingTracker;

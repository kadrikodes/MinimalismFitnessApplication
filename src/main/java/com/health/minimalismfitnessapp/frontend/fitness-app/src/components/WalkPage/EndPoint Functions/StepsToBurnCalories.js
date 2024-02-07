const calculateStepsToBurnCalories = async (caloriesToBurn) => {
    try {
        const response = await fetch(`/walking/calculateStepsToBurnCalories?caloriesToBurn=${caloriesToBurn}`);
        const steps = await response.json();
        // Handle steps
    } catch (error) {
        console.error('Error calculating steps to burn calories:', error);
    }
};

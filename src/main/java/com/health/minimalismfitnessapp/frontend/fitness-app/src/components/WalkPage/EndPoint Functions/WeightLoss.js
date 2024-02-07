const calculateWeightLoss = async (stepsTaken) => {
    try {
        const response = await fetch(`/walking/calculateWeightLoss?stepsTaken=${stepsTaken}`);
        const weightLoss = await response.json();
        // Handle weight loss
    } catch (error) {
        console.error('Error calculating weight loss:', error);
    }
};
export { calculateWeightLoss };

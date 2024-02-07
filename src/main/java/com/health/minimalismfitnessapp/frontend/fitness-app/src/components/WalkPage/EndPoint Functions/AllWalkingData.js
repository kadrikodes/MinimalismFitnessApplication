const fetchAllWalkingData = async () => {
    try {
        const response = await fetch('/walking/allWalkingData');
        const data = await response.json();
        // Handle data
    } catch (error) {
        console.error('Error fetching all walking data:', error);
    }
};

export default fetchAllWalkingData;

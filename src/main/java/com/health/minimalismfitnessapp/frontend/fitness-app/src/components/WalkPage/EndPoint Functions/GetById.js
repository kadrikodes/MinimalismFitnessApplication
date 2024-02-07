const fetchWalkingDataById = async (walkingId) => {
    try {
        const response = await fetch(`http://localhost:8080/walking/${walkingId}`);
        const data = await response.json();
        // Handle data
    } catch (error) {
        console.error(`Error fetching walking data by ID ${walkingId}:`, error);
    }
};
export { fetchWalkingDataById };
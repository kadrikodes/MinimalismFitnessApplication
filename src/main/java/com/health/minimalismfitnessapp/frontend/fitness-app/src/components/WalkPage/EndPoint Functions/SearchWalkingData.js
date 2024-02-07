const searchWalkingData = async (criteria) => {
    try {
        const query = new URLSearchParams(criteria).toString();
        const response = await fetch(`/walking/search?${query}`);
        const data = await response.json();
        // Handle data
    } catch (error) {
        console.error('Error searching walking data:', error);
    }
};
export { searchWalkingData };

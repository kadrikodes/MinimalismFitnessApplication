const updateWalkingData = async (walkingId, updatedData) => {
    try {
        const response = await fetch(`/walking/update/${walkingId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedData),
        });
        const data = await response.json();
        // Handle response
    } catch (error) {
        console.error(`Error updating walking data for ID ${walkingId}:`, error);
    }
};
export { updateWalkingData };

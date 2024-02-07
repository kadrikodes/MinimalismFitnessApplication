const deleteWalkingData = async (walkingId) => {
    try {
        const response = await fetch(`/walking/delete/${walkingId}`, {
            method: 'DELETE',
        });
        // Handle response
    } catch (error) {
        console.error(`Error deleting walking data for ID ${walkingId}:`, error);
    }
};

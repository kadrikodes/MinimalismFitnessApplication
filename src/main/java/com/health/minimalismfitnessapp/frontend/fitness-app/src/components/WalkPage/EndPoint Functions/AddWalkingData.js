const addWalkingData = async (newData) => {
    try {
        const response = await fetch('/walking/addWalkingData', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newData),
        });
        const data = await response.json();
        // Handle response
    } catch (error) {
        console.error('Error adding walking data:', error);
    }
};

const fetchWalkingDataByUserName = async (name) => {
    try {
        const response = await fetch(`/walking/name/${name}`);
        const data = await response.json();
        // Handle data
    } catch (error) {
        console.error(`Error fetching walking data by user name ${name}:`, error);
    }
};

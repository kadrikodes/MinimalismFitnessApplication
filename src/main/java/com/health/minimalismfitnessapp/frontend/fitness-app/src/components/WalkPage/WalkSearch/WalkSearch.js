// WalkSearch.js
import React, { useState } from 'react';

const WalkSearch = ({ setNewItem, onSearchResults }) => {
    const [walkMessage, setWalkMessage] = useState("");
    const [dateTime, setDateTime] = useState('');
    const [distance, setDistance] = useState('');

    const handleSearch = async () => {

        const searchWalkAPI = 'http://localhost:8080/walking/search';
        
        const queryParams = new URLSearchParams();
        if (dateTime) queryParams.append('dateTime', dateTime);
        if (distance) queryParams.append('distance', distance);

        const criteria = { dateTime, distance };

        try {
            const response = await fetch(`${searchWalkAPI}?${queryParams.toString()}`);
            if (!response.ok) throw new Error('Search failed');
            const results = await response.json(); // Define results based on the fetched data
            setWalkMessage("Walk data found!");
            setNewItem(results); // Assuming you want to update some state with the fetched data
            onSearchResults(results, criteria); // Pass results and criteria to the callback
        } catch (error) {
            console.error('Failed to search:', error);
            setWalkMessage("Error searching for walk data.");
        }
    };

    return (
        <form onSubmit={handleSearch} className='walksearch'>
            <label>
                Date & Time:
                <input type="datetime-local" value={dateTime} onChange={e => setDateTime(e.target.value)} />
            </label>
            <label>
                Distance (km):
                <input type="number" step="0.1" value={distance} onChange={e => setDistance(e.target.value)} />
            </label>
            <button type="submit">Search</button>
            {walkMessage && <p>{walkMessage}</p>}
        </form>
    );
};

export default WalkSearch;

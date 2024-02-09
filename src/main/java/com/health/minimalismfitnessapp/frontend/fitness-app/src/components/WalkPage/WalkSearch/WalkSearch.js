import React, { useState } from 'react';

const WalkSearch = ({ setNewItem, onSearchResults }) => {
    const [walkMessage, setWalkMessage] = useState("");
    const [dateTime, setDateTime] = useState('');
    const [distance, setDistance] = useState('');

    const handleSearch = async () => {

        const queryParams = new URLSearchParams();
        if (dateTime) queryParams.append('dateTime', dateTime);
        if (distance) queryParams.append('distance', distance);

        const searchWalkAPI = `http://localhost:8080/walking/search?${queryParams}`;
        
        

        const criteria = { dateTime, distance };

        try {
            const response = await fetch(`${searchWalkAPI}?${queryParams.toString()}`);
            if (!response.ok) throw new Error('Search failed');
            const results = await response.json(); 
            setWalkMessage("Walk data found!");
            setNewItem(results); 
            onSearchResults(results, criteria);
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

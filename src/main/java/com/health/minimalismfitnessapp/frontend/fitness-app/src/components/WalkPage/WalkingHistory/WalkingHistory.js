import "./WalkingHistory.css";
import { useState } from "react";

const WalkingHistory = (props) => {
    const {
        walkingData,
        setNewItem,
        searchCriteria
    } = props;

    const { walkType = '', steps = '', distance = '', caloriesBurned = '', duration = '', speed = '', dateTime = '', id = '' } = props.walkingData || {};
    const [walkMessage, setWalkMessage] = useState("");
    const [isEditing, setIsEditing] = useState(false);
    const [editData, setEditData] = useState({
        walkType, steps, distance, caloriesBurned, duration, speed, dateTime
    });

    const handleDelete = (event) => {
        event.preventDefault();

        const deleteWalkAPI = `http://localhost:8080/walking/delete/${id}`;

        fetch(deleteWalkAPI, {
            method: 'DELETE',
        })
        .then((response) => {
            if (response.ok) {
                setWalkMessage("Walking data successfully deleted");
                props.setNewItem(response);
            } else {
                console.log(response);
                setWalkMessage("Error deleting walking data");
            }
        });
    };

    const toggleEdit = () => setIsEditing(!isEditing);

    const handleEditChange = (event, field) => {
        setEditData({ ...editData, [field]: event.target.value });
    };

    const handleUpdate = (event) => {
        event.preventDefault();
        const updateWalkAPI = `http://localhost:8080/walking/update/${id}`;

        fetch(updateWalkAPI, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(editData)
        })
        .then((response) => {
            if (response.ok) {
                setWalkMessage("Walking data successfully updated");
                setIsEditing(false);
                props.setNewItem(response);
            } else {
                console.log(response);
                setWalkMessage("Error updating walking data");
            }
        });
    };

    const isSearchMatch = () => {
        if (!searchCriteria) return false;
        let matches = true;
        if (searchCriteria.dateTime) {
            matches = matches && walkingData.dateTime.startsWith(searchCriteria.dateTime);
        }
        if (searchCriteria.distance) {
            matches = matches && parseFloat(walkingData.distance) === parseFloat(searchCriteria.distance);
        }
        console.log(`Matching entry for distance ${distance}:`, matches);
        return matches;
    };

    return (
        <div className={`WalkContainer walkhistory ${isSearchMatch() ? 'highlight' : ''}`}>
            {isEditing ? (
                <>
                    
                    <label> Steps: </label>
                    <input type="number" value={editData.steps} onChange={(e) => handleEditChange(e, 'steps')} className="edit-input" />
                    
                    <label> Distance (km): </label>
                    <input type="number" value={editData.distance} onChange={(e) => handleEditChange(e, 'distance')} className="edit-input"/>
                    
                    <label> Calories Burned: </label>
                    <input type="number" value={editData.caloriesBurned} onChange={(e) => handleEditChange(e, 'caloriesBurned')} className="edit-input" />
                    
                    <label> Duration (mins): </label>
                    <input type="number" value={editData.duration} onChange={(e) => handleEditChange(e, 'duration')} className="edit-input"/>
                    
                    <label> Speed (km/h): </label>
                    <input type="number" value={editData.speed} onChange={(e) => handleEditChange(e, 'speed')} className="edit-input" />
                    
                    <label> Date & Time: </label>
                    <input type="datetime-local" value={editData.dateTime} onChange={(e) => handleEditChange(e, 'dateTime')}  className="edit-input"/>
                    
                    <button className="cancel-btn" onClick={toggleEdit}>Cancel</button>
                    <button className="update-btn" onClick={handleUpdate}>Save</button>
                    
                </>
            ) : (
                <>
                    <h1> {walkType} </h1>
                    <h2>Steps: {steps}</h2>
                    <h2> Distance: {distance}km </h2>
                    <h2>Calrories Burned: {caloriesBurned}</h2>
                    <h2>Duration: {duration}mins</h2>
                    <h2>Speed: {speed}km/h</h2>
                    <h2>Date Time: {dateTime}</h2>
                    <button onClick={toggleEdit} className="edit-btn">Edit</button>
                    <button onClick={handleDelete} className="delete-btn">Delete</button>
                    {walkMessage && <p className={(walkMessage.includes("deleted") || walkMessage.includes("updated")) ? "greenWalkMessage" : "redWalkMessage"}>
                        {walkMessage}
                    </p>}
                </>
            )}
        </div>
    );
};

export default WalkingHistory;

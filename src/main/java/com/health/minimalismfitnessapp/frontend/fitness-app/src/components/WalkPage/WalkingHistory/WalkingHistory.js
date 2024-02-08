import "./WalkingHistory.css";
import { useState } from "react";

const WalkingHistory = (props) => {
    const { walkType = '', steps = '', distance = '', caloriesBurned = '', duration = '', speed = '', dateTime = '', activity = '', id = '' } = props.walkingData || {};
    const [walkMessage, setWalkMessage] = useState("");
    const [isEditing, setIsEditing] = useState(false);
    const [editData, setEditData] = useState({
        walkType, steps, distance, caloriesBurned, duration, speed, dateTime, activity
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

    return (
        <div className="WalkingContainer">
            {isEditing ? (
                <>
                    {/* <input type="text" value={editData.walkType} onChange={(e) => handleEditChange(e, 'walkType')} /> */}
                    <input type="number" value={editData.steps} onChange={(e) => handleEditChange(e, 'steps')} />
                    <input type="number" value={editData.distance} onChange={(e) => handleEditChange(e, 'distance')} />
                    <input type="number" value={editData.caloriesBurned} onChange={(e) => handleEditChange(e, 'caloriesBurned')} />
                    <input type="number" value={editData.duration} onChange={(e) => handleEditChange(e, 'duration')} />
                    <input type="number" value={editData.speed} onChange={(e) => handleEditChange(e, 'speed')} />
                    <input type="datetime-local" value={editData.dateTime} onChange={(e) => handleEditChange(e, 'dateTime')} />
                    <button onClick={handleUpdate}>Save</button>
                    <button onClick={toggleEdit}>Cancel</button>
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
                    <h2>Activity Type: {activity}</h2>
                    <button onClick={toggleEdit}>Edit</button>
                    <button onClick={handleDelete}>Delete</button>
                    {walkMessage && <p className={(walkMessage.includes("deleted") || walkMessage.includes("updated")) ? "greenWalkMessage" : "redWalkMessage"}>
                        {walkMessage}
                    </p>}
                </>
            )}
        </div>
    );
};

export default WalkingHistory;

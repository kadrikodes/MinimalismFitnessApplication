import React from 'react';
import './WalkingItem.css';


const WalkingItem = ({ data, onEdit, onDelete }) => {
    return (
      <div className="WalkingItem">
        <div className="walkingItemData">
        <p>Steps: {data.steps}</p>
        <p>Distance: {data.distance}km</p>
        <p>caloriesBurned: {data.caloriesBurned}</p>
        <p>Duration: {data.duration}mins</p>
        <p>Speed: {data.speed}km/hr</p>
        </div>
        
        <button onClick={() => onEdit(data)}>Edit</button>
        <button onClick={() => onDelete(data.id)}>Delete</button>
      </div>
    );
  };

  export default WalkingItem;
  
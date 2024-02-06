import React from 'react';

const WalkingItem = ({ data, onEdit, onDelete }) => {
    return (
      <div className="WalkingItem">
        <p>Steps: {data.steps}</p>
        <p>Distance: {data.distance}km</p>
        {/* Add more details as needed */}
        <button onClick={() => onEdit(data)}>Edit</button>
        <button onClick={() => onDelete(data.id)}>Delete</button>
      </div>
    );
  };

  export default WalkingItem;
  
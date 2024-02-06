import React from 'react';
import WalkingItem from './WalkingItem';

const WalkingActivityList = ({ walkingData, onEdit, onDelete }) => {
    return (
      <div className="WalkingActivityList">
        {walkingData.map((data) => (
          <WalkingItem key={data.id} data={data} onEdit={onEdit} onDelete={onDelete} />
        ))}
      </div>
    );
  };

  export default WalkingActivityList;
  
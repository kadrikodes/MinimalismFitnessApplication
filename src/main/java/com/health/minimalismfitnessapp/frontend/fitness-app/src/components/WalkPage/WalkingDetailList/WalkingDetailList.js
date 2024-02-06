
import React, { useState, useEffect } from 'react';
import WalkingActivityList from './WalkingActivityList';
import WalkingForm from './WalkingForm';
import { getWalkingData, addWalkingData, updateWalkingData, deleteWalkingData } from '../services/WalkingService';

const WalkingDetailList = () => {
  const [walkingData, setWalkingData] = useState([]);
  const [selectedWalking, setSelectedWalking] = useState(null);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchWalkingData();
  }, []);

  const fetchWalkingData = async () => {
    const data = await getWalkingData();
    setWalkingData(data);
  };

  const handleAddWalking = async (walking) => {
    const newWalking = await addWalkingData(walking);
    setWalkingData([...walkingData, newWalking]);
  };

  const handleEditWalking = (walking) => {
    setSelectedWalking(walking);
    setIsEditing(true);
  };

  const handleUpdateWalking = async (walking) => {
    const updated = await updateWalkingData(walking);
    const updatedData = walkingData.map((item) => (item.id === updated.id ? updated : item));
    setWalkingData(updatedData);
    setIsEditing(false);
    setSelectedWalking(null);
  };

  const handleDeleteWalking = async (id) => {
    await deleteWalkingData(id);
    const updatedData = walkingData.filter((item) => item.id !== id);
    setWalkingData(updatedData);
  };

  return (
    <div>
      <h2>Walking Activities</h2>
      {isEditing ? (
        <WalkingForm initialData={selectedWalking} onSubmit={handleUpdateWalking} onCancel={() => setIsEditing(false)} />
      ) : (
        <WalkingForm onSubmit={handleAddWalking} />
      )}
      <WalkingActivityList
        walkingData={walkingData}
        onEdit={handleEditWalking}
        onDelete={handleDeleteWalking}
      />
    </div>
  );
};

export default WalkingDetailList;

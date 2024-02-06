import React, { useState, useEffect } from 'react';
import WalkingActivityList from './WalkingActivityList';
import WalkingForm from './WalkingForm';
import { getWalkingData, addWalkingData, updateWalkingData, deleteWalkingData } from '../Service/WalkingService';

const WalkingDetailList = () => {
  const [walkingData, setWalkingData] = useState([]);
  const [selectedWalking, setSelectedWalking] = useState(null);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchWalkingData();
  }, []);

  const fetchWalkingData = async () => {
    try {
      const data = await getWalkingData();
      setWalkingData(data);
    } catch (error) {
      console.error('Failed to fetch walking data:', error);
    }
  };

  const handleAddWalking = async (walking) => {
    try {
      const newWalking = await addWalkingData(walking);
      setWalkingData([...walkingData, newWalking]);
    } catch (error) {
      console.error('Failed to add walking data:', error);
    }
  };

  const handleEditWalking = (walking) => {
    setSelectedWalking(walking);
    setIsEditing(true);
  };

  const handleUpdateWalking = async (walking) => {
    try {
      const updated = await updateWalkingData(walking);
      const updatedData = walkingData.map((item) => (item.id === updated.id ? updated : item));
      setWalkingData(updatedData);
      setIsEditing(false);
      setSelectedWalking(null);
    } catch (error) {
      console.error('Failed to update walking data:', error);
    }
  };

  const handleDeleteWalking = async (id) => {
    try {
      await deleteWalkingData(id);
      const updatedData = walkingData.filter((item) => item.id !== id);
      setWalkingData(updatedData);
    } catch (error) {
      console.error('Failed to delete walking data:', error);
    }
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

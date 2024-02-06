// src/components/WalkPage/WalkingDetails/WalkingForm.js

import React, { useState } from 'react';

const WalkingForm = ({ initialData = {}, onSubmit, onCancel }) => {
  const [formData, setFormData] = useState(initialData);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="number"
        name="steps"
        placeholder="Steps"
        value={formData.steps || ''}
        onChange={handleChange}
      />
      <input
        type="number"
        step="0.1"
        name="distance"
        placeholder="Distance (km)"
        value={formData.distance || ''}
        onChange={handleChange}
      />
      <input
        type="number"
        name="caloriesBurned"
        placeholder="Calories Burned"
        value={formData.caloriesBurned || ''}
        onChange={handleChange}
      />
      <input
        type="number"
        step="0.1"
        name="duration"
        placeholder="Duration (mins)"
        value={formData.duration || ''}
        onChange={handleChange}
      />
      <input
        type="number"
        step="0.1"
        name="speed"
        placeholder="Speed (km/h)"
        value={formData.speed || ''}
        onChange={handleChange}
      />
      <button type="submit">Save</button>
      <button type="button" onClick={onCancel}>Cancel</button>
    </form>
  );
};

export default WalkingForm;

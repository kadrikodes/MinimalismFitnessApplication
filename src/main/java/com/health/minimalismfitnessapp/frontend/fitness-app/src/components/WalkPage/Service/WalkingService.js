const API_BASE_URL = 'http://localhost:8080/walking';

export const getWalkingData = async () => {
  const response = await fetch(`${API_BASE_URL}/allWalkingData`);
  if (!response.ok) {
    throw new Error('Failed to fetch walking data');
  }
  return response.json();
};

export const addWalkingData = async (walkingData) => {
  const response = await fetch(`${API_BASE_URL}/addWalkingData`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(walkingData),
  });
  if (!response.ok) {
    throw new Error('Failed to add walking data');
  }
  return response.json();
};

export const updateWalkingData = async (walkingData) => {
  const response = await fetch(`${API_BASE_URL}/update/${walkingData.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(walkingData),
  });
  if (!response.ok) {
    throw new Error('Failed to update walking data');
  }
  return response.json();
};

export const deleteWalkingData = async (id) => {
  const response = await fetch(`${API_BASE_URL}/delete/${id}`, {
    method: 'DELETE',
  });
  if (!response.ok) {
    throw new Error('Failed to delete walking data');
  }
};

import React, { useState, useEffect } from 'react';
import "./UserPage.css"; 

const UserPage = () => {
    const [userData, setUserData] = useState(null);
    const [formData, setFormData] = useState({
        name: '',
        height: '',
        weight: '',
        gender: '',
        birthdate: '',
        profilePicture: null
    });

    useEffect(() => {
        fetch('http://localhost:8080/users/userId/1')
            .then(response => response.json())
            .then(data => {
                setUserData(data);
                setFormData({
                    name: data.name,
                    height: data.height,
                    weight: data.weight,
                    gender: data.gender,
                    birthdate: data.birthdate
                });
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSaveChanges = () => {
        fetch('http://localhost:8080/users/updateUser/1', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(updatedUserData => {
            setUserData(updatedUserData);
            if (formData.profilePicture) {
                setUserData(prevUserData => ({
                    ...prevUserData,
                    profilePicture: formData.profilePicture
                }));
            }
        })
        .catch(error => console.error('Error updating data:', error));
    };

    const handleFileChange = e => {
        const file = e.target.files[0];
        setFormData(prevState => ({
            ...prevState,
            profilePicture: file
        }));
    };

    return (
        <div className="desktop">
            <div className="user-page-container">
                <div className="left-box">
                    <h2>Your Profile </h2>
                    {userData && (
                        <div>
                             {userData.profilePicture ? (
                <img src={URL.createObjectURL(userData.profilePicture)} alt="Profile" />
            ) : (
                <img src="\default-user-icon.png" alt="Default Image" />
            )}
                            <p>Name: {userData.name}</p>
                            <p>Height (cm): {userData.height}</p>
                            <p>Weight (kg): {userData.weight}</p>
                            <p>Gender: {userData.gender}</p>
                            <p>Birthdate: {userData.birthdate}</p>
                        </div>
                    )}
                </div>
                <div className="right-box">
                    <h2>Edit your Info </h2>
                    <div className="edit-container">
                        <label>Name: </label>
                        <input type="text" name="name" value={formData.name} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Height (cm): </label>
                        <input type="number" name="height" value={formData.height} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Weight (kg): </label>
                        <input type="number" name="weight" value={formData.weight} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Birthdate: </label>
                        <input type="date" name="birthdate" value={formData.birthdate} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Profile Picture:</label>
                        <input type="file" accept="image/*" name="profilePicture" onChange={handleFileChange} />
                    </div>
                    
                    <button className="saveUserChanges" onClick={handleSaveChanges}>Save Changes</button>
                </div>
            </div>
        </div>
    );         
}

export default UserPage;

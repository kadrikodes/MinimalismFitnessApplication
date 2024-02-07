import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
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
        // Send request to update user data in the backend
        fetch('http://localhost:8080/users/updateUser/1', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(updatedUserData => {
            setUserData(updatedUserData); // Update local state with new user data
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
            <Header />
            <NavBar />
            <div className="user-page-container">
                <div className="left-box">
                <h2>Your Profile: </h2>
                    {userData && (
                        <div>
                            {formData.profilePicture && (
                                <img src={URL.createObjectURL(formData.profilePicture)} alt="Profile" />
                            )}
                            <p>Name: {userData.name}</p>
                            <p>Height (m): {userData.height}</p>
                            <p>Weight (kg): {userData.weight}</p>
                            <p>Gender: {userData.gender}</p>
                            <p>Birthdate: {userData.birthdate}</p>
                        </div>
                    )}
                </div>
                <div className="right-box">
                    <h2>Edit User Information: </h2>
                    <div className="edit-container">
                        <label>Name: </label>
                        <input type="text" name="name" value={formData.name} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Height (m): </label>
                        <input type="number" name="height" value={formData.height} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Weight (kg): </label>
                        <input type="number" name="weight" value={formData.weight} onChange={handleInputChange} />
                    </div>
                    <div className="edit-container">
                        <label>Gender: </label>
                        <select name="gender" value={formData.gender} onChange={handleInputChange}>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
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
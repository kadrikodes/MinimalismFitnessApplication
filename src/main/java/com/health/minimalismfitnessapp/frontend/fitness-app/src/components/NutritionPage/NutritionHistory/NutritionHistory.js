import { useState } from "react";
import "./NutritionHistory.css"

const NutritionHistory = (props) => {

    const { mealType = '', foodName = '', calories = '', protein = '', carbohydrates = '', fats = '', id = ''} = props.nutritionData || {};
    const [isEditing, setIsEditing] = useState(false);
    const [editData, setEditData] = useState({
        mealType: mealType || '',
        foodName: foodName || '',
        calories: calories || '',
        protein: protein || '',
        carbohydrates: carbohydrates || '',
        fats: fats || '',
        user: {id: "1"},
      });
      
    const handleEdit = () => { setIsEditing(!isEditing)};

    const handleEditChange = (event, field) => {
        setEditData({ ...editData, [field]: event.target.value });
    };

    const handleUpdate = (event) => {
        event.preventDefault();
        const updateNutritionAPI = `http://localhost:8080/nutrition/${id}`;

        fetch(updateNutritionAPI, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(editData)
        })
        .then((response) => {
            if (response.ok) {
                props.setNewItem(response)
                handleEdit();
            } else {
                console.log(response);
            }
        });
    };

    const handleDelete = () =>{
        const deleteNutritionAPI = `http://localhost:8080/nutrition/${id}`

        fetch(deleteNutritionAPI, {
            method: 'DELETE',
        }).then((response) => {
            if (response.ok){
                props.setNewItem(response)
            } else{
                console.log(response)
            }
        })
    }

    return (
    <div className="NutritionContainer nutritionhistory"> 
        {isEditing ? (
            
            <form className="edit-form" onSubmit={handleUpdate}>

            
            <label> Type: </label>
            <input type="text" placeholder={`${mealType}`} className="edit-input" value={editData.mealType} onChange={(e) => handleEditChange(e, 'mealType')}/>

            <label>Name: </label> 
            <input type="text" placeholder={`${foodName}`} className="edit-input" value={editData.foodName} onChange={(e) => handleEditChange(e, 'foodName')}/>

            <label>Calories (kCal): </label>
            <input type="text" placeholder={`${calories}`} className="edit-input" value={editData.calories} onChange={(e) => handleEditChange(e, 'calories')}/>

            <label>Protein (%): </label>
            <input type="number"placeholder={`${protein}`} max="100" className="edit-input" value={editData.protein} onChange={(e) => handleEditChange(e, 'protein')}/>

            <label>Carbohydrates (%): </label>
            <input type="number"placeholder={`${carbohydrates}%`}  max="100" className="edit-input" value={editData.carbohydrates} onChange={(e) => handleEditChange(e, 'carbohydrates')}/>

            <label>Fats (%): </label>
            <input type="number" placeholder={`${fats}%`} max="100" className="edit-input" value={editData.fats} onChange={(e) => handleEditChange(e, 'fats')}/>

            <button className="cancel-btn" onClick={handleEdit}>Cancel</button>
            <input type="submit" className="update-btn" onClick={handleUpdate} value="Update" />
            </form>
           
        ): (
            <>
            <h1> {mealType}</h1>
            <h2>{foodName} ({calories}kCal)</h2>
            <h2>Carbohydrates: {carbohydrates}%</h2>
            <h2>Protein: {protein}%</h2>
            <h2>Fats: {fats}%</h2>
            <button className="edit-btn" onClick={handleEdit}>Edit</button>
            <button className="delete-btn" onClick={handleDelete}>Delete</button>
            </>
        )}
        
    </div>);
}

export default NutritionHistory;
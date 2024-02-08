import { useState } from "react";
import "./NutritionHistory.css"

const NutritionHistory = (props) => {

    const { mealType = '', foodName = '', calories = '', protein = '', carbohydrates = '', fats = '', id = ''} = props.nutritionData || {};
    const [isEditing, setIsEditing] = useState(false);


    const handleEdit = () => { setIsEditing(!isEditing)};

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
            
            <form className="edit-form">

            
            <label> Type: </label>
            <input type="text" placeholder={`${mealType}`} className="edit-input"/>

            <label>Name: </label> 
            <input type="text" placeholder={`${foodName}`} className="edit-input"/>

            <label>Calories (kCal): </label>
            <input type="text" placeholder={`${calories}`} className="edit-input"/>

            <label>Protein (%): </label>
            <input type="number"placeholder={`${protein}`} max="100" className="edit-input"/>

            <label>Carbohydrates (%): </label>
            <input type="number"placeholder={`${carbohydrates}%`}  max="100" className="edit-input"/>

            <label>Fats (%): </label>
            <input type="number" placeholder={`${fats}%`} max="100" className="edit-input"/>

            <button className="cancel-btn" onClick={handleEdit}>Cancel</button>
            <button className="update-btn" onClick={handleDelete}>Update</button>
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
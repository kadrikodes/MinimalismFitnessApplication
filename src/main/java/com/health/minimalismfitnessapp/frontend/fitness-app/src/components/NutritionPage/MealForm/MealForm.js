import { useState } from "react";
import "./MealForm.css"

const MealForm = (props) => {

  const [mealMessage, setMealMessage] = useState("");

    const handleSubmit = (event) => {
      event.preventDefault();

      const saveMealAPI = 'http://localhost:8080/nutrition/addNutritionData';
       
      const mealType = event.target.elements.mealType.value;
      const foodName = event.target.elements.foodName.value;
      const calories = event.target.elements.calories.value;
      const protein = event.target.elements.protein.value;
      const carbohydrates = event.target.elements.carbohydrates.value;
      const fats = event.target.elements.fats.value;
      const userID = "1"

      const mealData = {
        foodName,
        calories,
        protein,
        carbohydrates,
        fats,
        mealType,
        user: { id: userID},  
      };

        fetch(saveMealAPI, {
          method:'post',
          headers: { 'Content-Type': 'application/json',},
          body: JSON.stringify(mealData),
        })
        .then((response) => {
          if (response.ok){
            setMealMessage("Meal data consumed!")
            props.setNewItem(response)
          } else{
            setMealMessage("Meal data tastes bad!")
          }
          })
    }



    return (
    <div> 
        <form id="mealForm" onSubmit={handleSubmit}>

        <label> Type: </label>
        <select name="mealType">
          <option value="Breakfast">Breakfast</option>
          <option value="Lunch">Lunch</option>
          <option value="Dinner">Dinner</option>
          <option value="Snack">Snack</option>
        </select>

        <label>Name: </label>
        <input type="text" name="foodName" />

        <label>Calories (kCal): </label>
        <input type="text" name="calories"/>

        <label>Protein (%): </label>
        <input type="number" name="protein"  max="100" />

        <label>Carbohydrates (%): </label>
        <input type="number" name="carbohydrates" max="100"/>

        <label>Fats (%): </label>
        <input type="number" name="fats" max="100" />
      

        <input type="submit" className="submitButton"/>


            {mealMessage && <p className={mealMessage.includes("consumed") ? "greenMealMessage" : "redMealMessage"}>
              {mealMessage}
              </p>}

    </form>
    </div>);
}

export default MealForm;
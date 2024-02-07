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
        <form onSubmit={handleSubmit}>

        Meal Type:
        <select name="mealType">
          <option value="Breakfast">Breakfast</option>
          <option value="Lunch">Lunch</option>
          <option value="Dinner">Dinner</option>
          <option value="Snack">Snack</option>
        </select>


  
        Food Name:
        <input type="text" name="foodName" />
 


        Calories:
        <input type="text" name="calories"/>



        Protein (%):
        <input type="number" name="protein"  max="100" />
 


        Carbohydrates (%):
        <input type="number" name="carbohydrates" max="100"/>
    


        Fats (%):
        <input type="number" name="fats" max="100" />
      

      <button type="submit">Submit</button>


            {mealMessage && <p className={mealMessage.includes("consumed") ? "greenMealMessage" : "redMealMessage"}>
              {mealMessage}
              </p>}

    </form>
    </div>);
}

export default MealForm;
import "./MealForm.css"

const MealForm = () => {

    



    return (
    <div> 
        <form>
      <label>
        Meal Type:
        <select>
          <option value="breakfast">Breakfast</option>
          <option value="lunch">Lunch</option>
          <option value="dinner">Dinner</option>
          <option value="snack">Snack</option>
        </select>
      </label>

      <label>
        Food Name:
        <input type="text" />
      </label>

      <label>
        Calories:
        <input type="text" />
      </label>

      <label>
        Protein (%):
        <input type="number" max="100" />
      </label>

      <label>
        Carbohydrates (%):
        <input type="number" max="100"/>
      </label>

      <label>
        Fats (%):
        <input type="number" max="100" />
      </label>

      <button type="submit">Submit</button>
    </form>
    </div>);
}

export default MealForm;
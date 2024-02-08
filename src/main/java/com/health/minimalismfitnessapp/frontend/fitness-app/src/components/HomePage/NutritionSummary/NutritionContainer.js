import "./NutritionContainer.css";

const NutritionContainer = (props) => {

    const { mealType = '', foodName = '', calories = '', protein = '', carbohydrates = '', fats = ''} = props.nutritionData || {};

    if (!props.nutritionData){
        return(
            <div className="NutritionContainer">
                <h1>No nutrition data</h1>
                <img src="\nutrition.gif" alt="NutritionImg" id="nutrition-sum-img"/>
            </div>
        )
    }

    return (
    <div className="NutritionContainer"> 
        <h1> {mealType}</h1>
        <h2>{foodName} ({calories}kCal)</h2>
        <h2>Carbohydrates: {carbohydrates}%</h2>
        <h2>Protein: {protein}%</h2>
        <h2 >Fats: {fats}%</h2>
    </div>);
}

export default NutritionContainer;
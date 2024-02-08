import "./NutritionHistory.css"

const NutritionHistory = (props) => {

    const { mealType = '', foodName = '', calories = '', protein = '', carbohydrates = '', fats = '', id = ''} = props.nutritionData || {};

    const handleEdit = () => {
            
    }

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
    <div className="NutritionContainer"> 
        <h1> {mealType}</h1>
        <h2>{foodName} ({calories}kCal)</h2>
        <h2>Carbohydrates: {carbohydrates}%</h2>
        <h2>Protein: {protein}%</h2>
        <h2>Fats: {fats}%</h2>
        <button className="edit-btn" onClick={handleEdit}>Edit</button>
        <button className="delete-btn" onClick={handleDelete}>Delete</button>
    </div>);
}

export default NutritionHistory;
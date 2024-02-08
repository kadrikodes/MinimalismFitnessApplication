import { useEffect, useState } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import NutritionHistory from "./NutritionHistory/NutritionHistory";
import "./NutritionPage.css"
import MealForm from "./MealForm/MealForm";


const NutritionPage = () => {

    const [nutritionHistory, setNutritionHistory] = useState([]);
    const [newItem, setNewItem] = useState(null);

    const updateNutritionHistory = () => {
        const findAllNutritionAPI = 'http://localhost:8080/nutrition/user/name/Rais';

        fetch(findAllNutritionAPI)
            .then((response) => {return response.json();})
            .then((data) => {setNutritionHistory(data);} )
    }

    useEffect(() => {
        updateNutritionHistory();
    }, [newItem]);

    



    return(
    <div className="desktop">
        <div className="header">
        <Header />
        <NavBar />
        </div>
        <div className="row">
            <div className="nutrition-column">
                <h1 className="nutritionHeading">Nutriton History</h1>
                { nutritionHistory.map(
                    (nutritionData) => (<NutritionHistory key={nutritionData.id} nutritionData={nutritionData} setNewItem={setNewItem} /> )
                )}
            </div>
            <div className="nutrition-column">
                <h1 className="nutritionHeading">Enter meal</h1>
                <MealForm setNewItem={setNewItem}/>
            </div>
        </div>

    </div>)
    

}

export default NutritionPage;


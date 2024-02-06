import { useEffect, useState } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import NutritionHistory from "./NutritionHistory/NutritionHistory";
import "./NutritionPage.css"


const NutritionPage = () => {

    const [nutritionHistory, setNutritionHistory] = useState(null);

    useEffect(() => {
        const findAllNutritionAPI = 'http://localhost:8080/nutrition/user/name/Rais';

        fetch(findAllNutritionAPI)
            .then((response) => {return response.json();})
            .then((data) => { setNutritionHistory(data);} )
    }, [])

    



    return(
    <div className="desktop">
        <div className="header">
        <Header />
        <NavBar />
        </div>

        <div className="row">
            <div className="column">
                <h1>Nutriton History</h1>
                {nutritionHistory !== null && nutritionHistory.map(
                    (nutritionData) => (<NutritionHistory nutritionData={nutritionData} /> )
                )}
            </div>
            <div className="column">
                <h1>Enter meal</h1>
            </div>
        </div>

    </div>)
    

}

export default NutritionPage;


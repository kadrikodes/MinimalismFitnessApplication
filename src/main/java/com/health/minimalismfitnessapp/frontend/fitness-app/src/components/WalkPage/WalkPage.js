import { useEffect, useState } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import WalkingHistory from "./WalkingHistory/WalkingHistory";
import "./WalkPage.css"
import WalkForm from "./WalkForm/WalkForm";


const WalkPage = () => {

    const [walkingHistory, setWalkingHistory] = useState([]);
    const [newItem, setNewItem] = useState(null);

    const updateWalkingHistory = () => {
        const findAllWalkingAPI = 'http://localhost:8080/walking/name/Rais';

        fetch(findAllWalkingAPI)
            .then((response) => {return response.json();})
            .then((data) => {setWalkingHistory(data);} )
    }

    useEffect(() => {
        updateWalkingHistory();
    }, [newItem]);

    return(
    <div className="desktop">
        <div className="row">
            <div className="column">
                <h1 className="walkingHeading">Walking History</h1>
                { walkingHistory.map(
                    (walkingData) => (<WalkingHistory walkingData={walkingData} /> )
                )}
            </div>
            <div className="column">
                <h1 className="walkingHeading">Enter walking data</h1>
                <WalkForm setNewItem={setNewItem}/>
            </div>
        </div>

    </div>)   

}

export default WalkPage;
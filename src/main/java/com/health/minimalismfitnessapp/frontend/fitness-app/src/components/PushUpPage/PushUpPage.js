import { useState, useEffect } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import "./PushUpPage.css";
import PushUpContainer from "../HomePage/PushUpSummary/PushUpContainer";
import TargetTracker from "./TargetTracker/TargetTracker";
import TargetInput from "./TargetTracker/TargetInput";



const PushUpPage = () => {

    const [pushUpData, setPushUpData] = useState(null);

    useEffect(() => {
        const pushUpAPI = 'http://localhost:8080/pushups/1';

        fetch(pushUpAPI)
        .then((response) => {return response.json();})
        .then((data) => {setPushUpData(data);})
    }, [])

    return (
        <div className="desktop">
            <Header />
            <NavBar />
            <div className="row">
            <div className="colum">
            <PushUpContainer pushUpData={pushUpData} />
            </div>
            <div className="column">
            <TargetTracker />
            </div>
            
                
            </div>
        </div>
    );
};

export default PushUpPage;
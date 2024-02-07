import { useState, useEffect } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import "./PushUpPage.css";
import Counter from "./Counter/Counter";
import PushUpContainer from "../HomePage/PushUpSummary/PushUpContainer";
import TargetTracker from "./TargetTracker/TargetTracker";


const PushUpPage = () => {

    const [pushUpData, setPushUpData] = useState(null);

    useEffect(() => {
        const pushUpAPI = 'http://localhost:8080/pushups/1';

        fetch(pushUpAPI)
        .then((response) => {return response.json();})
        .then((data) => {setPushUpData(data);})
    }, [])

    return (
        <div className="pushUpPage">
            <Header />
            <NavBar />
            {/* <Counter /> */}
            <PushUpContainer />
            <TargetTracker />
        </div>
    )


}

export default PushUpPage;
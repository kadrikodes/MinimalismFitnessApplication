import { useEffect, useState } from "react";
import "./PushupPage.css";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";


const PushupPage = () => {
    const [pushUpData, setPushUpData] = useState(null);

    useEffect(() => {
        const pushUpAPI = 'http://localhost:8080/pushups/1';

        fetch(pushUpAPI)
        .then((response) => {return response.json();})
        .then((data) => {setPushUpData(data);})
    }, [])


    return(
        <>
        <div className="desktop">
            <Header />
            <NavBar />
            <div className="pushup-container">
                <div className="left-box">
                    <h2>Pushup Data: </h2>
                </div>

            </div>
        </div>
        </>
    );
};

export default PushupPage;
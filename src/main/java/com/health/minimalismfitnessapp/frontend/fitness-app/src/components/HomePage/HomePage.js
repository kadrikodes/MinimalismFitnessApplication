import React, { useState, useEffect } from "react";
import "./HomePage.css";
import NavBar from "./NavBar/NavBar";
import Header from "./Header/Header";
import SleepContainer from "./SleepSummary/SleepContainer";
import WalkContainer from "./WalkSummary/WalkContainer";
import PushUpContainer from "./PushUpSummary/PushUpContainer";
import NutritionContainer from "./NutritionSummary/NutritionContainer";
import UserContainer from "./UserSummary/UserContainer";

const HomePage = () => {

  const [userData, setUserData] = useState(null);
  const [nutritionData, setNutritionData] = useState(null);
  const [sleepData, setSleepData] = useState(null);
  const [walkingData, setWalkingData] = useState(null);
  const [pushUpData, setPushUpData] = useState(null);



  useEffect(() => {
    const userAPI = 'http://localhost:8080/users/userId/1';
    const nutritionAPI = 'http://localhost:8080/nutrition/1';
    const sleepAPI = 'http://localhost:8080/sleeptracker/1';
    const walkingAPI = 'http://localhost:8080/walking/1';
    const pushUpAPI = 'http://localhost:8080/pushups/1';
  

    fetch(userAPI)
        .then((response) => {return response.json();})
        .then((data) => { setUserData(data);} )

    fetch(nutritionAPI)
        .then((response) => { return response.json();})
        .then((data) => {setNutritionData(data);})

    fetch(sleepAPI)
        .then((response) => {return response.json();})
        .then((data) => {setSleepData(data);})

    fetch(walkingAPI)
        .then((response) => {return response.json();})
        .then((data) => {setWalkingData(data);})

    fetch(pushUpAPI)
    .then((response) => {return response.json();})
    .then((data) => {setPushUpData(data);})

  }, [])

  


  return (
    <div className="desktop">
          <Header />
          <NavBar />
          <UserContainer userData={userData} />
          <div className="containers">
            <NutritionContainer nutritionData={nutritionData} />
            <SleepContainer sleepData={sleepData} />
          </div>
          <div className="containers">
            <WalkContainer walkingData={walkingData}/>
            <PushUpContainer pushUpData={pushUpData} /> 
          </div>

      {/* {userData ? (<pre>{JSON.stringify(userData, null, 2)}</pre>) : (<p>Loading...</p>)} */}
    </div>
  );
};


export default HomePage;
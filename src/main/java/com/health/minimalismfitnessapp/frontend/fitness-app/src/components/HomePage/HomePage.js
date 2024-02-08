import React, { useState, useEffect } from "react";
import "./HomePage.css";
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
        .then((data) => { setUserData(data);})
        .catch((error) => {console.error(error)})

    fetch(nutritionAPI)
        .then((response) => { return response.json();})
        .then((data) => {setNutritionData(data);})
        .catch((error) => {console.error(error)})

    fetch(sleepAPI)
        .then((response) => {return response.json();})
        .then((data) => {setSleepData(data);})
        .catch((error) => {console.error(error)})

    fetch(walkingAPI)
        .then((response) => {return response.json();})
        .then((data) => {setWalkingData(data);})
        .catch((error) => {console.error(error)})

    fetch(pushUpAPI)
        .then((response) => {return response.json();})
        .then((data) => {setPushUpData(data);})
        .catch((error) => {console.error(error)})

  }, [])




  return (
    <div className="desktop">
          <div className="row">
            <div className="column">
              <UserContainer userData={userData} />
            </div>
            <div className="column">
              <NutritionContainer nutritionData={nutritionData} />
              <SleepContainer sleepData={sleepData} />
            </div>
            <div className="column">
              <WalkContainer walkingData={walkingData}/>
              <PushUpContainer pushUpData={pushUpData} /> 
            </div>
          
            
            
          </div>
    </div>
  );
};


export default HomePage;
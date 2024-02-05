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

  useEffect(() => {
    const userAPI = 'http://localhost:8080/users/userId/1';

    fetch(userAPI)
        .then((response) => {return response.json();})
        .then((data) => { setUserData(data);} )
  }, [])

  


  return (
    <div className="desktop">
          <Header />
          <NavBar />
          <UserContainer userData={userData} />
          <div className="containers">
            <NutritionContainer />
            <SleepContainer />
          </div>
          <div className="containers">
            <WalkContainer />
            <PushUpContainer /> 
          </div>

      {/* {userData ? (<pre>{JSON.stringify(userData, null, 2)}</pre>) : (<p>Loading...</p>)} */}
    </div>
  );
};


export default HomePage;
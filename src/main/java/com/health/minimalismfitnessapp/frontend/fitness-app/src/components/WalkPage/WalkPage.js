import React, { useState, useEffect } from "react";
import "./WalkPage.css";
import NavBar from "./NavBar/NavBar";
import WalkingHeader from "./WalkingHeader/WalkPageHeader"; 
import WalkingSummary from ".WalkingDetails/WalkingSummary";
import WalkingDetailList from "./WalkingDetails/WalkingDetailList";

const WalkPage = () => {
  const [walkingData, setWalkingData] = useState(null);

  useEffect(() => {
    const walkingAPI = 'http://localhost:8080/walking/allWalkingData';

    fetch(walkingAPI)
      .then((response) => response.json())
      .then((data) => setWalkingData(data));
  }, []);

  return (
    <div className="walkPage">
      <NavBar />
      <WalkingHeader />
      <WalkingSummary walkingData={walkingData} />
      <WalkingDetailList walkingData={walkingData} />
    </div>
  );
};

export default WalkPage;

import React, { useState, useEffect } from "react";
import "./HomePage.css";


const HomePage = () => {

  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const userAPI = 'http://localhost:8080/users';

    fetch(userAPI)
        .then((response) => {return response.json();})
        .then((data) => setUserData(data) )
  }, [])


  return (
    <div className="desktop">
      <div className="overlap-wrapper">
        <div className="overlap">
          <div className="overlap-group">
            <div className="div">
              <div className="ellipse" />
              <div className="rectangle" />
              <div className="rectangle-2" />
              <div className="rectangle-3" />
              <div className="rectangle-4" />
            </div>
            <div className="overlap-group-2">
              <div className="ellipse-2" />
              <div className="minimalism-fitness">MINIMALISM FITNESS APP</div>
              <div className="rectangle-5" />
            </div>
          </div>
          <div className="text-wrapper">
          USER
          {userData ? (<pre>{JSON.stringify(userData, null, 2)}</pre>) : (<p>Loading...</p>)}
          </div>
          <div className="navbar">
            <div className="w-ALK">WALK</div>
            <div className="p-US-hup">PUSHUP</div>
            <div className="n-UTRITION">NUTRITION</div>
            <div className="s-LEEP">SLEEP</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
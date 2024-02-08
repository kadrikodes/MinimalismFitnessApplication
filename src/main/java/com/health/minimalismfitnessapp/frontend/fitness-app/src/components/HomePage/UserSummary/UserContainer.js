import React from "react";
import "./UserContainer.css";

const UserContainer = (props) => {
  const { name = "", height = "", weight = "", profilePicture } =
    props.userData || {};
  return (
    <div className="UserContainer">
      <h1>Welcome <span className="name">{name}</span>!</h1>
      {profilePicture && (
        <img src={profilePicture} alt="Profile" style={{ width: "300px", height: "300px" }} />
      )}
      {!profilePicture && (
        <img src="\default-user-icon.png" alt="Default Image" style={{ width: "300px", height: "300px" }} />
      )}
      <h2 className="height">Height: <span className="height-value">{height}cm</span></h2>
      <h2 className="weight">Weight: <span className="weight-value">{weight}kg</span></h2>
    </div>
  );
};

export default UserContainer;

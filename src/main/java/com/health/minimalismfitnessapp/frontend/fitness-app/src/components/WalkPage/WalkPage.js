import { useEffect, useState } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import WalkingHistory from "./WalkingHistory/WalkingHistory";
import "./WalkPage.css";
import WalkForm from "./WalkForm/WalkForm";
import WalkSearch from "./WalkSearch/WalkSearch";


const WalkPage = () => {

    const [walkingHistory, setWalkingHistory] = useState([]);
    const [newItem, setNewItem] = useState(null);
    const [searchCriteria, setSearchCriteria] = useState(null);

    const updateWalkingHistory = () => {
        const findAllWalkingAPI = 'http://localhost:8080/walking/name/Rais';

        fetch(findAllWalkingAPI)
            .then((response) => {return response.json();})
            .then((data) => {setWalkingHistory(data);} )
    }

    const handleSearchResults = (results) => {
      setWalkingHistory(results);
  };

    useEffect(() => {
        updateWalkingHistory();
    }, [newItem]);

    return(
    <div className="desktop">
        <div className="header">
        <Header />
        <NavBar />
        </div>

        <div className="row">
            <div className="column">
                <h1 className="walkingHeading">Walking History</h1>
                <WalkSearch onSearchResults={handleSearchResults} />
                { walkingHistory.map(
                    (walkingData, index) => (<WalkingHistory key={index} walkingData={walkingData} setNewItem={setNewItem} onSearchResults={handleSearchResults}/> )
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
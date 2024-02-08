import { useEffect, useState } from "react";
import WalkingHistory from "./WalkingHistory/WalkingHistory";
import "./WalkPage.css";
import WalkForm from "./WalkForm/WalkForm";
import WalkSearch from "./WalkSearch/WalkSearch";
import CalculateStepsToBurnCalories from './Calculations/CalculateStepsToBurnCalories';
import CalculateWeightLoss from './Calculations/CalculateWeightLoss';
import ScheduleWalkReminder from './Calculations/ScheduleWalkReminder';
import CheckDailyGoal from './Calculations/CheckDailyGoal';
import GetTotalCaloriesBurned from './Calculations/GetTotalCaloriesBurned';


const WalkPage = () => {

    const [walkingHistory, setWalkingHistory] = useState([]);
    const [newItem, setNewItem] = useState(null);
    const [searchCriteria, setSearchCriteria] = useState(null);
    const [calculatedSteps, setCalculatedSteps] = useState(null);

    const updateWalkingHistory = () => {
        const findAllWalkingAPI = 'http://localhost:8080/walking/name/Rais';

        fetch(findAllWalkingAPI)
            .then((response) => {return response.json();})
            .then((data) => {setWalkingHistory(data);} )
    }

    const handleSearchResults = (results, criteria) => {
      setWalkingHistory(results);
      setSearchCriteria(criteria);
      console.log("Updated search criteria in WalkPage:", criteria);
  };

    useEffect(() => {
        updateWalkingHistory();
    }, [newItem]);

    return(
    <div className="desktop">
        <div className="row">
            <div className="walk-column">
                <h1 className="walkingHeading">Walking History</h1>
                <WalkSearch onSearchResults={handleSearchResults} />
                { walkingHistory.map(
                    (walkingData, index) => (<WalkingHistory key={index} walkingData={walkingData} setNewItem={setNewItem} searchCriteria={searchCriteria}/> )
                )}
            </div>
            <div className="walk-column">
                <h1 className="walkingHeading">Enter walking data</h1>
                <WalkForm setNewItem={setNewItem}/>
                <CalculateStepsToBurnCalories onStepsCalculated={setCalculatedSteps} />
                {calculatedSteps && <p>Calculated Steps: {calculatedSteps}</p>}
                <h2>Calculate Weight Loss</h2>
              <CalculateWeightLoss />

              <h2>Schedule Walk Reminder</h2>
              <ScheduleWalkReminder />

              <h2>Check Daily Goal</h2>
              <CheckDailyGoal />
            </div>
        </div>

    </div>)   

}

export default WalkPage;
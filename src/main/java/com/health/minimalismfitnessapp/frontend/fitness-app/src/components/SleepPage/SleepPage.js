import { useEffect, useState } from "react";
import "./SleepPage.css";
import SleepContainer from "../HomePage/SleepSummary/SleepContainer";


const SleepPage = () => {
    const [sleepData, setSleepData] = useState(null);

    useEffect(() => {
        const sleepAPI = 'http://localhost:8080/sleeptracker/1';

        fetch(sleepAPI)
        .then((response) => {return response.json();})
        .then((data) => {setSleepData(data);})


    }, [])

    return (
        <div className="desktop">
              <div className="containers">
                <SleepContainer sleepData={sleepData} />
              </div>
          {/* {userData ? (<pre>{JSON.stringify(userData, null, 2)}</pre>) : (<p>Loading...</p>)} */}
        </div>
      );
    };

export default SleepPage;

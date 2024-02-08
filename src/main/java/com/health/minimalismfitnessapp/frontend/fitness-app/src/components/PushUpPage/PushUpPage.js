import { useState, useEffect } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import "./PushUpPage.css";
import PushUpContainer from "../HomePage/PushUpSummary/PushUpContainer";
import TrackerContainer from "./Tracker/TrackerContainer";
import InputContainer from "./Input/InputContainer";
import CounterContainer from "./Counter/CounterContainer";



const PushUpPage = () => {
    const [count, setCount] = useState(0);
    const [savedCount, setSavedCount] = useState(0);


    const [pushUpData, setPushUpData] = useState(null);

    useEffect(() => {
        const pushUpAPI = 'http://localhost:8080/pushups/1';

        fetch(pushUpAPI)
        .then((response) => {return response.json();})
        .then((data) => {setPushUpData(data);})
    }, [])

    const handleIncrement = () => {
        setCount(count + 1);
      };
    
      const handleDecrement = () => {
        if (count > 0) {
          setCount(count - 1);
        }
      };

      const handleSave = () => {
        setSavedCount(savedCount + count);
        setCount(0); 
      };
    
      const handleReset = () => {
        setSavedCount(0); 
      };

    return (
        <div className="desktop">
            <Header />
            <NavBar />
            <div className="row">
            <div className="colum">
            <PushUpContainer pushUpData={pushUpData} />
            </div>
            <div className="column">
            <TrackerContainer handleIncrement={handleIncrement} handleDecrement={handleDecrement}/>
            </div>
            <div className="column">
                <h2>Target Tracker:</h2>
                <div className="button-column">
              <button className="action-button" onClick={handleSave}>Save</button>
              <button className="action-button" onClick={handleReset}>Reset</button>
            </div>
            <InputContainer />
            <CounterContainer count={count} savedCount={savedCount} />
            </div>
            </div>
        </div>
    );
};

export default PushUpPage;
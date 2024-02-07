import { useState } from "react";
import "./WalkForm.css"

const WalkForm = (props) => {

    const [walkMessage, setWalkMessage] = useState("");

        const handleSubmit = (event) => {
            event.preventDefault();

            const saveWalkAPI = 'http://localhost:8080/walking/addWalkingData';

            const steps = event.target.elements.steps.value;
            const distance = event.target.elements.distance.value;
            const caloriesBurned = event.target.elements.caloriesBurned.value;
            const duration = event.target.elements.duration.value;
            const speed = event.target.elements.speed.value;
            const dateTime = event.target.elements.datetime.value;
            const userId = "1"

            const walkData = {
                steps,
                distance,
                caloriesBurned,
                duration,
                speed,
                dateTime,
                userData: {id: userId},
            };

            fetch(saveWalkAPI, {
                method:'post',
                headers: { 'Content-Type': 'application/json',},
                body: JSON.stringify(walkData),
            })
            .then((response) => {
                if (response.ok){
                    setWalkMessage("Walk data added!")
                    props.setNewItem(response)
                } else{
                    setWalkMessage("Walk some more !")
                }
            })
        }

        return (
            <div>
                <form onSubmit={handleSubmit}>

                    <label>Steps: </label>
                    <input type="number" name="steps" />

                    <label>Distance (km): </label>
                    <input type="number" step="0.1" name="distance" />

                    <label>Calories burned: </label>
                    <input type="number" name="caloriesBurned" />

                    <label>Duration (mins): </label>
                    <input type="number" step="0.1" name="duration" />

                    <label>Speed (km/h): </label>
                    <input type="number" step="0.1" name="speed" />

                    <label>Date & Time: </label>
                    <input type="datetime-local" name="datetime" defaultValueExpression="currentDate()" />

                    <input type="submit" className="submitButton"/>

                    {walkMessage && <p className={walkMessage.includes("added") ? "greenWalkMessage" : "redWalkMessage"}>
                        {walkMessage
                        }</p>}
                </form>
            </div>
        );
}

export default WalkForm;
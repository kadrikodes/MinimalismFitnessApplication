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

                    Steps:
                    <input type="number" name="steps" />

                    Distance (km):
                    <input type="number" step="0.1" name="distance" />

                    Calories Burned:
                    <input type="number" name="caloriesBurned" />

                    Duration (mins):
                    <input type="number" step="0.1" name="duration" />

                    Speed (km/h):
                    <input type="number" step="0.1" name="speed" />

                    Date & Time:
                    <input type="datetime-local" name="datetime" defaultValueExpression="currentDate()" />

                    <button type="submit">Submit</button>

                    {walkMessage && <p className={walkMessage.includes("added") ? "greenWalkMessage" : "redWalkMessage"}>
                        {walkMessage
                        }</p>}
                </form>
            </div>
        );
}

export default WalkForm;
import "./PushUpContainer.css";

const PushUpContainer = (props) => {

    const { numberOfPushUps = '', timeDuration = '', caloriesBurnt = ''} = props.pushUpData || {};


    return (
    <div className="PushUpContainer"> 
        <h1>PushUps</h1>
        <h2>Reps: {numberOfPushUps}</h2>
        <h2>Duration: {timeDuration}mins</h2>
        <h2>Calories Burned: {caloriesBurnt}kCal</h2>
    
    </div>);
}

export default PushUpContainer;
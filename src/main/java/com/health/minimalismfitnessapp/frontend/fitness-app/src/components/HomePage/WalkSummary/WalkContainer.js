import "./WalkContainer.css";

const WalkContainer = (props) => {

    
    const { steps = '', duration = '', distance = '', caloriesBurned = ''} = props.walkingData || {};

    return (
    <div className="WalkContainer"> 
        <h1>Walking</h1>
        <h2>Steps: {steps}</h2>
        <h2>Duration: {duration}mins</h2>
        <h2>Distance: {distance}km</h2>
        <h2>Calories Burned: {caloriesBurned}kCal</h2>

    </div>);
}

export default WalkContainer;
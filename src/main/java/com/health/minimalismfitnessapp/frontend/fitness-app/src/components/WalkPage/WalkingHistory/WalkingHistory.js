const WalkingHistory = (props) => {

    const { walkType = '', steps = '', distance = '', caloriesBurned = '', duration = '', speed = '', dateTime = ''} = props.walkingData || {};

    return (
        <div className="WalkingContainer">
            <h1> {walkType} </h1>
            <h2>Steps: {steps} </h2>
            <h2> Distance: {distance}km </h2>
            <h2>Calrories Burned: {caloriesBurned}</h2>
            <h2>Duration: {duration}mins</h2>
            <h2>Speed: {speed}km/h</h2>
            <h2>Date Time: {dateTime}</h2>
        </div>
    );
}

export default WalkingHistory;
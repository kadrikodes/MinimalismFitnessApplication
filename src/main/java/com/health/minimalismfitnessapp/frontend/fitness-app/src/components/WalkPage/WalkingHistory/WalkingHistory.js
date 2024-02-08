import "./WalkingHistory.css";

const WalkingHistory = (props) => {

    const { walkType = '', steps = '', distance = '', caloriesBurned = '', duration = '', speed = '', dateTime = '', id = ''} = props.walkingData || {};

    const handleDelete = (event) => {
        event.preventDefault(); 

        const deleteWalkAPI = `http://localhost:8080/walking/delete/${id}`

        fetch(deleteWalkAPI, {
            method: 'DELETE',
        })
        .then((response) => {
            if (response.ok) {
                props.setNewItem(response);
            } else {
                console.log(response)
            }
        });
    };

    return (
        <div className="WalkingContainer">
            <h1> {walkType} </h1>
            <h2>Steps: {steps} </h2>
            <h2> Distance: {distance}km </h2>
            <h2>Calrories Burned: {caloriesBurned}</h2>
            <h2>Duration: {duration}mins</h2>
            <h2>Speed: {speed}km/h</h2>
            <h2>Date Time: {dateTime}</h2>
            <button onClick={handleDelete}>Delete</button> 
        </div>
    );
}

export default WalkingHistory;
import "./CounterContainer.css";

const CounterContainer = (props) => {
    return (
      <div className="counter-container">
        <div className="target-tracker">
          <h3>Current: {props.count}</h3>
          {/* <h3>Target: {goal}</h3> */}
          <h3>Total: {props.savedCount}</h3>
        </div>
      </div>
    );
  };
  
  export default CounterContainer;

 
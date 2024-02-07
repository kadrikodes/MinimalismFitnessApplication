import "./CounterContainer.css";

const CounterContainer = ({ goal, count, savedCount }) => {
    return (
      <div className="counter-container">
        <div className="target-tracker">
          <h3>Current: {count}</h3>
          <h3>Target: {goal}</h3>
          <h3>Total: {savedCount}</h3>
        </div>
      </div>
    );
  };
  
  export default CounterContainer;

 
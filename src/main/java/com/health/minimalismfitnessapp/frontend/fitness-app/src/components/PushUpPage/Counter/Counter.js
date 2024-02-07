import React, { useState } from "react";
import "./Counter.css";

const Counter = () => {
    const [count, setCount] = useState(0);
    const [totalCount, setTotalCount] = useState(0);

    const handleIncrement = () => setCount(count + 1);
    const handleDecrement = () => setCount(count - 1);

    const handleSubmitTotal = () => {
        setTotalCount(totalCount + count);
        setCount(0); // Reset count after submitting
    };

    return (
        <div className="counter">
            {/* <div className="count-box">
                <p>Total: {totalCount}</p>
            </div> */}
            <div className="button-box">
                <button onClick={handleIncrement} className="btn-increase">+</button>
                <button onClick={handleDecrement} className="btn-decrease">-</button>
            </div>
            <div className="submit-box" onClick={handleSubmitTotal}>
                <p>Current: {count}</p>
            </div>
        </div>
    );
}

export default Counter;

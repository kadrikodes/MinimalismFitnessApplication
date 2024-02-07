import "./InputContainer.css";


const InputContainer = ({ goal, setGoal }) => {
  const handleChange = (e) => {
    const newGoal = parseInt(e.target.value);
    setGoal(newGoal);
  };

  return (
    <div className="input-container">
      <label htmlFor="goalInput">Enter Goal:</label>
      <input
        id="goalInput"
        type="number"
        value={goal}
        onChange={handleChange}
        placeholder="Enter your goal"
      />
    </div>
  );
};

export default InputContainer;

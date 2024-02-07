import "./InputContainer.css";

const InputContainer = () => {
    const handleChange = (e) => {
        const newGoal = parseInt(e.target.value);
        setGoal(newGoal);
      };
    
      
      return (
        <input
          type="number"
          value={goal}
          onChange={handleChange}
        />
    );
};

export default InputContainer;
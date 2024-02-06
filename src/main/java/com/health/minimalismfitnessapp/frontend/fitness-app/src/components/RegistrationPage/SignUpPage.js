import './SignUpPage.css';

const SignupPage = () => {
  return (
    <div className="signup-wrapper">
      <form>
        <h1>Create an Account</h1>

        <div className="input-box">
          <label htmlFor="name">Name</label>
          <input type="text" id="name" name="name" placeholder="Your Name" required />
        </div>

        <div className="input-box">
          <label htmlFor="height">Height(cm)</label>
          <input type="text" id="height" name="height" placeholder="Your Height" required />
        </div>

        <div className="input-box">
          <label htmlFor="weight">Weight(kg)</label>
          <input type="text" id="weight" name="weight" placeholder="Your Weight" required />
        </div>

        <div className="input-box">
          <label htmlFor="dob">Date of Birth</label>
          <input type="date" id="dob" name="dob" required />
        </div>

        <div className="input-box">
          <label htmlFor="gender">Gender</label>
          <select id="gender" name="gender" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>

        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
}

export default SignupPage;

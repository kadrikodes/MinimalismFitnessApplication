import Header from "../HomePage/Header/Header";
import "./LoginPage.css";
import { Link } from "react-router-dom";

const LoginPage = () => {

    return (
        <div className="desktop">
            <div className="Header">
                <Header></Header>

            </div>
            <form action="" id="login-form" >
                <h1>Login</h1>
                <div className="input-box">
                    <input type="text" placeholder="Username" required />
                </div>
                <div className="input-box">
                    <input type="password" placeholder="Password" required />
                </div>
                <button type="submit">Login</button>
            </form>
            <p>
        Don't have an account? <Link to="/signup">Sign Up</Link>
      </p>
        </div>
    );
}

export default LoginPage;
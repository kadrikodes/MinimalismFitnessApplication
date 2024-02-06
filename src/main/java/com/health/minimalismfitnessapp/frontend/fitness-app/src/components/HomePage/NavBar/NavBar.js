import "./NavBar.css";
import { Link } from "react-router-dom";

const NavBar = () => {
    return (
    <div className="navbar">
        <Link to="/user" className="user">USER</Link>
        <Link to="/walk" className="walk">WALK</Link>
        <Link to="/pushup" className="pushup">PUSHUP</Link>
        <Link to="/nutrition" className="nutrition">NUTRITION</Link>
        <Link to="/sleep" className="sleep">SLEEP</Link>
    </div>
  );
}

export default NavBar;
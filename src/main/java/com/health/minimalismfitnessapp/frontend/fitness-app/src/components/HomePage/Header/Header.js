import React from 'react';
import "./Header.css";
import { Link } from 'react-router-dom'; 

const Header = () => {
    return (
        <div className="minimalism-fitness">
            <Link to="/" id='heading'>MINIMALISM FITNESS APP</Link> 
        </div>
    );
}

export default Header;

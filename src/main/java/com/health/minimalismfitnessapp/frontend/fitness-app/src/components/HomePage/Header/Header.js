import React from 'react';
import "./Header.css";
import { Link } from 'react-router-dom'; 

const Header = () => {
    return (
        <div className="minimalism-fitness">
            <Link to="/">MINIMALISM FITNESS APP</Link> 
        </div>
    );
}

export default Header;

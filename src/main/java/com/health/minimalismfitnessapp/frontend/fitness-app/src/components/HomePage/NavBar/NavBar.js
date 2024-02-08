import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";
import { CodeIcon, HamburgetMenuClose, HamburgetMenuOpen } from "./Icons";




function NavBar() {
  const [click, setClick] = useState(false);

  const handleNavClick = () => setClick(!click);
  return (
    <>
      <nav className="navbar">
        <div className="nav-container">
          <NavLink exact to="/" className="nav-logo">
            <span>Minimalism Fitness</span>
            <span className="icon">
              <CodeIcon />
            </span>
          </NavLink>

          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <NavLink
                
                to="/"
                activeClassName="active"
                className="nav-links"
                onClick={handleNavClick}
              >
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink           
                to="/nutrition"
                activeClassName="active"
                className="nav-links"
                onClick={handleNavClick}
              >
                Nutrition
              </NavLink>
            </li>

            <li className="nav-item">
              <NavLink
                
                to="/walk"
                activeClassName="active"
                className="nav-links"
                onClick={handleNavClick}
              >
                Walk
              </NavLink>
            </li>

            
            <li className="nav-item">
              <NavLink
                
                to="/sleep"
                activeClassName="active"
                className="nav-links"
                onClick={handleNavClick}
              >
                Sleep
              </NavLink>
            </li>
            
            
            <li className="nav-item">
              <NavLink
    
                to="/pushup"
                activeClassName="active"
                className="nav-links"
                onClick={handleNavClick}
              >
                PushUp
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                
                to="/user"
                activeClassName="active"
                className="nav-links"
                onClick={handleNavClick}
              >
                Profile
              </NavLink>
            </li>
          </ul>
          <div className="nav-icon" onClick={handleNavClick}>
            {click ? (
              <span className="icon">
                <HamburgetMenuOpen />{" "}
              </span>
            ) : (
              <span className="icon">
                <HamburgetMenuClose />
              </span>
            )}
          </div>
        </div>
      </nav>
    </>
  );
}

export default NavBar;

import React from 'react';
import './AboutPage.css'; 

const AboutPage = () => {
  const people = [
    {
      name: "Sam",
      role: "CEO & Founder",
      description: "Best Java Coder",
      email: "Please-do-not-contact-me@gmail.com",
      image: "sam.png"
    },
    {
      name: "Kadri",
      role: "CEO & Founder of Fitness Football",
      description: "Have my own million dollar business - @FitnessFootball",
      email: "I_love_Fitness_Football@example.com",
      image: "kadri.png"
    },
    {
      name: "Esra",
      role: "Creative Designer",
      description: "Always laughing at Sams jokes",
      email: "Jira-4-EVA@example.com",
      image: "esra2.jpg"
    },
    {
      name: "Divin",
      role: "Developer",
      description: "I have the best social life",
      email: "Party-All-Night01@example.com",
      image: "divin.png"
    },
    {
      name: "Rais",
      role: "Procrastinator",
      description: "I have no social life",
      email: "No_Life22@example.com",
      image: "rais.png"
    }
  ];

  return (
    <div className="desktop"> 
      <div className="about-page"> 
        {people.map((person, index) => (
          <div className="person-tile" key={index}>
            <img src={person.image} alt={person.name} className="profile-image" />
            <div className="info">
              <h2>{person.name}</h2>
              <p className="role">{person.role}</p>
              <p className="description">{person.description}</p>
              <p className="email">{person.email}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AboutPage;


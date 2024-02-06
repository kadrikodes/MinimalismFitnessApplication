import logo from './logo.svg';
import './App.css';
import HomePage from './components/HomePage/HomePage';

import { BrowserRouter as Router, Routes, Route, Switch } from 'react-router-dom';
import NavBar from './components/HomePage/NavBar/NavBar';
import WalkPage from './components/WalkPage/WalkPage';
import PushUpPage from './components/PushUpPage/PushUpPage';
import NutritionPage from './components/NutritionPage/NutritionPage';
import SleepPage from './components/SleepPage/SleepPage';
import UserPage from './components/UserPage/UserPage';
import LoginPage from './components/RegistrationPage/LoginPage';
import SignupPage from './components/RegistrationPage/SignUpPage';

function App() {
  return (
    <>
    <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/user' element={<UserPage />} />
            <Route path="/walk" element={<WalkPage />} />
            <Route path="/pushup" element={<PushUpPage />} />
            <Route path="/nutrition" element={<NutritionPage />} />
            <Route path="/sleep" element={<SleepPage />} />
        <Route path='/login' element={<LoginPage />} />
        <Route path='/signup' element={<SignupPage />} />
    </Routes>
    </>
  );
}

export default App;

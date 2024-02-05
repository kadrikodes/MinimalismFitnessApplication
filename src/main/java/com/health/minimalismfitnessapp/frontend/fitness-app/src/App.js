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
    </Routes>
    </>
  );
}

export default App;

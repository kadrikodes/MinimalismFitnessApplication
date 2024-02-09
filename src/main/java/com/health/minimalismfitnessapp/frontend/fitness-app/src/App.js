import "./App.css";
import NavBar from './components/HomePage/NavBar/NavBar';
import { BrowserRouter as Router,  Route, Routes } from "react-router-dom";
import HomePage from './components/HomePage/HomePage';
import UserPage from './components/UserPage/UserPage';
import SleepPage from './components/SleepPage/SleepPage';
import WalkPage from './components/WalkPage/WalkPage';
import NutritionPage from './components/NutritionPage/NutritionPage';
import PushUpPage from './components/PushUpPage/PushUpPage';
// import { AboutPage } from "./components/AboutPage/AboutPage";
import AboutPage from "./components/AboutPage/AboutPage";

function App() {
  return (
    <>
        <NavBar />
        <div className="pages">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/about" element={<AboutPage />} />
            <Route path="/user" element={<UserPage />} />
            <Route path="/sleep" element={<SleepPage />} />
            <Route path="/walk" element={<WalkPage />} />
            <Route path="/nutrition" element={<NutritionPage />} />
            <Route path="/pushup" element={<PushUpPage />} />
          </Routes>
        </div>
     </>
  );
}

export default App;

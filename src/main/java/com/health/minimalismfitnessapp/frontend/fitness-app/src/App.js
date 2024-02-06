import logo from './logo.svg';
import './App.css';
import HomePage from './components/HomePage/HomePage';
import { Routes, Route} from 'react-router-dom';
import LoginPage from './components/RegistrationPage/LoginPage';
import SignupPage from './components/RegistrationPage/SignUpPage';

function App() {
  return (
    <Routes>
        <Route path='/' element={<HomePage />}/>
        <Route path='/login' element={<LoginPage />} />
        <Route path='/signup' element={<SignupPage />} />
    </Routes>

  );
}

export default App;

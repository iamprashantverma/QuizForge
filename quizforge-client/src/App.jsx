import './App.css'
import NavBar from '../src/component/common/NavBar';
import Home from './pages/Home'
import { Toaster } from "react-hot-toast";
import { Route, Routes } from 'react-router-dom';
import SignUp from './pages/SignUp';
import Login from './pages/Login';
function App() {
  return (
    <>
      <NavBar/>
      <Routes>
        <Route path='/' element = {<Home/>}/>
        <Route path='/signup' element={<SignUp/>} />
        <Route path='/login' element={<Login/>} />
      </Routes>
      
    </>
    
  )
}
export default App

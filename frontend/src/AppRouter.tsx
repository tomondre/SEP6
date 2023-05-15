import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './Pages/home-page';
import SignUp from './Pages/sign-up';


const AppRouter: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/"  element={<HomePage/>} />
        <Route path="/sign-up"  element={<SignUp/>} />
      </Routes>
    </Router>
  );
};

export default AppRouter;

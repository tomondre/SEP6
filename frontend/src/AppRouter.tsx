import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Layout from './Components/Layout';
import HomePage from './Pages/home-page';
import SignUp from './Pages/sign-up';
import Login from './Pages/login';
import { ThemeProvider } from "@mui/material/styles";
import theme from "./Theme/theme";
import PeoplePage from './Pages/people';




const AppRouter: React.FC = () => {
  return (

    <ThemeProvider theme={theme}>
      <Layout>
        <Router>
          <Routes>
            <Route path="/"  element={<HomePage/>} />
            <Route path="/sign-up"  element={<SignUp/>} />
            <Route path="/login"  element={<Login/>} />
            <Route path="/actor"  element={<PeoplePage/>} />
          </Routes>
        </Router>
      </Layout>
    </ThemeProvider>
  );
};

export default AppRouter;

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Layout from './components/Layout';
import HomePage from './pages/home-page';
import SignUp from './pages/sign-up';
import Login from './pages/login';
import { ThemeProvider } from "@mui/material/styles";
import theme from "./theme/theme";
import PeoplePage from './pages/people';
import ProfilePage from './pages/profile';



const AppRouter: React.FC = () => {
  return (

    <ThemeProvider theme={theme}>
      <Layout>
        <Router>
          <Routes>
            <Route path="/"  element={<HomePage/>} />
            <Route path="/sign-up"  element={<SignUp/>} />
            <Route path="/login"  element={<Login/>} />
            <Route path="/profile"  element={<ProfilePage/>} />
            <Route path="/person"  element={<PeoplePage/>} />
          </Routes>
        </Router>
      </Layout>
    </ThemeProvider>
  );
};

export default AppRouter;

import React, { ReactNode } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Navbar from './Navbar';
import { makeStyles } from "tss-react/mui";
import background from '../images/Background-Photo.webp'
import { Colors } from '../Constants/Colors';


type LayoutProps = {
    children: ReactNode;
  };

  const Layout = ({ children }: LayoutProps) => {
    const { classes } = useStyles();

    return (
        <div className={classes.mainLayout}>

         <Navbar/>
  
          <main className={classes.mainContainer}>
            {children}
          </main>
  
        </div>
    );
  };

const useStyles = makeStyles()(() => ({
    mainLayout:{
        backgroundImage: `url(${background}) `,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        height: '100vh',
    },
    mainContainer:{
      padding: '3rem'
    }
}));

  export default Layout;

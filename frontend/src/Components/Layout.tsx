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
  
          <main >
            {children}
          </main>
  
        </div>
    );
  };

const useStyles = makeStyles()(() => ({
    mainLayout:{
        backgroundImage: `url(${background})`,
        backgroundRepeat: "no-repeat",
        backgroundSize: 'cover',
        backgroundPosition: 'center',

        backgroundColor: Colors.black50,
        height: '100vh',
        // width: '100%'
    }
}));

  export default Layout;

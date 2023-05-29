import React, { ReactNode } from 'react';
import Navbar from './Navbar';
import { makeStyles } from "tss-react/mui";
import background from '../images/Background-Photo.webp'
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

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

          <ToastContainer
            position="bottom-right"
            autoClose={3000}
            hideProgressBar={false}
            newestOnTop={true}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="light"
          />

  
        </div>
    );
  };

const useStyles = makeStyles()(() => ({
    mainLayout:{
        backgroundImage: `url(${background}) `,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'repeat-y',
        minHeight: '100vh',
    },
    mainContainer:{
      padding: '3rem'
    }
}));

  export default Layout;

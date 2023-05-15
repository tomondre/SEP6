import React, { ReactNode } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Navbar from './Navbar';

type LayoutProps = {
    children: ReactNode;
  };

  const Layout = ({ children }: LayoutProps) => {
    return (
        <>
          {/* Place your header, navigation, or any other common components here */}
         <Navbar/>
  
          {/* Place the content of the wrapped page here */}
          <main>
            {children}
          </main>
  
          {/* Add your footer component or other common components here */}
          <footer>
            <h2>there</h2>
          </footer>
        </>
    );
  };

  export default Layout;

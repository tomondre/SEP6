
import './App.css';
import React from 'react';
import Navbar from './components/Navbar'
import AppRouter from './AppRouter';

function App() {
  const logo = require('./logo.svg');

  return (
    <div className="App">
      <AppRouter />
    </div>
  );
}

export default App;

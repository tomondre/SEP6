import React from 'react';
import './index.css';
import ReactDOM from 'react-dom';
import App from './App';
import { createRoot } from 'react-dom/client';

const domNode = document.getElementById('root') as Element;
const root = createRoot(domNode);

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);


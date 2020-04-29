import "core-js/stable";
import "regenerator-runtime/runtime";
import "es6-promise";
import "isomorphic-fetch";
import React from 'react';
import ReactDOM from 'react-dom';
import { HashRouter } from 'react-router-dom';

import App from './App';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './css/custom.css';


const app = (
  <HashRouter>
    <App />
  </HashRouter>
);


ReactDOM.render(app, document.getElementById("app"));
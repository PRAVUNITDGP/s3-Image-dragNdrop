import React,{useState,useEffect} from 'react';
import './App.css';
import axios from 'axios';
import UserProfiles from './user/UserProfiles';
function App() {
  return (
    <div className="App">
      <UserProfiles/>
     <h1>React App</h1>
    </div>
  );
}

export default App;

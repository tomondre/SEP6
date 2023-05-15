import React from "react";

const Navbar = () =>{
    const logo = require("../images/Logo.png");

    return (
        <div>
            <img src={logo}/>
            <h1>My navabar
            </h1>
        </div>
    )
}

export default Navbar
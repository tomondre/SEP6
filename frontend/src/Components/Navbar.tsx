import React, { ChangeEvent, FunctionComponent } from "react";
import { Drawer, Grid, Typography, useMediaQuery, Link, TextField, IconButton } from "@mui/material";
import { makeStyles } from "tss-react/mui";
import { Colors } from "../Constants/Colors";
import SearchBar from "./SearchBar";

const Navbar: FunctionComponent = () =>{
    const logo = require("../images/Logo.png");
    const { classes } = useStyles();

    const handleSearch = (event: ChangeEvent<HTMLInputElement>) => {
        console.log(event.target.value);
      };

    return (
        <Grid container className={classes.navbar}>

            <Link className={classes.link} href='/'>
                <Grid item container>
                    <Grid item>
                        <img className={classes.logo} src={logo}/>
                    </Grid>
                    <Grid item className={classes.textContainer}>
                        <Typography variant="h4" className={classes.title}>Cinemate</Typography>
                    </Grid>
                </Grid>
            </Link>

            <SearchBar/>

        </Grid>
    )
}

const useStyles = makeStyles()(() => ({
   logo: {
    width: '4rem',
    height: '4rem'
   },
   title: {
    background: 'linear-gradient(270deg, #CF0A0A 0%, #F97676 100%)',
    backgroundClip: 'text',
    color: 'transparent',
  },
  link:{
    textDecoration: 'none'
  },
  navbar:{
    backgroundColor: 'rgba(0, 0, 0, 0.75)',
    padding: '1rem 4rem',
    alignItems: 'center',
  },
  textContainer:{
    display:'grid',
    alignItems: 'center',
    padding: '0 1rem'
  },
  }));

export default Navbar


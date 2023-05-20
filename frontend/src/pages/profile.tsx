import { Button, FormControl, Grid, InputLabel, MenuItem, Pagination, Select, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../components/Carousel';
import MovieCard from '../components/MovieCard';
import { Colors } from '../constants/Colors';
import { SelectChangeEvent } from '@mui/material';
import MovieService from "../services/movies";
import GenreFilter from '../components/GenreFilter';
import { Movie } from "../types";

const ProfilePage = () => {
  const { classes } = useStyles();


  useEffect(() => {

  }, []);


  return (
    <Grid container>
        <Grid item lg={4} className={classes.gridContainer}>
            <div className={classes.profileContainer}>

            </div>
        </Grid>
        <Grid item lg={8}>
            <div className={classes.profileContainer}>
                <div className={classes.personalInformation}>
                    <Typography variant="h3">Personal Information</Typography>

                    <div className={classes.textContainer}>
                        <Typography variant="h6">Email: </Typography>
                        <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                    </div>
                    <div className={classes.textContainer}>
                        <Typography variant="h6">Email: </Typography>
                        <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                    </div>
                </div>
            </div>


        </Grid>

        <MovieCard poster={"/r8LPeldxskHrGJTPfhICguCip2H.jpg"} title={"Rambo"} id={7555} />
    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
    profileContainer:{
        backgroundColor:Colors.black50,
        height: '25rem',
        border: '1rem',
        padding: '2.5rem',
    },
    gridContainer:{
        paddingRight: '3rem'
    },
    icon:{
        width: '3rem',
        height: '3rem',

    },
    personalInformation:{
        display: 'grid',
        justifyContent: 'start',
    },
    textContainer:{
        display: 'flex',
        alignItems: 'center',
        margin: '2rem 0'
    },
    marginLeft:{
        marginLeft: '0.5rem'
    }
}));


export default ProfilePage;

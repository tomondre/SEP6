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
import EditIcon from '@mui/icons-material/Edit';

const ProfilePage = () => {
  const { classes } = useStyles();
  const [editMode, setEditMode] = useState(false);


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
                    <div className={classes.title}>
                        <Typography variant="h3">Personal Information</Typography>
                        <EditIcon
                        onClick={() => setEditMode(!editMode)}
                        className={classes.icon} />
                    </div>
                    <div className={classes.infoContainer}>
                        <div className={classes.textContainer}>
                            <Typography variant="h6">Email: </Typography>
                            <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                        </div>
                        <div className={classes.textContainer}>
                            <Typography variant="h6">Username: </Typography>
                            <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                        </div>
                        <div className={classes.textContainer}>
                            <Typography variant="h6">Country: </Typography>
                            <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                        </div>
                        <div className={classes.textContainer}>
                            <Typography variant="h6">Birthday: </Typography>
                            <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                        </div>
                        <div className={classes.textContainer}>
                            <Typography variant="h6">Gender: </Typography>
                            <Typography variant="p" className={classes.marginLeft}>myemail.com</Typography>
                        </div>
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
        // height: '100%',
        border: '1rem',
        padding: '2.5rem',
    },
    gridContainer:{
        paddingRight: '3rem'
    },
    icon:{
        width: '3rem',
        height: '3rem',
        color: Colors.lightCyan,
        marginLeft: '1rem',
        cursor: 'pointer'

    },
    personalInformation:{
        display: 'grid',
        justifyContent: 'start',
    },
    textContainer:{
        display: 'flex',
        alignItems: 'center',
        marginTop: '1rem'
    },
    marginLeft:{
        marginLeft: '0.5rem'
    },
    infoContainer:{
        marginTop: '1rem'
    },
    title:{
        display: 'flex',
        alignItems: 'center',
    }
}));


export default ProfilePage;

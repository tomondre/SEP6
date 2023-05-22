import { Button, FormControl, Grid, InputLabel, MenuItem, Pagination, Select, Typography } from '@mui/material';
import React, { useEffect, useMemo, useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../components/Carousel';
import MovieCard from '../components/MovieCard';
import { Colors } from '../constants/Colors';
import { SelectChangeEvent } from '@mui/material';
import MovieService from "../services/movies";
import GenreFilter from '../components/GenreFilter';
import EditIcon from '@mui/icons-material/Edit';
import jwt_decode from "jwt-decode";
import profileServie from '../services/profile';
import { profile } from 'console';
import Reviews from '../components/Reviews';

interface Profile {
    country: string;
    dateOfBirth: string;
    email: string;
    gender: string;
    id: number;
    name: string;
    password: string;
    profilePictureUrl: string;
    username: string;
  }

  interface ProfileInfoProps {
    label: string;
    value: string;
  }

const ProfilePage = () => {
  const { classes } = useStyles();
  const [editMode, setEditMode] = useState(false);
  const [profile, setProfile] = useState<Profile | null>(null);

  const fieldsAndValues = useMemo(() => {
    if(!profile)
      return [];

    return [
        {
        field: "Email",
        value: profile.email,
        },
        {
        field: "Username",
        value: profile.username,
        },
        {
        field: "Country",
        value: profile.country,
        },
        {
        field: "Birthday",
        value: profile.dateOfBirth,
        },
        {
        field: "Gender",
        value: profile.gender,
        }
    ]
  }, [profile])
  
  const reviews = [
    {
      id: 1,
      user: "John Doe",
      date: "12/12/2012",
      rating: 5,
      comment: "lorem ipsum dolor sit amet",
    },
    {
      id: 2,
      user: "John Doe 2",
      date: "12/12/2012",
      rating: 9,
      comment: "lorem ipsum dolor sit amet",
    },
    {
      id: 3,
      user: "John Doe 3",
      date: "12/12/2012",
      rating: 10,
      comment: "lorem ipsum dolor sit amet",
    },
    {
      id: 4,
      user: "John Doe 4",
      date: "12/12/2012",
      rating: 5,
      comment: "lorem ipsum dolor sit amet",
    },
    {
      id: 5,
      user: "John Doe 5",
      date: "12/12/2012",
      rating: 2,
      comment: "lorem ipsum dolor sit amet",
    },
  ];

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await profileServie.getProfile();
        setProfile(response);
      } catch (error) {
        console.error("Error fetching profile:", error);
      }
    };

    fetchProfile();

  }, []);

  const ProfileInfo = ({ label, value }: ProfileInfoProps) => {
    return (
      <div className={classes.textContainer}>
        <Typography variant="h6">{label}: </Typography>
        <Typography variant="p" className={classes.marginLeft}>
          {value}
        </Typography>
      </div>
    );
  };


  if(!profile)
    return null;

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
                    {fieldsAndValues.map((fieldAndValue) => (
                        <ProfileInfo label={fieldAndValue.field} value={fieldAndValue.value} />
                        ))}
                        </div>
                </div>
            </div>


        </Grid>
        <Reviews reviews={reviews} />

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

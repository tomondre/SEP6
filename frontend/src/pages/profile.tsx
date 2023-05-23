import { Button, FormControl, Grid, InputLabel, MenuItem, OutlinedInput, Pagination, Select, TextField, Typography } from '@mui/material';
import React, { useEffect, useMemo, useRef, useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../components/Carousel';
import MovieCard from '../components/MovieCard';
import { Colors } from '../constants/Colors';
import { SelectChangeEvent } from '@mui/material';
import MovieService from "../services/movies";
import GenreFilter from '../components/GenreFilter';
import EditIcon from '@mui/icons-material/Edit';
import jwt_decode from "jwt-decode";
import profileServie from '../services/account-service';
import { profile } from 'console';
import Reviews from '../components/Reviews';
import { useForm } from 'react-hook-form';

interface Profile {
    country: string;
    dateOfBirth: string;
    email: string;
    gender: string;
    id: number;
    name: string;
    profilePictureUrl: string;
    username: string;
  }

  interface ProfileInfoProps {
    label: string;
    value: string;
    editable: boolean;
    onValueChange: (value: string) => void;
  }

const ProfilePage = () => {
  const { classes } = useStyles();
  const [editMode, setEditMode] = useState(false);
  const [profile, setProfile] = useState<Profile | null>(null);
  const { handleSubmit, register, setValue } = useForm<Profile>();

  const fieldsAndValues = useMemo(() => {
    if(!profile)
      return [];

    return [
        {
        field: "Email",
        value: profile.email,
        for: "email"
        },
        {
        field: "Username",
        value: profile.username,
        for: "username"
        },
        {
        field: "Country",
        value: profile.country,
        for: "country"
        },
        {
        field: "Birthday",
        value: profile.dateOfBirth,
        for: "dateOfBirth"
        },
        {
        field: "Gender",
        value: profile.gender,
        for: "gender"
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

  const ProfileInfo = ({ label, value, editable, onValueChange }: ProfileInfoProps) => {

      const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        onValueChange(event.target.value);
      };

    return (
        <div className={classes.textContainer}>
        <Typography variant="h6">{label}: </Typography>
        {editable ? (
          <OutlinedInput
            value={value}
            // inputRef={inputRef}
            // placeholder={value}
            // {...register(profileAttribute)}
            onChange={handleChange}
            className={classes.marginLeft + " " + classes.input}
          />
        ) : (
          <Typography variant="p" className={classes.marginLeft}>
            {value}
          </Typography>
        )}
      </div>
    );
  };
  const handleValueChange = (field: keyof Profile, value: string) => {
    console.log(`Updating ${field} to:`, value);
    setProfile((prevProfile) => {
      if (prevProfile) {
        return {
          ...prevProfile,
          [field]: value,
        };
    }
      return null;
    });
  };
  
  const handleEditIconClick = () => {
    if (editMode) {
        handleSubmit(handleSubmitForm)();
      }

    setEditMode((prevEditMode) => !prevEditMode);

  };

  const handleSubmitForm = (data: Profile) => {
    console.log("Form submitted:", data);
    setProfile(data);
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
                    {fieldsAndValues.map((fieldAndValue, index) => (
                            <ProfileInfo
                            key={index}
                            label={fieldAndValue.field}
                            value={fieldAndValue.value || ""}
                            editable={editMode}
                            // profileAttribute = {fieldAndValue.for as keyof Profile}
                            onValueChange={(value) => handleValueChange(fieldAndValue.for as keyof Profile, value)}
                        />
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
    },
    input:{
        width: '38.5rem',
        height: '2.5rem',
        background: Colors.lightCyan,
        border: '0.063rem',
        borderRadius: '0.625rem',
        color: Colors.black,
    }
}));


export default ProfilePage;

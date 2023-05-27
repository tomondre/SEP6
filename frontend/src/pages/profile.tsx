import { Grid, OutlinedInput, Typography } from '@mui/material';
import React, { useEffect, useMemo, useRef, useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import MovieCard from '../components/MovieCard';
import { Colors } from '../constants/Colors';
import EditIcon from '@mui/icons-material/Edit';
import DoneIcon from '@mui/icons-material/Done';
import profileService from '../services/account-service';
import Reviews from '../components/Reviews';
import { useForm } from 'react-hook-form';
import { IMovie, IReview} from '../utils/types';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import FavoriteButton from '../components/FavoriteButton';
import accountService from '../services/account-service';
import { getUserId } from '../services/user-service';

interface IProfile {
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
    profileAttribute: keyof IProfile;
  }

const ProfilePage = () => {
  const { classes } = useStyles();
  const [editMode, setEditMode] = useState(false);
  const [profile, setProfile] = useState<IProfile | null>(null);
  const [movies, setMovies] = useState<IMovie[]>([]);
  const [userReviews, setUserReviews] = useState<IReview[]>([]);

  const { handleSubmit, register, setValue } = useForm<IProfile>();


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

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await profileService.getProfile();
        setProfile(response);
  
        if(response){
          setValue('email', response.email);
          setValue('username', response.username);
          setValue('country', response.country);
          setValue('dateOfBirth', response.dateOfBirth);
          setValue('gender', response.gender);
        }
  
      } catch (error) {
        console.error("Error fetching profile:", error);
      }
  
      try {
        const response = await profileService.getFavoriteMovies();
        setMovies(response);
      }
      catch(error){
        console.error("Error fetching favorite movies:", error);
      } 
    };

    const fetchUserReviews = () => {
    accountService.getUserReviews().then(res => {
      setUserReviews(res);
    })
    }

    fetchProfile();
    fetchUserReviews();

  }, []);

  const ProfileInfo = ({ label, value, editable, profileAttribute }: ProfileInfoProps) => {

    return (
        <div className={classes.textContainer}>
        <Typography variant="h6">{label}: </Typography>
        {editable ? (
          <OutlinedInput
            {...register(profileAttribute)}
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
  
  const handleEditIconClick = () => {
    if (editMode) {
        handleSubmit(handleSubmitForm)();
      }

    setEditMode((prevEditMode) => !prevEditMode);

  };

  const handleSubmitForm = (data: IProfile) => {
    setProfile(data);

    //request to update profile

  };

  const removeMovie = (movieId: number) => {
    setMovies((currentMovies) => currentMovies.filter((movie) => movie.id !== movieId));
  };

  if(!profile)
  {
    if(getUserId() === null){
      window.location.href="/login";
    }
    return null;
  }

  return (
    <Grid container>

        <Grid item lg={4} className={classes.gridContainer}>
            <div className={classes.profileContainer}>
              <AccountCircleIcon className={classes.profilePic}/>
              <Typography variant="h5">{profile.name}</Typography>
            </div>
        </Grid>

        <Grid item lg={8} className={classes.profileContainer}>
          <div className={classes.alignStart}>
              <div className={classes.title}>
                  <Typography variant="h5">Personal Information</Typography>
                  <div onClick={handleEditIconClick}>
                      {editMode ? (
                          <DoneIcon className={classes.icon} />
                      ) : (
                          <EditIcon className={classes.icon} />
                      )}
                  </div>
              </div>
              <div className={classes.infoContainer}>
              {fieldsAndValues.map((fieldAndValue, index) => (
                      <ProfileInfo
                      key={index}
                      label={fieldAndValue.field}
                      value={fieldAndValue.value || ""}
                      editable={fieldAndValue.for === "username" ? false : editMode}
                      profileAttribute = {fieldAndValue.for as keyof IProfile}
                  />
                  ))}
                  </div>
          </div>
        </Grid>
          <Reviews reviews={userReviews} />

          <Grid item container>
            <Grid item lg={12} className={classes.alignStart}>
              <Typography variant="h5">Favorite Movies</Typography>
            </Grid>
                {movies.map((movie) => (
                  <Grid item lg={3} key={movie.id} className={classes.movieCardContainer}>
                      <MovieCard poster={movie.posterUrl} title={movie.title} id={movie.id} />
                      <div className={classes.favoriteButtonContainer}>
                        <FavoriteButton movieId={movie.id} isFave={true} removeMovie={removeMovie}/>
                      </div>
                  </Grid>
                ))}
          </Grid>

    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
    profileContainer:{
        backgroundColor:Colors.black50,
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
    alignStart:{
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
    },
    favoriteIcon: {
      position: "absolute",
      top: 10,
      right: 10,
      color: Colors.red1,
    },
    movieCardContainer: {
      position: 'relative',
      display: 'inline-block',
    },
    favoriteButtonContainer: {
      position: 'absolute',
      top: 10,
      right: 10,
    },
    profilePic:{
      width: '10rem',
      height: '10rem',
      color: Colors.lightCyan,
    }
}));


export default ProfilePage;

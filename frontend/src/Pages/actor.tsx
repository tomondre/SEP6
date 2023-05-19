import React from "react";
import { useEffect, useState } from "react";
import { makeStyles } from "tss-react/mui";
import MovieCard from "../Components/MovieCard";
import { Grid, Typography, Link } from "@mui/material";
import { useNavigate } from "react-router-dom";
import actorService from "../Services/actor-service";
import StarIcon from "@mui/icons-material/Star";
import { Colors } from "../Constants/Colors";


interface Movie {
  id:number;
  title:string;
  posterUrl:string;
}

interface Actor {
  id: number;
  name: string;
  type: string;
  dateOfBirth: string;
  placeOfBirth: string;
  gender: string;
  biography: string;
  deathDate: string;
  profileImg: string;
  movies:Movie[]
}

const ActorPage = () => {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const [actor, setActor] = useState<Actor>();


  const baseUrl = 'https://image.tmdb.org/t/p/original'


  useEffect(() => {
    const fetchActor = async () => {
      try {
        const actor= await actorService.getSpecificActor(976)
        setActor(actor);
      } catch (error) {
        console.error("Error fetching movies:", error);
      }
    };

    fetchActor();
  }, []);



  if(!actor)
  {
    return <div>Loading...</div>
  }

  return (
    <Grid container>
      <Grid className={classes.container}>
        <Grid>
          <img src={`${baseUrl}${actor.profileImg}`} className={classes.image} alt="actor" />
        </Grid>

        <Grid className={classes.actorDetails}>
          <Grid className={classes.ratingGroup}>
            <StarIcon className={classes.star} />
            <Grid className={classes.rating}>Rating</Grid>
            <Grid className={classes.ratingGoal}>/ 10</Grid>
          </Grid>

          <Grid className={classes.actorName}>
            <Typography variant="h2">{actor.name}</Typography>
          </Grid>

          <Grid className={classes.specifications}>
            <Grid className={classes.type}>
              <Typography variant="h6">{actor.type}</Typography>
            </Grid>
            <Grid className={classes.dateOfBirth}>
              <Typography variant="h6">Born: {actor.dateOfBirth.substring(0,10)}</Typography>
            </Grid>
            { actor.deathDate &&
              <Grid className={classes.deathDate}>
              <Typography variant="h6">Died: {actor.deathDate.substring(0,10)}</Typography>
            </Grid>}
            <Grid className={classes.birthPlace}>
              <Typography variant="h6">Place of birth: {actor.placeOfBirth}</Typography>
            </Grid>
            <Grid className={classes.gender}>
              <Typography variant="h6">Gender: {actor.gender}</Typography>
            </Grid>
          </Grid>

          <Grid className={classes.biography}>
            <Typography variant="p">{actor.biography}</Typography>
          </Grid>
        </Grid>
      </Grid>
      <Grid className={classes.knownForLabel}>
      <Typography variant="h4">Known for:</Typography>
      </Grid>
      <Grid item container className={classes.moviesContainer}>
        {actor.movies.map((movie, index) => (
          <Grid item lg={2} key={index}>
            <Link href="/">
            <MovieCard poster={`${baseUrl}${movie.posterUrl}`} title={movie.title} />
            </Link>
          </Grid>
        ))}
      </Grid>
    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
  container: {
    display: "flex",
    justifyContent: "space-around",
  },
  image: {
    height: "46rem",
  },
  ratingGroup: {
    display: "flex",
    alignItems: "center",
    height: "10%",
  },
  star: {
    color: Colors.yellow,
    fontSize: "2rem",
  },
  rating: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  ratingGoal: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
  },
  actorName: {
    width: "100%",
    height: "5rem",
    textAlign: "start",
    marginTop: "3rem",
  },
  actorDetails: {
    width: "70%",
    padding: "2rem",
  },
  specifications: {
    display: "flex",
    alignItems: "start",
    height: "10%",
  },
  type: {
    color: Colors.yellow,
    fontSize: "2rem",
  },
  dateOfBirth: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  deathDate: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  birthPlace: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  gender: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  biography:{
    textAlign:'justify',
    fontWeight:'300',
  },
  knownForLabel:{
    margin:'4rem 4rem 4rem 0rem'
  },
  moviesContainer:{
    border:'0.05rem solid white'
  }

}));

export default ActorPage;

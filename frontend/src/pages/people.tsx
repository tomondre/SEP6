import React from "react";
import { useEffect, useState } from "react";
import { makeStyles } from "tss-react/mui";
import MovieCard from "../components/MovieCard";
import { Grid, Typography, Link } from "@mui/material";
import { useNavigate } from "react-router-dom";
import StarIcon from "@mui/icons-material/Star";
import { Colors } from "../constants/Colors";
import { useLocation } from "react-router-dom";
import { useIdFromUrl } from "../hooks/useIdFromUrl";
import personService from "../services/person-service";


interface Movie {
  id:number;
  title:string;
  posterUrl:string;
}


interface Person {
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

const PeoplePage = () => {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const [people, setPeople] = useState<Person>();
  const id = useIdFromUrl();


  const baseUrl = 'https://image.tmdb.org/t/p/original'


  useEffect(() => {
    const fetchPerson = async () => {
      try {
        if (id !== -1) {
          const people = await personService.getPersonById(id);
          setPeople(people);
        }
      } catch (error) {
        console.error("Error fetching movies:", error);
      }
    };
    fetchPerson();
  }, []);



  if(!people)
  {
    return <div>Loading...</div>
  }

  return (
    <Grid container>
      <Grid className={classes.container}>
        <Grid>
          <img src={`${baseUrl}${people.profileImg}`} className={classes.image} alt="people" />
        </Grid>

        <Grid className={classes.peopleDetails}>
          <Grid className={classes.ratingGroup}>
            <StarIcon className={classes.star} />
            <Grid className={classes.rating}>Rating</Grid>
            <Grid className={classes.ratingGoal}>/ 10</Grid>
          </Grid>

          <Grid className={classes.peopleName}>
            <Typography variant="h2">{people.name}</Typography>
          </Grid>

          <Grid className={classes.specifications}>
            <Grid className={classes.type}>
              <Typography variant="h6">{people.type}</Typography>
            </Grid>
            <Grid className={classes.dateOfBirth}>
              <Typography variant="h6">Born: {people.dateOfBirth.substring(0,10)}</Typography>
            </Grid>
            { people.deathDate &&
              <Grid className={classes.deathDate}>
              <Typography variant="h6">Died: {people.deathDate.substring(0,10)}</Typography>
            </Grid>}
            <Grid className={classes.birthPlace}>
              <Typography variant="h6">Place of birth: {people.placeOfBirth}</Typography>
            </Grid>
            <Grid className={classes.gender}>
              <Typography variant="h6">Gender: {people.gender}</Typography>
            </Grid>
          </Grid>

          <Grid className={classes.biography}>
            <Typography variant="p">{people.biography}</Typography>
          </Grid>
        </Grid>
      </Grid>
      <Grid className={classes.knownForLabel}>
      <Typography variant="h4">Known for:</Typography>
      </Grid>
      <Grid item container className={classes.moviesContainer}>
        {people.movies.map((movie, index) => (
          <Grid item lg={2} key={index}>
            <MovieCard id={movie.id} poster={`${baseUrl}${movie.posterUrl}`} title={movie.title} />
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
  peopleName: {
    width: "100%",
    height: "5rem",
    textAlign: "start",
    marginTop: "3rem",
  },
  peopleDetails: {
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
    justifyContent: 'space-around',
    border:'0.05rem solid white'
  }

}));

export default PeoplePage;

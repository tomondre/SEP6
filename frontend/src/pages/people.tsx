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
import { IPerson } from "../utils/types";
import personService from "../services/person-service";
import RatingStars from "../components/Rating";


interface Movie {
  id: number;
  title: string;
  posterUrl: string;
}


const PeoplePage = () => {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const [people, setPeople] = useState<IPerson>();
  const id = useIdFromUrl();
  const baseUrl = "https://image.tmdb.org/t/p/original";

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

  if (!people) {
    return <div>Loading...</div>;
  }

  return (
    <Grid container>
      <Grid className={classes.container}>
        <Grid>
          <img
            src={`${baseUrl}${people.profileImg}`}
            className={classes.image}
            alt="people"
          />
        </Grid>

        <Grid className={classes.peopleDetails}>
          <Grid>
             {!(typeof people.ratingAverage === undefined) && 
            <RatingStars rating={people.ratingAverage}/>
             }
          </Grid>

          <Grid className={classes.peopleName}>
            <Typography variant="h2">{people.name}</Typography>
          </Grid>

          <Grid className={classes.specifications}>
            <Grid>
              <Typography variant="p">{people.type}</Typography>
            </Grid>
            {people.dateOfBirth && (
              <Grid >
                <Typography variant="p">
                  Born: {people.dateOfBirth.substring(0, 10)}
                </Typography>
              </Grid>
            )}
            {people.deathDate && (
              <Grid >
                <Typography variant="p">
                  Died: {people.deathDate.substring(0, 10)}
                </Typography>
              </Grid>
            )}
            <Grid>
              <Typography variant="p">
                Place of birth: {people.placeOfBirth}
              </Typography>
            </Grid>
            <Grid >
              <Typography variant="p">Gender: {people.gender}</Typography>
            </Grid>
          </Grid>

          <Grid className={classes.biography}>
            <Typography variant="p">{people.biography}</Typography>
          </Grid>
        </Grid>
      </Grid>
      <Grid item container  lg={12} className={classes.moviesContainer}>
        <Grid item lg={12} className={classes.knownForLabel}>
          <Typography variant="h4">Known for:</Typography>
        </Grid>
        {people.movies.map((movie, index) => (
          <Grid item lg={3} key={index}>
            <MovieCard
              id={movie.id}
              poster={`${baseUrl}${movie.posterUrl}`}
              title={movie.title}
            />
          </Grid>
        ))}
      </Grid>
    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
  container: {
    display: "flex",
  },
  image: {
    height: "46rem",
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
    textAlign: "start",
    marginTop: "1rem",
  },
  peopleDetails: {
    width: "70%",
    padding: "2rem",
  },
  specifications: {
    display: "flex",
    alignItems: "start",
    justifyContent: "space-between",
    height: "10%",
  },
  biography: {
    textAlign: "justify",
  },
  knownForLabel: {
    marginTop: "4rem",
    display: "flex",
    justifyContent: "flex-start",
  },
  moviesContainer: {
    justifyContent: "space-around",
  },
}));

export default PeoplePage;

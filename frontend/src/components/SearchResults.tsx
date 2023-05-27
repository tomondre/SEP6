import React, { FunctionComponent } from "react";
import { IMovie, IPerson } from "../types";
import { makeStyles } from "tss-react/mui";
import { Colors } from "../constants/Colors";
import Typography from "@mui/material/Typography";
import { movieSearchLimit } from "../constants/GeneralConstants";
import FilterSelection from "./FilterSelection";


interface Props {
  items?: (IMovie | IPerson)[];
  filters:{
    people:boolean;
    movies:boolean;
  }
}

interface MovieItemProps {
  movie: IMovie;
  shouldHaveSeparator: boolean;
  navigateToMovie: (movieId: number) => void;
}

interface PersonItemProps {
  person: IPerson;
  shouldHaveSeparator: boolean;
  navigateToPerson: (personId: number) => void;
}

const MovieItem: FunctionComponent<MovieItemProps> = ({
  movie,
  shouldHaveSeparator,
  navigateToMovie,
}) => {
  const { classes } = useStyles();

  return (
    <li
      className={`${classes.result} ${shouldHaveSeparator && classes.separation}`}
      onClick={() => navigateToMovie(movie.id)}
    >
      <img
        src={`https://image.tmdb.org/t/p/w200${movie.posterUrl}`}
        className={classes.poster}
      />
      <Typography variant="p" color={Colors.black75}>
        {movie.title}
      </Typography>
    </li>
  );
};

const PersonItem: FunctionComponent<PersonItemProps> = ({
  person,
  shouldHaveSeparator,
  navigateToPerson,
}) => {
  const { classes } = useStyles();

  return (
    <li
      className={`${classes.result} ${shouldHaveSeparator && classes.separation}`}
      onClick={() => navigateToPerson(person.id)}
    >
      <img
        src={`https://image.tmdb.org/t/p/w200${person.profileImg}`}
        className={classes.poster}
      />
      <Typography variant="p" color={Colors.black75}>
        {person.name}
      </Typography>
    </li>
  );
};

const SearchResults: FunctionComponent<Props> = ({ items, filters}) => {
  const { classes } = useStyles();

  const navigateToMovie = (movieId: number) => {
    window.location.href = `/movies?id=${movieId}`;
  };

  const navigateToPerson = (personId: number) => {
    window.location.href = `/person?id=${personId}`;
  };

  if (!items) return null;

  if (!items.length)
    return (
      <div className={classes.resultContainer}>
        <Typography variant="p" color={Colors.black75}>
          No results found
        </Typography>
      </div>
    );

  return (
    <div className={classes.resultContainer}>
      <ul className={classes.ulStyling}>
        {items.slice(0, movieSearchLimit).map((item, index) => {
          const shouldHaveSeparator = index !== 0;

          if ("title" in item) {
            if(!filters.movies) return null;
            const movie = item as IMovie;

            return (
              <MovieItem
                key={movie.id}
                movie={movie}
                shouldHaveSeparator={shouldHaveSeparator}
                navigateToMovie={navigateToMovie}
              />
            );
          } else {
            if(!filters.people) return null;
            const person = item as IPerson;
            return (
              <PersonItem
                key={person.id}
                person={person}
                shouldHaveSeparator={shouldHaveSeparator}
                navigateToPerson={navigateToPerson}
              />
            );
          }
        })}
      </ul>
    </div>
  );
};

const useStyles = makeStyles()(() => ({
  resultContainer: {
    zIndex: 2,
    position: "absolute",
    background: Colors.white,
    width: "32rem",
    marginTop: "-8px",
    borderRadius: "0.5rem",
  },
  ulStyling: {
    padding: 0,
    margin: 0,
  },
  result: {
    listStyleType: "none",
    display: "flex",
    alignItems: "center",
    width: "100%",
    cursor: "pointer",
    paddingBlock: "1rem",
    gap: "1.5rem",
    "&:hover": {
      backgroundColor: Colors.gray,
    },
  },
  poster: {
    width: "3rem",
    height: "4.5rem",
    marginLeft: "1.5rem",
  },
  separation: {
    borderTop: `1px solid ${Colors.black50}`,
  },
}));

export default SearchResults;

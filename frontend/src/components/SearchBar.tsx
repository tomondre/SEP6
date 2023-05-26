import React, { ChangeEvent, useState, FunctionComponent } from "react";
import { makeStyles } from "tss-react/mui";
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

import { Colors } from "../constants/Colors";
import debounce from "../utils/debounce";

import { IMovie, IPerson } from "../types";
import MovieService from "../services/movies";
import PersonService from "../services/person-service";
import SearchResults from "./SearchResults";

const SearchBar: FunctionComponent = ({}) => {
  const { classes } = useStyles();

  const [movies, setMovies] = useState<IMovie[] | undefined>([]);
  const [persons, setPersons] = useState<IPerson[] | undefined>([]);
  const [tempMovies, setTempMovies] = useState<IMovie[] | undefined>();
  const [tempPersons, setTempPersons] = useState<IPerson[] | undefined>();


  const searchItemsByName = async (event: ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;

    if (!inputValue) {
      setMovies([]);
      setPersons([]);

      return;
    }

    const [retrievedMovies, retrievedPersons] = await Promise.all([
      MovieService.getMovies(0, "", inputValue),
      PersonService.getPeople(inputValue),
    ]);

    setMovies(retrievedMovies);
    setPersons(retrievedPersons);
  };

  const hideSearchResults = () => {
    // save the last movies so when the user clicks on searchbar again it will not do another request
    setTempMovies(movies);
    setTempPersons(persons);
    setMovies(undefined);
    setPersons(undefined);
  };

  const showSearchResults = () => {
    setMovies(tempMovies);// set movies to the last movies
    setPersons(tempPersons);
    setTempMovies(undefined);// reset tempMovies
    setTempPersons(undefined);
  };

  const debouncedHandleSearch = debounce(searchItemsByName, 250);
  const debounceHideSearch = debounce(hideSearchResults, 250);

  const combinedItems = [...(movies || []), ...(persons || [])];

  return (
    <>
      <TextField
        className={classes.searchInput}
        variant="outlined"
        placeholder="Search..."
        onChange={debouncedHandleSearch}
        onBlur={debounceHideSearch}
        onFocus={showSearchResults}
        InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <IconButton>
                <SearchIcon />
              </IconButton>
            </InputAdornment>
          ),
        }}
      />

      <SearchResults items={combinedItems} />
    </>
  );
};

const useStyles = makeStyles()(() => ({
  searchInput: {
    flexGrow: 1,
    background: Colors.white80,
    borderRadius: "0.5rem",
    width: "32rem",
    height: "3.5rem",
    position: "relative",
  },
}));

export default SearchBar;

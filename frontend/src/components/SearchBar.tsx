import React, { ChangeEvent, useState, FunctionComponent } from "react";
import { makeStyles } from "tss-react/mui";
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

import { Colors } from "../constants/Colors";
import debounce from "../utils/debounce";

import { IMovie } from "../types";
import MovieService from "../services/movies";
import SearchResults from "./SearchResults";

const SearchBar: FunctionComponent = ({}) => {
  const { classes } = useStyles();

  const [movies, setMovies] = useState<IMovie[] | undefined>();
  const [tempMovies, setTempMovies] = useState<IMovie[] | undefined>();

  const searchMoviesByName = async (event: ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;

    if (!inputValue) {
      return setMovies([]);
    }

    const retrievedMovies = await MovieService.getMovies(0, "", inputValue);
    setMovies(retrievedMovies);
  };

  const hideSearchResults = () => {
    //save the last movies so when the user clicks on searchbar again it will not do another request
    setTempMovies(movies); 
    setMovies(undefined);
  };

  const showSearchResults = () => {
    setMovies(tempMovies); // set movies to the last movies
    setTempMovies(undefined); // reset tempMovies
  };

  const debouncedHandleSearch = debounce(searchMoviesByName, 250);

  return (
    <>
      <TextField
        className={classes.searchInput}
        variant="outlined"
        placeholder="Search..."
        onChange={debouncedHandleSearch}
        onBlur={hideSearchResults}
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

      <SearchResults movies={movies} />
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

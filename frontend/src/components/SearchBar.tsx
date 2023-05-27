import React, { ChangeEvent, useState, FunctionComponent, useEffect, useRef } from "react";
import { makeStyles } from "tss-react/mui";
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

import { Colors } from "../constants/Colors";
import debounce from "../utils/debounce";

import { IMovie, IPerson } from "../utils/types";
import MovieService from "../services/movies";
import PersonService from "../services/person-service";
import SearchResults from "./SearchResults";
import FilterListRoundedIcon from '@mui/icons-material/FilterListRounded';
import FilterSelection from "./FilterSelection";

const SearchBar: FunctionComponent = ({}) => {
  const { classes } = useStyles();
  const componentRef = useRef(null);
  const [results, setResults] = useState<(IMovie | IPerson)[] | []>()
  const [filterVisbility, setFilterVisibility] = useState<boolean>(false);
  const [filters, setFilters] = useState<{
    people:boolean;
    movies:boolean;
  }>({people:true, movies:true});
  const [viewResults, setViewResults] = useState(false);

  const searchItemsByName = async (event: ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;

    if (!inputValue) {
      setResults([]);

      return;
    }

    const [retrievedMovies, retrievedPersons] = await Promise.all([
      MovieService.getMovies(0, "", inputValue),
      PersonService.getPeople(inputValue),
    ]);

    setResults([
      ...retrievedMovies,
      ...retrievedPersons,
    ])
    setViewResults(true);
  };

  const hideSearchResults = () => {
    setViewResults(false);
  };

  const showSearchResults = () => {
    setViewResults(true);
  };

  const debouncedHandleSearch = debounce(searchItemsByName, 250);

  const toggleFiltersVisibility = () => {
    setFilterVisibility(curr => !curr)
  }

  const changeFilters = (people:boolean, movies:boolean) => {
    setFilters({
      people,
      movies,
    })
  }

  useEffect(() => {
    if(!!results) {
      setFilterVisibility(false);
    }
  },[results])

  useEffect(() => {
    function handleClickOutside(event: any) {
      if (
        componentRef.current &&
        // @ts-ignore
        !componentRef.current.contains(event.target)
      ) {
        hideSearchResults();
      }
    }
    document.addEventListener("mousedown", handleClickOutside);

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [componentRef]);

  return (
    <div ref={componentRef}>
      <TextField
        className={classes.searchInput}
        variant="outlined"
        placeholder="Search..."
        onChange={debouncedHandleSearch}
        onFocus={showSearchResults}
        InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <IconButton>
                <SearchIcon />
              </IconButton>
            </InputAdornment>
          ),
          endAdornment:(
            <InputAdornment position="end" onClick={toggleFiltersVisibility}>
              <IconButton>
                <FilterListRoundedIcon/>
              </IconButton>
            </InputAdornment>
          )
        }}
      />

      <SearchResults items={results} filters={filters} visible={viewResults}/>

      {
        filterVisbility && 
        <FilterSelection
          setFilters={changeFilters}
          filters={filters}
        />
      }
    </div>
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


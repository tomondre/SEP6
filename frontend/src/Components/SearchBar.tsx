import React, { ChangeEvent } from 'react';
import { makeStyles } from 'tss-react/mui';
import { TextField, IconButton } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

const SearchBar = () => {
  const { classes } = useStyles();

  const handleSearch = (event: ChangeEvent<HTMLInputElement>) => {
    // Handle search functionality
    console.log(event.target.value);
  };

  return (
    <div className={classes.searchContainer}>
      <TextField
        className={classes.searchInput}
        variant="outlined"
        placeholder="Search..."
        onChange={handleSearch}
      />
      <IconButton color="primary" aria-label="search">
        <SearchIcon />
      </IconButton>
    </div>
  );
};

const useStyles = makeStyles()(() => ({
    searchContainer: {
      display: 'flex',
      alignItems: 'center',
      maxWidth: 300,
      margin: '0 auto',
    },
    searchInput: {
      flexGrow: 1,
    },
  }));

export default SearchBar;

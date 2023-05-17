import React, { ChangeEvent } from 'react';
import { makeStyles } from 'tss-react/mui';
import { TextField, IconButton, InputAdornment } from '@mui/material';
//import SearchIcon from '@mui/icons-material/Search';

import { Colors } from '../Constants/Colors';

const SearchBar = () => {
  const { classes } = useStyles();

  const handleSearch = (event: ChangeEvent<HTMLInputElement>) => {
    console.log(event.target.value);
  };

  return (
        <TextField
        className={classes.searchInput}
        variant="outlined"
        placeholder="Search..."
        onChange={handleSearch}
        // InputProps={{
        //   startAdornment: (
        //     <InputAdornment position="start">
        //       <IconButton>
        //         <SearchIcon />
        //       </IconButton>
        //     </InputAdornment>
        //   ),
        // }}
        />
  );
};

const useStyles = makeStyles()(() => ({
    searchInput: {
      flexGrow: 1,
      background: Colors.white80,
      borderRadius: '0.5rem',
      width: '32rem',
      height: '3.5rem'
    },
  }));

export default SearchBar;

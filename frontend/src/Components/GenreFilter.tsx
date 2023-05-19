import React from 'react';
import { FormControl, InputLabel, MenuItem, Select, SelectChangeEvent } from '@mui/material';
import { Colors } from '../constants/Colors';
import { makeStyles } from 'tss-react/mui';

interface GenreFilterProps {
  selectedGenre: number | '';
  onChange: (event: SelectChangeEvent<number>) => void;
}

interface GenreOption {
  value: number | '';
  label: string;
}

const genreOptions: GenreOption[] = [
  { value: '', label: 'All' },
  { value: 28, label: 'Action' },
  { value: 53, label: 'Thriller' },
  { value: 80, label: 'Crime' },
  { value: 18, label: 'Drama' },
  { value: 12, label: 'Adventure' },
  { value: 14, label: 'Fantasy' },
  { value: 16, label: 'Animation' },
  { value: 35, label: 'Comedy' },
  { value: 36, label: 'History' },
  { value: 99, label: 'Documentary' },
  { value: 878, label: 'Science Fiction' },
  { value: 9648, label: 'Mystery' },
  { value: 10402, label: 'Music' },
  { value: 10749, label: 'Romance' },
  { value: 10751, label: 'Family' },
  { value: 10752, label: 'War' },
];

const GenreFilter: React.FC<GenreFilterProps> = ({ selectedGenre, onChange }) => {
  const { classes } = useStyles();
  
  return (
    <FormControl className={classes.genres}>
      <InputLabel
      className={classes.lightCyan}
      >Genre</InputLabel>
      <Select
        className={classes.lightCyan + " " + classes.lightCyanBorder}
        value={selectedGenre}
        onChange={onChange}
        label="Genre"
      >
        {genreOptions.map((option) => (
          <MenuItem key={option.value} value={option.value}>
            {option.label}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

const useStyles = makeStyles()(() => ({
  genres:{
    width: '10rem',
    margin: '1rem',
    color: Colors.lightCyan,

  },
  lightCyan:{
    color: Colors.lightCyan,
  },
  lightCyanBorder:{
    border: '1px solid ' + Colors.lightCyan,
  },
}));

export default GenreFilter;

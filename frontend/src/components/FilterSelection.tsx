import React, { FunctionComponent } from 'react'
import { Checkbox, FormControlLabel, FormGroup } from '@mui/material';
import { makeStyles } from 'tss-react/mui';
import { Colors } from '../constants/Colors';

interface Props {
    filters:{
        people:boolean;
        movies:boolean;
    }
    setFilters(people:boolean, movies:boolean): void;
}

const FilterSelection:FunctionComponent<Props> = ({
    filters,
    setFilters
}) => {
  const { classes } = useStyles();

  return (
    <div className={classes.checkboxContainer}>
        <FormGroup>
            <FormControlLabel className={classes.formControlTextStyling} control={
                 <Checkbox
                    checked={filters.people}
                    onChange={(event) => setFilters(event.target.checked, filters.movies)}
                />
            } label="People" />
            <FormControlLabel className={classes.formControlTextStyling} control={
                 <Checkbox
                    checked={filters.movies}
                    onChange={(event) => setFilters(filters.people, event.target.checked)}
                />
            } label="Movies" />
        </FormGroup>
    </div>
  )
}

const useStyles = makeStyles()(() => ({
    checkboxContainer:{
        display:'flex',
        flexDirection:'column',
        gap:'1rem',
        position:'absolute',
        backgroundColor:Colors.white,
        padding:'1rem',
        transform: 'translateX( calc(512px - 100%))',
        borderRadius:'0.5rem',
        zIndex:2,
    },
    formControlTextStyling:{
        fontWeight:300
    }
}));

export default FilterSelection
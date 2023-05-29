import { Typography } from "@mui/material";
import React from "react";
import { makeStyles } from "tss-react/mui";
import {Link as RouterLink } from "react-router-dom";
import { Colors } from "../constants/Colors";

type PeopleCardProps = {
  profileImg: string;
  name: string;
  type:string
  id: number;
};

const PeopleCard: React.FC<PeopleCardProps> = ({ profileImg, name, id,type }) => {
  const { classes } = useStyles();

  return (
    <div>
      <RouterLink to={`/person?id=${id}`}>
        <img src={`https://image.tmdb.org/t/p/w200${profileImg}`} className={classes.image} alt={name} />
        <Typography variant="h6" className={classes.padding}>
          {name}
        </Typography>
      </RouterLink>
      
        <Typography variant="h6" className={classes.padding}>
          {type}
        </Typography>
      

    </div>
  );
};

const useStyles = makeStyles()(() => ({
  image:{
      marginTop: '3rem',
      width :'16rem',
      height: '24rem'
  },
  padding:{
      padding: '1rem 0',
  },
}));
  

export default PeopleCard;

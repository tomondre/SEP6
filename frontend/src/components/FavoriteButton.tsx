import React, { useState } from 'react';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { makeStyles } from 'tss-react/mui';
import { Colors } from '../constants/Colors';
import profileServie from '../services/account-service';

type ButtonProps = {
  movieId: number;
  isFave: boolean;
  removeMovie?: (movieId: number) => void;
};

const FavoriteButton: React.FC<ButtonProps> = ({ movieId, isFave, removeMovie }) => {
  const [isFavorite, setIsFavorite] = useState(isFave);
  const { classes } = useStyles();

  const handleFavoriteClick = async () => {
    try{
        if(isFavorite){
         await profileServie.deleteFavoriteMovie(movieId);
         if(removeMovie)
         removeMovie(movieId);
        } else {
            //await profileServie.addFavoriteMovie(movieId);
        }
    }
    catch(error){
        console.log(error);
    }

    setIsFavorite((current) => !current);
  };

  return (
    <FavoriteIcon
      className={isFavorite ? classes.favorite : classes.notFavorite}
      onClick={handleFavoriteClick}
    />
  );
};

const useStyles = makeStyles()(() => ({
    favorite: {
        color: Colors.red1,
        cursor: 'pointer',
        fontSize: '2rem',
        '&:hover': {
            color: Colors.red2,
        },
    },
    notFavorite: {
        color: Colors.black,
        cursor: 'pointer',
        fontSize: '2rem',
        '&:hover': {
            color: Colors.black50,
        },
    },

}));

export default FavoriteButton;

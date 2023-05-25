import React, {useEffect, useState} from 'react';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { makeStyles } from 'tss-react/mui';
import { Colors } from '../constants/Colors';
import profileService from '../services/account-service';
import { getUserId } from "../services/user-service";

type ButtonProps = {
  movieId: number;
  isFave: boolean;
};

const FavoriteButton: React.FC<ButtonProps> = ({ movieId, isFave= false }) => {
  const [isFavorite, setIsFavorite] = useState(isFave);
  useEffect(() => { setIsFavorite(isFave)}, [isFave] );
  const { classes } = useStyles();
  const userId = getUserId() || 0;

  const handleFavoriteClick = async () => {
    try{
        if(isFavorite){
         await profileService.deleteFavoriteMovie(movieId);
        } else {
          userId!==0 && await profileService.addFavourite(userId,movieId);
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
        color: Colors.white,
        cursor: 'pointer',
        fontSize: '2rem',
        '&:hover': {
            color: Colors.white80,
        },
    },

}));

export default FavoriteButton;

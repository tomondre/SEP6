import Carousel from "react-material-ui-carousel";
import { makeStyles } from "tss-react/mui";
import React from "react";
import { IMovie } from "../utils/types";
import {Link as RouterLink } from "react-router-dom";


interface CarouselComponentProps {
  movies: IMovie[];
}

const CarouselComponent: React.FC<CarouselComponentProps> = ({ movies }) => {
const { classes } = useStyles();

 return <Carousel
          indicatorContainerProps={{
            style: {
              display: "none",
            },
          }} 
        >
              {movies.map((movie) => (
                  <div key={movie.id}>
                    <RouterLink to={`/movies?id=${movie.id}`}>
                      <img src={`https://image.tmdb.org/t/p/w500${movie.posterUrl}`} alt={movie.title} className={classes.image} />
                    </RouterLink>
                  
                  </div>
                ))}
            </Carousel>
}

const useStyles = makeStyles()(() => ({
        image: {
        height: '80vh'
        }
}));


export default CarouselComponent
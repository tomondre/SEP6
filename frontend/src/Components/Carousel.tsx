import Carousel from "react-material-ui-carousel";
import { makeStyles } from "tss-react/mui";
import { Colors } from "../Constants/Colors";
import React from "react";

const CarouselComponent = () => {
const { classes } = useStyles();

const logo = require("../images/shrek.png");
const background = require("../images/dictator.png");

const mockedImages = [
    { id: 1, image: logo },
    { id: 2, image: background }
  ];

 return <Carousel
              className={classes.carousel}
              navButtonsProps={{
                style: {
                  position: "absolute",
                  marginTop: '16rem',
                  marginLeft: "3rem",
                  opacity: "1",
                },
              }}
              activeIndicatorIconButtonProps={{
                style: {
                  color: Colors.red1,
                  width: "2rem",
                  background: Colors.red1,
                  height: "0.7rem",
                  borderRadius: "1rem",
                  margin: "0 0.2rem",
                },
              }}
              indicatorContainerProps={{
                style: {
                  width: "7.2rem",
                  marginTop: -45,
                  marginLeft: "1rem",
                  position: "absolute",
                  zIndex: "2",
                },
              }}
            >
                {mockedImages.map((image) => (
                <div key={image.id}>
                    <img src={image.image} alt={`Image ${image.id}`} className={classes.image}/>
                </div>
                ))}
            </Carousel>
}

const useStyles = makeStyles()(() => ({
    carousel: {
        width: "100%",
      },
        image: {
        width: "37.9rem",
        height: '80vh'
        }
}));


export default CarouselComponent
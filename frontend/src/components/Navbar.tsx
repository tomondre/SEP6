import React, { FunctionComponent } from "react";
import { Grid, Typography, Link } from "@mui/material";
import { makeStyles } from "tss-react/mui";
import { Colors } from "../constants/Colors";
import SearchBar from "./SearchBar";
import { getUserId } from "../services/user-service";

interface Links {
  destination: string;
  link: string;
}

const Navbar: FunctionComponent = () => {
  const logo = require("../images/Logo.png");
  const { classes } = useStyles();
  const user = getUserId();

  const links: Links[] = user
  ? [{ destination: "Home", link: "/" }]
  : [
      { destination: "Home", link: "/" },
      { destination: "Sign Up", link: "/sign-up" },
      { destination: "Login", link: "/login" },
    ];


  return (
    <Grid container className={classes.navbar}>
      <Link className={classes.link} href="/">
        <Grid item container>
          <Grid item>
            <img className={classes.logo} src={logo} />
          </Grid>
          <Grid item className={classes.textContainer}>
            <Typography variant="h4" className={classes.title}>
              CINEMATE
            </Typography>
          </Grid>
        </Grid>
      </Link>

      <Grid item>
        <SearchBar />
      </Grid>

      <Grid item container lg={2} className={classes.menu}>
        {links.map(({ destination, link }, index) => (
          <Grid item key={index}>
            <Link href={link} key={index}>
              <Typography className={classes.navbarText} variant="h6">
                {destination}
              </Typography>
            </Link>
          </Grid>
        ))}
          <Grid item>
            {
              user && 
              <Typography className={classes.navbarText} variant="h6">
                Log-out
              </Typography>
            }
            </Grid>
      </Grid>
    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
  logo: {
    width: "4rem",
    height: "4rem",
  },
  title: {
    background: "linear-gradient(270deg, #CF0A0A 0%, #F97676 100%)",
    backgroundClip: "text",
    color: "transparent",
    lineHeight: "1.5em",
  },
  link: {
    textDecoration: "none",
  },
  navbar: {
    backgroundColor: Colors.black75,
    padding: "1rem 4rem",
    alignItems: "center",
    justifyContent: "space-around",
    // marginBottom:'3rem',
  },
  textContainer: {
    display: "grid",
    alignItems: "center",
    padding: "0 1rem",
  },
  navbarText: {},
  menu: {
    justifyContent: "space-between",
  },
}));

export default Navbar;

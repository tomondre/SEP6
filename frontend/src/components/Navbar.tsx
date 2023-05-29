import React, { FunctionComponent, useState } from "react";
import { Grid, Typography, Link } from "@mui/material";
import { makeStyles } from "tss-react/mui";
import { Colors } from "../constants/Colors";
import SearchBar from "./SearchBar";
import { getUserId } from "../services/user-service";
import authService from "../services/authentication";
import theme from "../theme/theme";

interface Links {
  destination: string;
  link: string;
}

const Navbar: FunctionComponent = () => {
  const logo = require("../images/Logo.png");
  const { classes } = useStyles();
  const [userId, setUserId] = useState(getUserId());

  let links: Links[];

  if (userId){
    links = [{ destination: "Home", link: "/" },
      { destination: "Profile", link: "/profile" },
      { destination: "Statistics", link: "/statistics" }]
  } else {
    links = [{ destination: "Home", link: "/" },
      { destination: "Sign Up", link: "/sign-up" },
      { destination: "Statistics", link: "/statistics" },
      { destination: "Login", link: "/login" }];
  }

    const handleLogout = () => {
      authService.logout();
      setUserId(null);
      window.location.href="/";
    };

  return (
    <Grid container className={classes.navbar}>
      <Link className={classes.link} href="/">
        <Grid item container>
          <Grid item>
            <img className={classes.logo} src={logo} />
          </Grid>
          <Grid item className={classes.textContainer}>
            <Typography variant="h4" className={classes.title}>
              Best Movies
            </Typography>
          </Grid>
        </Grid>
      </Link>

      <Grid item>
        <SearchBar />
      </Grid>

      <Grid item container xs={12} sm={12} md={12} lg={3} className={classes.menu}>
        {links.map(({ destination, link }, index) => (
          <Grid item key={index} className={classes.menuItem}>
            <Link href={link} key={index}>
              <Typography variant="h6">
                {destination}
              </Typography>
            </Link>
          </Grid>
        ))}
          <Grid item className={classes.menuItem}>
            {
              userId &&
              <Typography className={classes.logout} variant="h6" onClick={handleLogout}>
                Logout
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
  menu: {
    justifyContent: "space-around",
    width: '100%'
  },
  logout: {
    cursor: "pointer",
  },
  menuItem:{
    margin: 'auto',
    [theme.breakpoints.down('custom1234')]: {
      marginTop: '1rem',
      margin: 'auto',
    }
  }
}));

export default Navbar;

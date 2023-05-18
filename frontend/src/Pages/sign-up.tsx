import React from "react";
import { makeStyles } from "tss-react/mui";
import { Typography, Button, Link } from "@mui/material"
import { Colors }  from '../Constants/Colors';
import SignUpField from "../Components/SignUpField";


const SignUp = () => {

  const { classes } = useStyles();

  return (
    <>
     <Typography variant="h2">
          SignUp
        </Typography>
    <form className={classes.sign_up_form}>
        <SignUpField label="First Name" data="" />
        <SignUpField label="Last Name" data="" />
        <SignUpField label="Email" data="" />
        <SignUpField label="Password" data="" />
        <div>
        <Button variant="contained">
          Register
        </Button>
        </div>
    </form>
    <div className={classes.already}>
      <div className={classes.line}></div>
      <Typography variant="h5">Already a member?</Typography>
      <div className={classes.line}></div>
    </div>
    <div>
    <Link href="/login">
      <Typography variant="h4">Login</Typography>
    </Link>
    </div>
    

    </>
  );
};

const useStyles = makeStyles()(() => ({
  sign_up_form:{
    display:'grid',
    justifyContent: 'center',
    alignItems:'center'
  },
  already:{
    display:'flex',
    alignItems:'center',
    justifyContent:'center',
    margin:'2rem'

  },
  line: {
    border: '0.25rem solid',
    borderColor: Colors.lightCyan,
    height: '0rem',
    width:'14rem',
    margin: '1rem'
    
  },
}));


export default SignUp;



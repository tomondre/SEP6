import React from "react";
import { makeStyles } from "tss-react/mui";
import { Typography, Button } from "@mui/material"
import { Colors }  from '../Constants/Colors';
import SignUpField from "../Components/SignUpField";


const SignUp = () => {

  const { classes } = useStyles();

  return (
    <>
     <Typography variant="h1">
          SignUp
        </Typography>
    <form className={classes.sign_up_form}>
        <SignUpField label="First Name" data="" />
        <SignUpField label="Last Name" data="" />
        <SignUpField label="Email" data="" />
        <SignUpField label="Password" data="" />
        <div>
        <Button className={classes.sign_up_button} variant="contained">Register</Button>
        </div>
    </form>
    <div className={classes.already}>
      <div className={classes.firstLine}></div>
      <div>Already a member?</div>
      <div className={classes.secondLine}></div>
    </div>
    <div>
    <a href="/">Login</a>
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
  
  sign_up_button:{
    textAlign: 'center',
    fontSize:'2.188rem',
    fontStyle:'normal',
    fontFamily:'Rubik',
    fontWeight:'800',
    color:Colors.light_blue,
    width:'16rem',
    height: '3rem',
    background: Colors.red1,
    borderRadius: '1.25rem',
    margin:'2rem'
  },
  already:{
    display:'flex',
    alignItems:'center',
    justifyContent:'center',
    margin:'2rem'

  },
  firstLine: {
    border: '0.25rem solid',
    borderColor: Colors.black1,
    height: '0rem',
    width:'14rem',
    margin: '1rem'
    
  },
  secondLine: {
    border: '0.25rem solid',
    borderColor: Colors.black1,
    height: '0rem',
    width:'14rem',
    margin: '1rem'
  }
}));


export default SignUp;



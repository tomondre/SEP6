import React from "react";
import { makeStyles } from "tss-react/mui";
import { Colors }  from '../Constants/Colors';
import SignUpField from "../Components/SignUpField";

const SignUp = () => {

  const { classes } = useStyles();

  return (
    <form className={classes.sign_up_form}>
        <h1>SignUp</h1>
        <SignUpField label="First Name" data="" />
        <SignUpField label="Last Name" data="" />
        <SignUpField label="Email" data="" />
        <SignUpField label="Password" data="" />
        <input type="submit" className={classes.sign_up_button} value="Sign Up"/>
    </form>
  );
};

const useStyles = makeStyles()(() => ({
  sign_up_button:{
    textAlign: 'center',
    fontSize:'3.188rem',
    fontStyle:'normal',
    fontFamily:'Rubik',
    fontWeight:'800',
    width: '16.25rem',
    height: '5.938rem',
    background: Colors.red1,
    borderRadius: '1.25rem'
  },

  sign_up_form:{
    display:'grid',
    justifyContent: 'center',
    alignItems:'center'
  }
}));


export default SignUp;



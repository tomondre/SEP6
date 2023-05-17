import React, { useState }  from "react";
import { makeStyles } from "tss-react/mui";
import { Typography, Button, Link, OutlinedInput } from "@mui/material"
import { Colors }  from '../Constants/Colors';
import { useNavigate } from "react-router-dom";
import AuthService from "../Services/authentication";
import { useForm } from "react-hook-form";

type Account = {
  email: string,
  password: string
}

const Login = () => {

  const { classes } = useStyles();
  const navigate = useNavigate();

  const { register, setValue, handleSubmit, formState: { errors } } = useForm<Account>();
  const onSubmit = handleSubmit(data => handleLogin(data));

  const handleLogin = async (data:Account) => {
    //e.preventDefault();
    try {
      await AuthService.login(data.email, data.password).then(
        () => {
          navigate("/");
          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
     <Typography variant="h1">Login</Typography>
    <form className={classes.login_form}  onSubmit={onSubmit}>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Email</Typography>
        <OutlinedInput label="Email"  className={classes.input}   {...register("email")} required/>
        </div>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Password</Typography>
        <OutlinedInput label="Password" className={classes.input} type="password"  {...register("password")}  required/>
        </div>
        <div>
        <Button className={classes.login_button} type="submit" variant="contained">Login</Button>
        </div>
    </form>
    <div className={classes.group}>
      <div className={classes.line}></div>
      <Typography variant="h5">Or</Typography>
      <div className={classes.line}></div>
    </div>

    <Link href="/sign-up">
      <Typography variant="h4">Sign Up</Typography>
    </Link>
    
    

    </>
  );
};

const useStyles = makeStyles()(() => ({
  login_form:{
    display:'grid',
    justifyContent: 'center',
    alignItems:'center'
  },
  
  login_button:{
  //   // textAlign: 'center',
   fontSize:'2.188rem',
  //   // fontStyle:'normal',
  //   // fontFamily:'Rubik',
  //   // fontWeight:'800',
  //   //color:Colors.lightCyan,
    width:'16rem',
    height: '3rem',
  //   //background: Colors.red1,
  //   //borderRadius: '1.25rem',
    margin:'2rem'
   },
  group:{
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
   fieldContainer:{
    display:'grid',
    justifyContent: 'center',
    alignItems:'center',
    margin:'1rem 0'
  },
  label:{
    textAlign:'start',
    lineHeight:'1.938rem',
    marginTop: '1rem',
    marginBottom: '1rem'
  },
  input:{
    width: '38.5rem',
    height: '2.5rem',
    background: Colors.lightCyan,
    border: '0.063rem',
    borderRadius: '0.625rem',
  },
 
}));


export default Login;



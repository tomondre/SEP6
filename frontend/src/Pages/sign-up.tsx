import React, { ChangeEvent, useState }  from "react";
import { makeStyles } from "tss-react/mui";
import { Typography, Button, OutlinedInput, Link } from "@mui/material"
import { Colors }  from '../Constants/Colors';
import { useNavigate } from "react-router-dom";
import AuthService from "../Services/authentication";
import { useForm } from "react-hook-form";

type Account = {
    name: string,
    username: string,
    email: string,
    password: string,
    country: string,
    profilePictureUrl: string,
    dateOfBirth: string,
    gender: string,
    role:string
};

const SignUp = () => {

  const { classes } = useStyles();
  const navigate = useNavigate();

  const { register, setValue, handleSubmit, formState: { errors } } = useForm<Account>();
  const onSubmit = handleSubmit(data => handleSignup(data));


  const handleSignup = async (data: Account) => {
  //e.preventDefault();

    try {
      await AuthService.signup(data.name,data.email,data.username, data.password,data.country,data.gender, data.dateOfBirth).then(
        (response) => {
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
    <form className={classes.sign_up_form} onSubmit={onSubmit}>
        <Typography variant="h1"> Sign Up</Typography>
        
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Name</Typography>
        <OutlinedInput label="Name" className={classes.input} {...register("name")} required/>
        </div>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Email</Typography>
        <OutlinedInput label="Email"  className={classes.input}   {...register("email")} required/>
        </div>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Username</Typography>
        <OutlinedInput label="Username"  className={classes.input}  {...register("username")} required/>
        </div>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Password</Typography>
        <OutlinedInput label="Password" className={classes.input} type="password"  {...register("password")}  required/>
        </div>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Date of birth</Typography>
        <OutlinedInput label="DateOfBirth" placeholder='yyyy-mm-dd' className={classes.input} type="text"  {...register("dateOfBirth")}  required/>
        </div>
        <div className={classes.fieldContainer}>
        <Typography variant="h5" className={classes.label}> Country</Typography>
        <OutlinedInput label="Country" className={classes.input}  {...register("country")}  required/>
        </div>
        <Typography variant="h5" className={classes.label}>Gender</Typography>
        <div className={classes.radioContainer}> 
        <input type="radio" value="Male" className={classes.radioInput} {...register("gender")} defaultChecked /> Male
        <input type="radio" value="Female" className={classes.radioInput} {...register("gender")}  /> Female
        <input type="radio" value="Other" className={classes.radioInput} {...register("gender")}  /> Other
        </div>
        <div>
        <Button className={classes.sign_up_button} type="submit" variant="contained">Register</Button>
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
  line: {
    border: '0.25rem solid',
    borderColor: Colors.lightCyan,
    height: '0rem',
    width:'14rem',
    margin: '1rem'
    
  },
  image:{
    image: {
      borderRadius: '1rem 0 0 1rem'
      }
  },
  radioContainer:{
    color:Colors.lightCyan,
    textAlign:'start',
    marginTop: '1rem',
    marginBottom: '1rem',
    fontSize:'1.5rem'
  },
  radioInput:{
    marginLeft:'4rem',
    marginRight:'0.5rem'
  },
  sign_up_button:{
    textAlign: 'center',
    fontSize:'2.188rem',
    fontStyle:'normal',
    fontFamily:'Rubik',
    fontWeight:'800',
    color:Colors.lightCyan,
    width:'16rem',
    height: '3rem',
    background: Colors.red1,
    borderRadius: '1.25rem',
    margin:'2rem'
  },
  calendar:{
    color:Colors.lightCyan,
  }
}));


export default SignUp;



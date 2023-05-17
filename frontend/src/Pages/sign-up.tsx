import React, { ChangeEvent, useState }  from "react";
import { makeStyles } from "tss-react/mui";
import { Typography, Button, OutlinedInput } from "@mui/material"
import { Colors }  from '../Constants/Colors';
import SignUpField from "../Components/SignUpField";
import { useNavigate } from "react-router-dom";
import AuthService from "../Services/authentication";
import { useForm } from "react-hook-form";
import logo from "../images/Logo.png";

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
      await AuthService.signup(data.name,data.email,data.username, data.password,data.country,data.gender).then(
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
        <div>
        <img src={logo} className={classes.image} alt="profile_picture" />
        </div>
        <OutlinedInput label="Name"  {...register("name")} required/>
        <OutlinedInput label="Email"     {...register("email")} required/>
        <OutlinedInput label="Username"    {...register("username")} required/>
        <OutlinedInput label="Password"  type="password"  {...register("password")}  required/>
        <OutlinedInput label="Country"   {...register("country")}  required/>
        <Typography variant="h5" className={classes.genderLabel}>Gender</Typography>
        <div> 
        <input type="radio" value="Male"   {...register("gender")} defaultChecked /> Male
      
        <input type="radio" value="Female" {...register("gender")}  /> Female
        <input type="radio" value="Other" {...register("gender")}  /> Other
        </div>
        
        
        
        <div>
        <Button className={classes.sign_up_button} type="submit" variant="contained">Register</Button>
        </div>
    </form>
    <div className={classes.already}>
      <div className={classes.firstLine}></div>
      <div>Already a member?</div>
      <div className={classes.secondLine}></div>
    </div>
    <div>
    <a href="/login">Login</a>
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
  },
  genderLabel:{
    width: '13.125rem',
    height: '1.563rem',
    textAlign:'start',
    fontSize:'1.625rem',
    fontStyle:'normal',
    fontFamily:'Rubik',
    fontWeight:'800',
    lineHeight:'1.938rem',
    margin: '1rem'
    //color:Colors.light_blue,
  },
  image:{
    image: {
      borderRadius: '1rem 0 0 1rem'
      }
  }
}));


export default SignUp;



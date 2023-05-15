import React, { useState }  from "react";
import { makeStyles } from "tss-react/mui";
import { Typography, Button } from "@mui/material"
import { Colors }  from '../Constants/Colors';
import SignUpField from "../Components/SignUpField";
import { useNavigate } from "react-router-dom";
import AuthService from "../Services/authentication";


const Login = () => {

  const { classes } = useStyles();
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    try {
      await AuthService.login(email, password).then(
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
     <Typography variant="h1">
          Login
        </Typography>
    <form className={classes.login_form}  onSubmit={handleLogin}>
        <SignUpField label="Email" value={email} typeOF="text" onChange={(e: { target: { value: React.SetStateAction<string>; }; }) => setEmail(e.target.value)} required/>
        <SignUpField label="Password" value={password} typeOF="password" onChange={(e: { target: { value: React.SetStateAction<string>; }; }) => setPassword(e.target.value)} required/>
        <div>
        <Button className={classes.login_button} type="submit" variant="contained">Login</Button>
        </div>
    </form>
    <div className={classes.group}>
      <div className={classes.firstLine}></div>
      <div>Or</div>
      <div className={classes.secondLine}></div>
    </div>
    <div>
    <a href="/sign-up">Create an account</a>
    </div>
    

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
  group:{
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


export default Login;



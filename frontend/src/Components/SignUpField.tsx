import React, { useState } from "react";
import { Typography, Button } from "@mui/material"
import { makeStyles } from "tss-react/mui";
import { Colors }  from '../Constants/Colors';

const SignUpField = (props:any) => {

    const { classes } = useStyles();

    return (
      <div className={classes.container} >
        <Typography variant="h5" className={classes.label}>{props.label}
        </Typography>
        <input className={classes.inputField}  type={props.typeOF}/>
      </div>
    );
  };

  const useStyles:any = makeStyles()(() => ({
    
    container:{
        display:'grid',
        justifyContent: 'center',
        alignItems:'center',
        margin:'1rem'
      },
      label:{
        width: '13.125rem',
        height: '1.563rem',
        textAlign:'start',
        fontSize:'1.625rem',
        fontStyle:'normal',
        fontFamily:'Rubik',
        fontWeight:'800',
        lineHeight:'1.938rem',
        marginTop: '1rem',
        marginBottom: '1rem'
        //color:Colors.light_blue,
      },
      inputField:{
        width: '38.5rem',
        height: '2.5rem',
        background: Colors.light_blue,
        border: '0.063rem',
        borderRadius: '0.625rem',
        
      }

  }));
  
  export default SignUpField;
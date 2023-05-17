import React from "react";
import { Typography, Button } from "@mui/material"
import { makeStyles } from "tss-react/mui";
import { Colors }  from '../Constants/Colors';

const SignUpField = (props:any) => {

    const { classes } = useStyles();

    return (
      <div className={classes.container}>
        <Typography variant="h5" className={classes.label}>
          {props.label}
        </Typography>
        <input className={classes.data} type="text" 
        value={props.data}/>
      </div>
    );
  };

  const useStyles:any = makeStyles()(() => ({
    
    container:{
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
      data:{
        width: '38.5rem',
        height: '2.5rem',
        background: Colors.lightCyan,
        border: '0.063rem',
        borderRadius: '0.625rem',
        
      }

  }));
  
  export default SignUpField;
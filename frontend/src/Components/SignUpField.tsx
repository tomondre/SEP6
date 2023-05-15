import React from "react";
import { makeStyles } from "tss-react/mui";
import { Colors }  from '../Constants/Colors';

const SignUpField = (props:any) => {

    const { classes } = useStyles();

    return (
      <div className={classes.container}>
        <h5>{props.label}</h5>
        <input type="text" 
        value={props.data}/>
      </div>
    );
  };

  const useStyles = makeStyles()(() => ({
    
    container:{
        display:'grid',
        justifyContent: 'center',
        alignItems:'center',
        width: '38.5rem',
        height: '5.125rem'
      },
      projectCardContainer:{
        textAlign: 'center',
        display:'grid',
        gridTemplateRows: '1fr 0.25fr',
        aspectRatio: '12/9',
        flexBasis:'100%',
        marginBottom:'1rem',
      }

  }));
  
  export default SignUpField;
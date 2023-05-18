
import axios from '../api/axios';
import { useParams } from 'react-router-dom';

const API_URL = "/actors";



  const getSpecificActor = async (id: number) => {
    try 
    {
        const response = await axios.get(`${API_URL}/${id}`)
        return response.data;
    } 
    catch (error) 
    {
      console.error(error);
    }

  };

  const actorService = {
    getSpecificActor
  };
  
  export default actorService;
//import axios from 'axios';
//const API_URL = "http://localhost:8081/actors"; 
import axios from '../api/axios';

const API_URL = "/actors";

interface Movie {
  id:number;
  title:string;
  posterUrl:string;
}

interface Actor {
  id: number;
  name: string;
  type: string;
  dateOfBirth: string;
  placeOfBirth: string;
  gender: string;
  biography: string;
  deathDate: string;
  profileImg: string;
  movies:Movie[]
}


  const getSpecificActor = async (id: number): Promise<Actor> => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        const actor = response.data;
        return actor as Actor;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};

  const actorService = {
    getSpecificActor
  };
  
  export default actorService;


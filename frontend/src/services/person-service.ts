
import axios from '../api/axios';
const API_URL = "/people";

interface Movie {
  id:number;
  title:string;
  posterUrl:string;
}

interface Person {
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


  const getPersonById = async (id: number): Promise<Person> => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        const person = response.data;
        return person as Person;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};

  const personService = {
    getPersonById
  };
  
  export default personService;



import axios from '../api/axios';
const API_URL = "/people";
import { IPerson } from "../types";


const getPeople = async (personName: string): Promise<IPerson[]> => {
  try {
    const response = await axios.get(API_URL, {
      params: {
        search: personName,
      },
      headers: {
        'Accept': 'application/json',
      },
    });

    const people = response.data;
    return people as IPerson[];
  } catch (error) {
    console.error('Error fetching people:', error);
    throw error;
  }
};

const getPersonById = async (id: number): Promise<IPerson> => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    const person = response.data;
    return person as IPerson;
  } catch (error) {
    console.error('Error fetching movies:', error);
    throw error;
  }
};

const personService = {
  getPersonById,
  getPeople,
};

export default personService;


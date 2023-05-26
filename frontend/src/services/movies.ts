import { IMovie } from "../types";

import axios from '../api/axios';
const API_URL = "/movies";

const getMovies = async (pageNumber?: number, genreId?: number | "", movieName?: string): Promise<IMovie[]> => {
    try {
        const response = await axios.get(`${API_URL}`, {
            params: {
                page: pageNumber,
                genreId: genreId,
                search: movieName
            },
            headers: {
                'Accept': 'application/json',
            },
        });

        const movies = response.data;
        return movies as IMovie[];
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};



const MovieService = {
    getMovies,
};

export default MovieService;

import { IMovie } from "../types";

import axios from '../api/axios';
const API_URL = "/movies";

const getMovies = async (pageNumber?: number, genreId?: number | ""): Promise<IMovie[]> => {
    try {
        const response = await axios.get(`${API_URL}`, {
            params: {
                page: pageNumber,
                genreId: genreId,
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

const filterMovies = async (pageNumber: number, genreId?: number | ""): Promise<IMovie[]> => {
    try {
        let movies = await getMovies(pageNumber, genreId);

        const filteredMovies = movies.map((movie: IMovie) => {
            const { id, genres, posterUrl, title } = movie;
            const filteredGenres = genres.map((genre) => ({ id: genre.id, name: genre.name }));
            return { id, genres: filteredGenres, posterUrl, title };
        });

        return filteredMovies;
    } catch (error) {
        console.error('Error filtering:', error);
        return [];
    }
};


const MovieService = {
    getMovies,
    filterMovies,
};

export default MovieService;

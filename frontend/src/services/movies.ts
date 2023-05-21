import axios from 'axios';
import { Movie } from "../types";

const API_URL = 'https://sep6-api.tomondre.com';

const getMovies = async (pageNumber?: number, genreId?: number | ""): Promise<Movie[]> => {
    try {
        const response = await axios.get(`${API_URL}/movies`, {
            params: {
                page: pageNumber,
                genreId: genreId,
            },
            headers: {
                'Accept': 'application/json',
            },
        });

        const movies = response.data;
        return movies as Movie[];
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};

const filterMovies = async (pageNumber: number, genreId?: number | ""): Promise<Movie[]> => {
    try {
        let movies = await getMovies(pageNumber, genreId);

        const filteredMovies = movies.map((movie: Movie) => {
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

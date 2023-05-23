import axios from '../api/axios';
import { Movie } from "../types";

const API_URL = "/movies";

const getMovies = async (pageNumber?: number, genreId?: number | ""): Promise<Movie[]> => {
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

const getMovieById = async (id: number): Promise<Movie> => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        const movie = response.data;
        return movie as Movie;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};


const MovieService = {
    getMovies,
    filterMovies,
    getMovieById
};

export default MovieService;

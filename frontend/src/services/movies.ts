import { IMovie, IReview } from "../types";
import axios from '../api/axios';
import authHeader from "./auth-header";

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

const getMovieById = async (id: number): Promise<IMovie> => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        const movie = response.data;
        return movie as IMovie;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};

const addReview = (movieId:number, rating:number, comment: string, createdOn: string,accountId:number,movieTitle:string) => {
    return axios
      .post(`${API_URL}/${movieId}/reviews`, {
        movieId,
        rating,
        comment,
        createdOn,
        accountId,
        movieTitle
      })
      .then((response: { data: {}; }) => {
        return response.data;
      });
  };

  const getReviewsByMovieId = async (id: number): Promise<IReview[]>  => {
    try {
        const response = await axios.get(`${API_URL}/${id}/reviews`,{
             
                headers: {
                    ...authHeader()
                }
            
        });
        const reviews = response.data;
        return reviews as IReview[];
    } catch (error) {
        console.error('Error fetching reviews:', error);
        throw error;
    }
};

const deleteReview = async (movieId: number, reviewId:number) => {
    try {
        await axios.delete(`${API_URL}/${movieId}/reviews/${reviewId}`, {
                headers: {
                    ...authHeader()
                }
        });
    } catch (error) {
        console.error('Error deleting review:', error);
        throw error;
    }
};
const MovieService = {
    getMovies,
    filterMovies,
    getMovieById,
    addReview,
    getReviewsByMovieId,
    deleteReview
};

export default MovieService;

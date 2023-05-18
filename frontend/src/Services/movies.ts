import axios from 'axios';

const API_URL = 'http://localhost:8081/';

interface Movie {
    id: number;
    genres: { id: number; name: string }[];
    posterUrl: string;
    title: string;
}

const getMovies = async (pageNumber: number): Promise<Movie[]> => {
    try {
        const response = await axios.get(`${API_URL}movies`, {
            params: {
                page: pageNumber,
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

const filterMovies = async (pageNumber: number): Promise<Movie[]> => {
    try {
        const movies = await getMovies(pageNumber);
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

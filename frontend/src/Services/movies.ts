import axios from 'axios';

const API_URL = 'http://localhost:8081/';

const getMovies = async (pageNumber: number) => {
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
        console.log(movies);
    } catch (error) {
        console.error('Error fetching movies:', error);
    }
};

const MovieService = {
    getMovies
};

export default MovieService;
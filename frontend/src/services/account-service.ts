import axios from '../api/axios';
import { getUserId } from "./user-service";

const userId = getUserId();

const getProfile = async () => {
    try {
        const response = await axios.get(`/accounts/${userId}`);
        const profile = response.data;
        return profile;
    } catch (error) {
        console.error('Error fetching profile:', error);
        throw error;
    }
};

const getFavoriteMovies = async () => {
    try {
        const response = await axios.get(`/accounts/${userId}/favourites`);
        const favoriteMovies = response.data;
        return favoriteMovies;
    } catch (error) {
        console.error('Error fetching favorite movies:', error);
        throw error;
    }
};

const deleteFavoriteMovie = async (movieId: number) => {
    try {
        await axios.delete(`/accounts/${userId}/favourites/${movieId}`);
    } catch (error) {
        console.error('Error deleting favorite movie:', error);
        throw error;
    }
};

const profileServie = {
    getProfile,
    getFavoriteMovies,
    deleteFavoriteMovie,
};

export default profileServie;
import axios from '../api/axios';
import {getUserId} from "./user-service";
import authHeader from "./auth-header";

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

const addFavourite = (userId: number, movieId:number) => {
    return axios
      .post(`/accounts/${userId}/favourites`, {
        userId,
        movieId
      }, {
          headers: {
              ...authHeader()
          }
      })
      .then((response: { data: {}; }) => {
        return response.data;
      });
  };

const getFavoriteMovies = async () => {
    try {
        const response = await axios.get(`/accounts/${userId}/favourites`, {
            headers: {
                ...authHeader()
            }
        });
        const favoriteMovies = response.data;
        return favoriteMovies;
    } catch (error) {
        console.error('Error fetching favorite movies:', error);
        throw error;
    }
};

const deleteFavoriteMovie = async (movieId: number) => {
    try {
        await axios.delete(`/accounts/${userId}/favourites/${movieId}`, {
                headers: {
                    ...authHeader()
                }
        });
    } catch (error) {
        console.error('Error deleting favorite movie:', error);
        throw error;
    }
};

const profileService = {
    getProfile,
    getFavoriteMovies,
    deleteFavoriteMovie,
    addFavourite
};

export default profileService;

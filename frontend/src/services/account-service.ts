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

const addFavourite = (userId: number, movieId:number) => {
    return axios
      .post(`/accounts/${userId}/favourites`, {
        userId,
        movieId,
      })
      .then((response: { data: {}; }) => {
        return response.data;
      });
  };

const profileService = {
    getProfile,
    addFavourite
};

export default profileService;
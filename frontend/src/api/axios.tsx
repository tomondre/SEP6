import axios from "axios";
import { getDecodedToken, getUserTokens } from "../services/user-service";

const userTokens = getUserTokens();
const baseURL = process.env.REACT_APP_URL;
const instance = axios.create({
  baseURL,
  headers: {
    "Content-type": "application/json",
    Authorization: userTokens ? `Bearer ${userTokens.access_token}` : "",
  },
});

export default instance;


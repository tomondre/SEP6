import axios from "axios";
import { getUserTokens } from "../services/user-service";

const userTokens = getUserTokens();

const instance = axios.create({
  baseURL: process.env.REACT_APP_URL,
  headers: {
    "Content-type": "application/json",
  },
});

if (userTokens) {
  instance.defaults.headers.common["Authorization"] = `Bearer ${userTokens.access_token}`;
}

export default instance;

import axios from "axios";
import { getUserTokens } from "../services/user-service";

const userTokens = getUserTokens();

const instance = axios.create({
  baseURL: "https://sep6-api.tomondre.com",
  headers: {
    "Content-type": "application/json",
  },
});

if (userTokens) {
  instance.defaults.headers.common["Authorization"] = `Bearer ${userTokens.access_token}`;
}

export default instance;


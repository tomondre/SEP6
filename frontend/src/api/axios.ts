import axios from "axios";
import { getUserTokens, getDecodedToken } from "../services/user-service";
import { memoizedRefreshToken } from "../services/refreshToken";

const userTokens = getUserTokens();

export const BASE_URL = "https://sep6-api.tomondre.com";
// export const BASE_URL = "http://localhost:8081";

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-type": "application/json",
  },
});

instance.interceptors.request.use(
  async (config) => {
    console.log("interceptor")
    if (userTokens && userTokens.access_token) {
      config.headers["Authorization"] = `Bearer ${userTokens.access_token}`;

      const decodedToken = getDecodedToken();

      if (!decodedToken) return config;

      const tokenExpireTime = decodedToken.exp * 1000;

      if (tokenExpireTime < Date.now()) {
        console.log("token expired")
        await memoizedRefreshToken().then(newTokens => {
          if (newTokens) {
            config.headers["Authorization"] = `Bearer ${newTokens.access_token}`;

            return config;
          }
        })
      }
    }

    return config;
  }
);

export default instance;

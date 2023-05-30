import mem from "mem";
import instance, { BASE_URL } from "../api/axios";
import { getUserTokens } from "./user-service";
import { toast } from "react-toastify";

import axios from "axios";

const refreshTokenFn = async () => {
  const userTokens = getUserTokens();

  if (!userTokens) return;

  instance.defaults.headers.common["Authorization"] = `Bearer ${userTokens.refresh_token}`;

  toast.info("Your token expired")

  try {
    const url = `${BASE_URL}/auth/refresh-token`;
    const response = await axios.post(url,
      {},
      {
        headers: {
          Authorization: `Bearer ${userTokens.refresh_token}`
        }
      });

    if (response) {
      localStorage.setItem("tokens", JSON.stringify(response.data))
      instance.defaults.headers.common["Authorization"] = `Bearer ${response.data.access_token}`;
    }

    return response.data;
  } catch (error) {
    localStorage.removeItem("tokens");
  }
};

const maxAge = 10000;

export const memoizedRefreshToken = mem(refreshTokenFn, {
  maxAge,
});
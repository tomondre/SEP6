import axios from "axios";

export default axios.create({
  baseURL: "https://sep6-api.tomondre.com",
  headers: {
    "Content-type": "application/json",
  },
});
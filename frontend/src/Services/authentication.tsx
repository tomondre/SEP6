import axios from "axios";

const API_URL = "/auth";

const signup = (name: any, email: any, username:any, password: any, ) => {
  return axios
    .post(API_URL + "/register", {
      name,
      email,
      username,
      password,
    })
    .then((response: { data: { accessToken: any; }; }) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const login = (email: any, password: any) => {
  return axios
    .post(API_URL + "/authenticate", {
      email,
      password,
    })
    .then((response: { data: { accessToken: any; }; }) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user")|| '{}');
};

const authService = {
  signup,
  login,
  logout,
  getCurrentUser,
};

export default authService;
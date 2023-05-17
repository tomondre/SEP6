import axios from "axios";

const API_URL = "http://localhost:8081/auth";



const signup = (name:string, email:string, username:string, password:string, country:string, gender:string) => {

  const profilePictureUrl="mhgjhgjh"
  const dateOfBirth = "2022-10-10"
  const role="USER"

  return axios
    .post(API_URL + "/register", {
      name,
      email,
      username,
      password,
      country,
      profilePictureUrl,
      dateOfBirth,
      gender,
      role
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
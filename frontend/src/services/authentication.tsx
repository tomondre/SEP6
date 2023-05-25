import axios from '../api/axios';

const API_URL = '/auth';



const signup = (name:string, email:string, username:string, password:string, country:string, gender:string, dateOfBirth:string) => {

  const profilePictureUrl=""
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
    .then((response: { data: {} }) => {
      if (response.data) {
        localStorage.setItem("tokens", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const login = (email: string, password: string) => {
  return axios
    .post(API_URL + "/authenticate", {
      email,
      password,
    })
    .then((response: { data: {}; }) => {
      console.log(response.data)
      if (response.data) {
        localStorage.setItem("tokens", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("tokens");
};

const authService = {
  signup,
  login,
  logout,
};

export default authService;
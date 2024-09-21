import api from "./api.js";
import {login} from "../redux/slices/authSlice.js";

export const registerUser = (registerData) => async (dispatch) => {
    try{
        const response = await api.post("/user/register", registerData);
        console.log("User registered successfully: ", response);
        if(response.status === 200){
           alert("User registered successfully");
           return true; // Return true if user registered successfully
        }
    }catch(error){
        console.log("User registered successfully: ", error);
    }
}

export const loginUser = (loginData) => async (dispatch) => {
    try{
        const response = await api.post("/user/authenticate", loginData);
        console.log("User logged in successfully: ", response.data);
        if(response.status === 200){
           alert("User logged in successfully");
           dispatch(login(response.data));
        }
    }catch(error){
        console.log("User logged in successfully: ", error);
    }
}
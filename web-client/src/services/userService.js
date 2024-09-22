import api from "./api.js";
import {login} from "../redux/slices/authSlice.js";
import {showAlert} from "../redux/slices/alertSlice.js";

export const registerUser = (registerData) => async (dispatch) => {
    try{
        const response = await api.post("/user/register", registerData);
        console.log("User registered successfully: ", response);
        if(response.status === 200){
           dispatch(showAlert(
               {
                   message: "User registered successfully",
                   title: "User Registration",
                   status: "success"
               }
           ));
           return true; // Return true if user registered successfully
        }
    }catch(error){
        console.log("User registration failed: ", error.response?.data);
        dispatch(showAlert(
            {
                message: "User registration failed. Retry",
                title: "User Registration",
                status: "error"
            }
        ));
        return false; // Return false if user registration
    }
}

export const loginUser = (loginData) => async (dispatch) => {
    try{
        const response = await api.post("/user/authenticate", loginData);
        console.log("User logged in successfully: ", response.data);
        if(response.status === 200){
            dispatch(showAlert(
                {
                    message: "User logged in successfully",
                    title: "User Login",
                    status: "success"
                }
            ));
           dispatch(login(response.data));
        }
    }catch(error){
        dispatch(showAlert(
            {
                message: "User login failed: " + error.response?.data,
                title: "User Login",
                status: "error"
            }
        ));
    }
}
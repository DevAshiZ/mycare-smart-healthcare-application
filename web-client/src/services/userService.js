import api from "./api.js";

export const registerUser = async (registerData) => {
    try{
        const response = await api.post("/user/register", registerData);
        console.log("User registered successfully: ", response);
        if(response.statusText === "OK"){
           alert("User registered successfully");
        }
    }catch(error){
        console.log("User registered successfully: ", error);
    }
}

export const loginUser = async (loginData) => {
    try{
        const response = await api.post("/user/authenticate", loginData);
        console.log("User logged in successfully: ", response.data);
        if(response.statusText === "OK"){
           alert("User logged in successfully");
        }
    }catch(error){
        console.log("User logged in successfully: ", error);
    }
}

import { createSlice } from '@reduxjs/toolkit';
import {jwtDecode} from "jwt-decode";

const initialState = {
    user: null,
    token: null,
    role: "",
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login(state, action) {
            const token = action.payload.token;
            state.role = action.payload.role;
            state.token = token;
            // Decode the token to extract the "sub" (user email or ID)
            const decodedToken = jwtDecode(token);
            state.user = decodedToken.sub;  // Assign "sub" value (email) to user
        },
        logout(state) {
            state.user = null;
            state.token = null;
        },
        clearToken(state) {
            state.token = null;
        }
    },
});

// Export actions
export const { login, logout, clearToken } = authSlice.actions;

// Export reducer
export default authSlice.reducer;

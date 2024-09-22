import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    message: '',
    title: '',
    status: '',
    show: false
};

const alertSlice = createSlice({
    name: 'alert',
    initialState,
    reducers: {
        showAlert(state, action) {
            state.message = action.payload.message;
            state.title = action.payload.title;
            state.status = action.payload.status;
            state.show = true;
        },
        hideAlert(state) {
            state.show = false;
            state.message = '';
            state.title = '';
            state.status = '';
        }
    }
});

// Export actions
export const { showAlert, hideAlert } = alertSlice.actions;

export default alertSlice.reducer;
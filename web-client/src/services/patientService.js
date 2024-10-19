import api from "./api.js";
import toast from "react-hot-toast";
import ERRORS from "../constants/error_messages.js";
import MESSAGES from "../constants/messages.js";

export const createAppointment = async (appointmentData) => {
    console.log('Appointment Data Sending: ', appointmentData);
    try{
        const response = await api.post('patient/create-appointment', appointmentData);
        toast.success(MESSAGES.APPOINTMENT_SUCCESS , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
        return response.data;
    }catch (error) {
        toast.error(ERRORS.APPOINTMENT_ERROR , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
        console.log(error);
    }
}

export const getAppointments = async (patientId) => {
    try{
        const response = await api.get(`patient/get-appointments?patientId=${patientId}`, {
            patientId
        })

        return response.data;
    }catch (error) {
        toast.error(ERRORS.APPOINTMENT_FETCH_ERROR , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
        console.log(error);
    }
}
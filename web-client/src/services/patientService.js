import api from "./api.js";
import toast from "react-hot-toast";
import MESSAGES from "../constants/messages.js";

export const createAppointment = async (appointmentData) => {
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
        toast.error(error.response?.data.message , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
    }
}

export const getAppointments = async (patientId) => {
    try{
        const response = await api.get(`patient/get-appointments?patientId=${patientId}`, {
            patientId
        })

        return response.data.data;
    }catch (error) {
        toast.error(error.response?.data.message , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
    }
}
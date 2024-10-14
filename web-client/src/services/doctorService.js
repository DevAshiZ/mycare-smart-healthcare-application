import toast from "react-hot-toast";
import api from "./api.js";
import ERRORS from "../constants/error_messages.js";
import MESSAGES from "../constants/messages.js";

export const getDoctorAvailability = async (doctorId, date) => {
    try{
        const response = await api.post('patient/doctor-availability', {
            doctorId: doctorId,
            date: date
        })
        return response.data;
    }catch (error) {
        toast.error(ERRORS.DOCTOR_AVAILABILITY_ERROR , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
        console.log(error);
    }
}

export const addNewDoctor = async (doctorData) => {
    try{
        const response = await api.post('/admin/doctor/register-doctor', doctorData)
        toast.success(MESSAGES.DOCTOR_REGISTRATION_SUCCESS , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });

        return response.data;
    }catch (error) {
        toast.error(ERRORS.ADD_DOCTOR_ERROR , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
        console.log(error);
    }
}
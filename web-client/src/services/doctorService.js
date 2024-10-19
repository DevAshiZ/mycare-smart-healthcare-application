import toast from "react-hot-toast";
import api from "./api.js";
import MESSAGES from "../constants/messages.js";

export const getDoctorAvailability = async (doctorId, date) => {
    try{
        const response = await api.post('patient/doctor-availability', {
            doctorId: doctorId,
            date: date
        })
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
        toast.error(error.response?.data.message , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
    }
}

export const getAllDoctors = async () => {
    try{
        const response = await api.get('/admin/doctor/get-all-doctors');
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

export const createPrescription = async (prescriptionData) => {
    try{
        const response = await api.post('/prescription/issue', prescriptionData);
        toast.success(MESSAGES.PRESCRIPTION_CREATED_SUCCESS , {
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

export const getDoctorAppointments = async (doctorId) => {
    try{
        const response = await api.get(`/admin/doctor/get-appointments?doctorId=${doctorId}`);
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
import api from "./api.js";
import toast from "react-hot-toast";
import MESSAGES from "../constants/messages.js";

export const createSchedule = async (schedule) => {
    try{
        const response = await api.post('/admin/schedule/add-schedule', schedule)
        toast.success(MESSAGES.SCHEDULE_ADD_SUCCESS , {
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

export const getSchedulesByDate = async (day) => {
    try{
        const response = await api.get(`/admin/schedule/get-schedules-by-day?day=${day}`);
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

export const getSchedulesByDoctor = async (doctorId) => {
    try{
        const response = await api.get(`/admin/schedule/get-schedules-by-doctor?doctorId=${doctorId}`);
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
import toast from "react-hot-toast";
import api from "./api.js";
import MESSAGES from "../constants/messages.js";

export const registerPharmacy = async (pharmacy) => {
    try{
        await api.post('/admin/pharmacy/register-pharmacy', pharmacy);
        toast.success(MESSAGES.PHARMACY_REGISTRATION_SUCCESS , {
            style: {
                borderRadius: '10px',
                background: '#333',
                color: '#fff',
            }
        });
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

export const getPharmacies = async () => {
    try{
        return await api.get('/admin/pharmacy/get-all-pharmacies')
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
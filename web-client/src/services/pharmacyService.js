import toast from "react-hot-toast";
import api from "./api.js";

export const registerPharmacy = async (pharmacy) => {
    try{
        await toast.promise(
            api.post('/admin/pharmacy/register-pharmacy', pharmacy),
            {
                loading: 'Registering pharmacy...',
                success: 'Pharmacy registered successfully!',
                error: 'Failed to register pharmacy'
            }
        )
    }catch (e) {
        toast.error('Failed to register pharmacy: ' + e.response?.message || e.message);
    }
}
import api from "./api.js";
import toast from "react-hot-toast";
import MESSAGES from "../constants/messages.js";

export const makeCashPayment = async (cashPaymentData) => {
    try {
        const response = await api.post("/patient/payment/pay-cash", cashPaymentData)
        toast.success(MESSAGES.PAYMENT_SUCCESS , {
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

export const makeCardPayment = async (cardPayment) => {
    try {
        const response = await api.post("/patient/payment/pay-card", cardPayment)
        toast.success(MESSAGES.PAYMENT_SUCCESS , {
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
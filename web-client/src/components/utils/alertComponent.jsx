import {
    faCheckCircle,
    faExclamationCircle,
    faX,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {useDispatch, useSelector} from "react-redux";
import {hideAlert} from "../../redux/slices/alertSlice.js";

export default function AlertComponent() {
    const {message, title, status, show} = useSelector((state) => state.alert);

    const dispatch = useDispatch();

    // Hide alert after 2 seconds
    if(show) {
        setTimeout(() => {
            dispatch(hideAlert());
        }, 2000);
    }
    const alert_color = () => {
        if (status === "success")
            return "bg-green-100 border-green-400 text-green-700";
        if (status === "error")
            return "bg-red-100 border-red-400 text-red-700";
        if (status === "warning")
            return "bg-yellow-100 border-yellow-400 text-yellow-700";
        return "bg-blue-100 border-blue-400 text-blue-700"; // Default case
    };

    // Only show alert if `alert_show` is true
    if (!show) return null;

    return (
        <div className="top-15 right-5 absolute z-50">
            <div
                className={`border ${alert_color()} px-4 py-3 rounded relative w-80`}
                role="alert"
            >
                <div className="items-center flex justify-between">
                    <div className="flex items-center gap-2">
                        <FontAwesomeIcon
                            icon={
                                status === "error" || status === "warning"
                                    ? faExclamationCircle
                                    : status === "success"
                                        ? faCheckCircle
                                        : null
                            }
                            className="text-md"
                        />
                        <strong className="font-bold ">{title}</strong>
                    </div>
                    <FontAwesomeIcon icon={faX} className="text-sm rounded-full" />
                </div>
                <span className="block sm:inline text-sm">{message}</span>
            </div>
        </div>
    );
}

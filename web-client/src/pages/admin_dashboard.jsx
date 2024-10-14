import AdminNavbar from "../components/admin/AdminNavbar.jsx";
import Summary from "../components/admin/Summary.jsx";

import PharmacyRegister from "../components/admin/PharmacyRegister.jsx";
import {Route, Routes} from "react-router-dom";
import {DoctorTab} from "../components/admin/doctor_tab.jsx";

const Admin_dashboard = () => {
    const styleSheet = {
        containerStyle: {
            display: "flex",
            height: "100vh",
        },
        sidebarStyle: {
            width: "20%",
        },
        contentStyle: {
            width: "80%",
            flexGrow: 1,
        }
    }

    return (
        <div style={styleSheet.containerStyle}>
            <div style={styleSheet.sidebarStyle}>
                <AdminNavbar/>
            </div>
            <div style={styleSheet.contentStyle}>
                <Routes>
                    <Route path="summary" element={<Summary/>}/>
                    <Route path="doctor-register" element={<DoctorTab/>}/>
                    <Route path="pharmacy-register" element={<PharmacyRegister/>}/>
                </Routes>
            </div>
        </div>
    );
};

export default Admin_dashboard;
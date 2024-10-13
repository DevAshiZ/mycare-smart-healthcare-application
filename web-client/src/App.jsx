import {BrowserRouter, Route, Routes, useLocation} from "react-router-dom";
import Home from "./pages/home.jsx";
import {NavigationBar} from "./components/common/navbar.jsx";
import {Footer} from "./components/common/footer.jsx";
import {Dashboard} from "./pages/dashboard.jsx";
import {PrivateRoute} from "./services/privateRoute.jsx";
import AlertComponent from "./components/utils/alertComponent.jsx";
import {DoctorDashboard} from "./pages/doctor_dashboard.jsx";
import {Profile} from "./pages/profile.jsx";
import AdminDashboard from "./pages/AdminDashboard.jsx";
import {Toaster} from "react-hot-toast";
import {PaymentPage} from "./pages/payment.jsx";

function AppContent() {
    const location = useLocation();
    const isAdminRoute = location.pathname.includes("/admin");

    return (
        <>
            <Toaster
                position="top-center"
                reverseOrder={false}
            />
            {!isAdminRoute && <NavigationBar/>}
            {!isAdminRoute && <AlertComponent/>}
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route element={<PrivateRoute/>}>
                    <Route path="/dashboard" element={<Dashboard/>}/>
                    <Route path="/doc" element={<DoctorDashboard/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                    <Route path="/admin/*" element={<AdminDashboard/>}/>
                    <Route path="/payment" element={<PaymentPage/>}/>
                </Route>
            </Routes>
            {!isAdminRoute && <Footer/>}
        </>
    );
}

function App() {
    return (
        <BrowserRouter>
            <AppContent/>
        </BrowserRouter>
    );
}

export default App;

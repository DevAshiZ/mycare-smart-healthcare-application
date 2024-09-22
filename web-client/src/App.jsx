import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/home.jsx";
import { NavigationBar } from "./components/common/navbar.jsx";
import { Footer } from "./components/common/footer.jsx";
import {Dashboard} from "./pages/dashboard.jsx";
import {PrivateRoute} from "./services/privateRoute.jsx";
import AlertComponent from "./components/utils/alertComponent.jsx";
import {DoctorDashboard} from "./pages/doctor_dashboard.jsx";
import {Profile} from "./pages/profile.jsx";

function App() {
  return (
    <BrowserRouter>
      <NavigationBar />
      <AlertComponent/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route element={<PrivateRoute/>}>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/doc" element={<DoctorDashboard />} />
            <Route path="/profile" element={<Profile />} />
        </Route>

      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;

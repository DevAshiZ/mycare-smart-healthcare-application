import React from "react";
import {
    Card,
    Typography,
    List,
    ListItem,
    ListItemPrefix,
    Accordion,
    AccordionHeader,
    AccordionBody,
} from "@material-tailwind/react";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faCalendar,
    faChevronDown,
    faChevronRight,
    faCog,
    faDashboard, faHandPaper,
    faInbox,
    faPowerOff,
} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";
import {logout} from "../../redux/slices/authSlice.js";
import {useDispatch} from "react-redux";

function AdminNavbar() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [open, setOpen] = React.useState(0);

    const handleOpen = (value) => {
        setOpen(open === value ? 0 : value);
    };
    const handleLogout = async () => {
        await dispatch(logout());
    }

    return (
        <div>
            <Card className="h-[calc(100vh)] w-full max-w-[20rem] p-4 shadow-xl shadow-blue-gray-900/5">
                <div className="mb-2 p-4 flex items-center gap-2 bg-green-50 rounded-lg border-2 border-green-200">
                    <Typography variant="h5" className={'text-green-500 font-bold text-2xl'}>
                        MyCare
                    </Typography>
                    <Typography  className={'text-gray-900 text-sm font-semibold'}>
                        Admin Panel
                    </Typography>
                </div>
                <List>
                    <Accordion
                        open={open === 1}
                        icon={
                            <FontAwesomeIcon icon={faChevronDown}
                                strokeWidth={2.5}
                                className={`mx-auto h-4 w-4 transition-transform ${open === 1 ? "rotate-180" : ""}`}
                            />
                        }
                    >
                        <ListItem className="p-0" selected={open === 1}>
                            <AccordionHeader onClick={() => handleOpen(1)} className="border-b-0 p-3">
                                <ListItemPrefix>
                                    <FontAwesomeIcon icon={faDashboard} className="h-4 w-4" />
                                </ListItemPrefix>
                                <Typography color="blue-gray" className="mr-auto text-sm">
                                    Registration Forms
                                </Typography>
                            </AccordionHeader>
                        </ListItem>
                        <AccordionBody className="py-1">
                            <List className="p-0">
                                <ListItem className={'text-sm text-gray-800'} onClick={()=> navigate("/admin/doctor-register")}>
                                    <ListItemPrefix>
                                        <FontAwesomeIcon icon={faChevronRight} strokeWidth={3} className="h-3 w-5" />
                                    </ListItemPrefix>
                                    Doctor
                                </ListItem>
                                <ListItem className={'text-sm text-gray-800'} onClick={ ()=> navigate("/admin/pharmacy-register")}>
                                    <ListItemPrefix>
                                        <FontAwesomeIcon icon={faChevronRight} strokeWidth={3} className="h-3 w-5" />
                                    </ListItemPrefix>
                                    Pharmacy
                                </ListItem>
                            </List>
                        </AccordionBody>
                    </Accordion>

                    <ListItem className={'text-sm text-gray-800'} onClick={()=> navigate("/admin/summary")}>
                        <ListItemPrefix>
                            <FontAwesomeIcon icon={faInbox} className="h-4 w-4" />
                        </ListItemPrefix>
                        Summary
                    </ListItem>
                    <ListItem className={'text-sm text-gray-800'} onClick={()=> navigate("/admin/schedule")}>
                        <ListItemPrefix>
                            <FontAwesomeIcon icon={faHandPaper} className="h-4 w-4" />
                        </ListItemPrefix>
                        Schedule
                    </ListItem>
                    <ListItem className={'text-sm text-gray-800'}>
                        <ListItemPrefix>
                            <FontAwesomeIcon icon={faCalendar} className="h-4 w-4" />
                        </ListItemPrefix>
                        Calender
                    </ListItem>
                    <ListItem className={'text-sm text-gray-800'}>
                        <ListItemPrefix>
                            <FontAwesomeIcon icon={faCog} className="h-4 w-4" />
                        </ListItemPrefix>
                        Settings
                    </ListItem>
                    <ListItem className={'text-sm text-gray-800'} onClick={handleLogout}>
                        <ListItemPrefix>
                            <FontAwesomeIcon icon={faPowerOff} className="h-4 w-4" />
                        </ListItemPrefix>
                        Log Out
                    </ListItem>
                </List>
            </Card>
        </div>
    )
}

export default AdminNavbar;

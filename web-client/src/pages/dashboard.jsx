import {
    Button,
    Card,
    CardBody,
    CardHeader,
    IconButton,
    Input,
    Select,
    Tooltip,
    Typography
} from "@material-tailwind/react";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faBrain, faChevronLeft, faChevronRight,
    faHeartbeat, faMessage, faNotesMedical,
    faSearch,
    faStar,
    faTooth,
    faUser
} from "@fortawesome/free-solid-svg-icons";
import PropTypes from "prop-types";
import {DayPicker} from "react-day-picker";
import React, {useEffect} from "react";
import {getAllDoctors} from "../services/doctorService.js";
import {getSchedulesByDoctor} from "../services/scheduleService.js";
import {formatTime,  addMinutes, getNextAppointmentDate} from "../utils/helper_functions.js";
import {useSelector} from "react-redux";
import {createAppointment} from "../services/patientService.js";
import {APPOINTMENT_DURATION} from "../configs/applicationConfigs.js";
import toast from "react-hot-toast";

export const PatientDashboard = () => {
    const [selectedDoctor, setSelectedDoctor] = React.useState(null);

    return (
        <div>
            <DashboardHeader/>
            <div className="flex gap-4 bg-gray-100 px-4 py-4">
                <div className="w-3/12">
                    {/*Doctor List*/}
                    <DoctorListSection setSelectedDoctor={setSelectedDoctor}/>

                </div>
                <div className="w-6/12">
                    {/*Doctor Details*/}
                    <DoctorDetailsSection selectedDoctor={selectedDoctor}/>

                </div>
                <div className="w-3/12">
                    {/*Calendar and Appointment*/}
                    <CalendarAndAppointmentSection selectedDoctor={selectedDoctor}/>

                </div>
            </div>
        </div>
    )
}
function DoctorListSection({setSelectedDoctor}) {

    const [doctors, setDoctors] = React.useState([]);

    useEffect(() => {
        const fetchDoctors = async () => {
            const response = await getAllDoctors();
            setDoctors(response);
        }
        fetchDoctors();
    }, []);

    return (
        <div className="h-screen overflow-auto p-2">
            {doctors.map((doctor, index) => (
                    <DoctorCard key={index} doctor={doctor} index={index} setSelectedDoctor={setSelectedDoctor}/>
                )
            )}
        </div>
    )
}

{/* Helper functions */}
function DoctorDetailsSection({selectedDoctor}) {
    return (
        <div>
            <Card className="w-full  flex-row mb-2">
                <CardHeader
                    shadow={false}
                    floated={false}
                    className="m-0 w-2/5 shrink-0 rounded-r-none"
                >
                    <img
                        src="https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-white-background_1368-5790.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid"
                        alt="doctor-img"
                        className="h-full w-full object-cover"
                    />
                </CardHeader>
                <CardBody>
                    <div className="mb-3 flex items-center justify-between">
                        <div>
                            <Typography variant="h5" color="blue-gray" className="font-semibold font-abril">
                                {selectedDoctor?.firstName} {selectedDoctor?.lastName}
                            </Typography>
                            <Typography color="gray" className="text-xs font-abril">
                                {selectedDoctor?.specialization}
                            </Typography>
                        </div>
                        <Typography
                            color="blue-gray"
                            className="flex items-center gap-1.5 font-semibold text-xs font-abril"
                        >
                            <FontAwesomeIcon icon={faStar} className="text-yellow-600"/>
                            4.5 (143 reviews)
                        </Typography>
                    </div>
                    <Typography color="gray" className="text-sm font-abril">
                        A highly experienced cardiologist dedicated to providing expert care in diagnosing and treating heart-related conditions.
                    </Typography>
                    <div className="group mt-5 grid grid-cols-4 gap-3">
                        <div className="flex flex-col items-center">
                            <IconButton className="rounded-full" variant="outlined">
                                <FontAwesomeIcon icon={faUser} className="w-4 h-4"/>
                            </IconButton>
                            <Typography className="text-sm mt-2 font-bold text-gray-800">{Math.floor(Math.random() * 200) + 1}+</Typography>
                            <Typography className="text-xs   text-gray-800">Followers</Typography>
                        </div>
                        <div className="flex flex-col items-center">
                            <IconButton className="rounded-full" variant="outlined">
                                <FontAwesomeIcon icon={faNotesMedical} className="w-4 h-4"/>
                            </IconButton>
                            <Typography className="text-sm mt-2 font-bold text-gray-800">{Math.floor(Math.random() * 10) + 1}+</Typography>
                            <Typography className="text-xs   text-gray-800">Years</Typography>
                        </div>
                        <div className="flex flex-col items-center">
                            <IconButton className="rounded-full" variant="outlined">
                                <FontAwesomeIcon icon={faStar} className="w-4 h-4"/>
                            </IconButton>
                            <Typography className="text-sm mt-2 font-bold text-gray-800">4.5 / 5</Typography>
                            <Typography className="text-xs   text-gray-800">Rating</Typography>
                        </div>
                        <div className="flex flex-col items-center">
                            <IconButton className="rounded-full" variant="outlined">
                                <FontAwesomeIcon icon={faMessage} className="w-4 h-4"/>
                            </IconButton>
                            <Typography className="text-sm mt-2 font-bold text-gray-800">{Math.floor(Math.random() * 60) + 1}k+</Typography>
                            <Typography className="text-xs   text-gray-800">Appointments</Typography>
                        </div>
                    </div>
                </CardBody>

            </Card>
        </div>
    )
}

function CalendarAndAppointmentSection({ selectedDoctor }) {
    const { userId } = useSelector((state) => state.auth);
    const [date, setDate] = React.useState(new Date());
    const [schedules, setSchedules] = React.useState([]);
    const [isScheduleAvailable, setIsScheduleAvailable] = React.useState(false);
    const [selectedSchedule, setSelectedSchedule] = React.useState({
        day: '',
        startTime: '',
        maxAppointments: 0,
    });

    const [appointment, setAppointment] = React.useState({
        appointmentLength: APPOINTMENT_DURATION,
        appointmentStart: '',
        patientId: parseInt(userId, 10),
        doctorId: '',
    });

    // When the schedule is selected, update the appointmentStart
    React.useEffect(() => {
        const nextAppointmentDate = getNextAppointmentDate(selectedSchedule);
        setAppointment(prev => ({
            ...prev,
            appointmentStart: nextAppointmentDate,
        }));
    }, [selectedSchedule]);

    const handleAppointmentSubmit = async () => {
        if (selectedDoctor !== null && selectedSchedule !== null) {
            setAppointment({
                ...appointment,
                doctorId: selectedDoctor.userId,
            });

            if (appointment.patientId !== null && appointment.doctorId !== null) {
                await createAppointment(appointment);
            }
        } else {
            toast.error("Please select a doctor and a schedule to book an appointment");
        }
    };

    useEffect(() => {
        const fetchSchedules = async () => {
            if (selectedDoctor !== null) {
                const response = await getSchedulesByDoctor(selectedDoctor.userId);
                if (response.data.length > 0) {
                    setSchedules(response.data);
                    setIsScheduleAvailable(true);
                } else {
                    setIsScheduleAvailable(false);
                    setSchedules([]);
                }
            } else {
                setSchedules([]);
            }
        };
        fetchSchedules();
    }, [selectedDoctor]);

    return (
        <div>
            <Card className="w-full h-screen p-2 items-center">
                <Typography variant="h6" className="font-semibold text-gray-800 mt-2">
                    Select Appointment Date
                </Typography>
                <div className="items-center mt-10">
                    <DayPicker
                        mode="single"
                        selected={date}
                        onSelect={setDate}
                        showOutsideDays
                        className="border-0"
                        classNames={{
                            month_caption: "flex justify-center py-2 mb-4 relative items-center",
                            caption_label: "text-sm font-medium text-gray-900",
                            nav: "flex items-center justify-between ",
                            button_previous: "",
                            button_next: "",
                            table: "w-full border-collapse",
                            weekdays: "m-0.5 w-9 font-normal text-sm text-gray-900",
                            row: "flex w-full mt-2 items-center justify-center",
                            cell: "text-gray-600 rounded-md h-9 w-9 text-center text-sm p-0 m-0.5 relative [&:has([aria-selected].day-range-end)]:rounded-r-md [&:has([aria-selected].day-outside)]:bg-gray-900/20 [&:has([aria-selected].day-outside)]:text-white [&:has([aria-selected])]:bg-gray-900/50 first:[&:has([aria-selected])]:rounded-l-md last:[&:has([aria-selected])]:rounded-r-md focus-within:relative focus-within:z-20",
                            day: "h-9 w-9 p-0 font-normal text-center text-sm text-gray-900 items-center justify-center",
                            range_end: "day-range-end",
                            selected:
                                "rounded-md bg-gray-900 text-white hover:bg-gray-900 hover:text-white focus:bg-gray-900 focus:text-white",
                            today: "rounded-md bg-gray-200 text-gray-900 ",
                            outside:
                                "day-outside text-gray-500 opacity-50 aria-selected:bg-gray-500 aria-selected:text-gray-900 aria-selected:bg-opacity-10",
                            disabled: "text-gray-500 opacity-50",
                            hidden: "invisible",
                        }}
                        components={{
                            IconLeft: ({ ...props }) => (
                                <FontAwesomeIcon icon={faChevronLeft} {...props} className="h-4 w-4 stroke-2" />
                            ),
                            IconRight: ({ ...props }) => (
                                <FontAwesomeIcon icon={faChevronRight} {...props} className="h-4 w-4 stroke-2" />
                            ),
                        }}
                    />
                </div>

                <div className="w-full mt-4 p-2">
                    <hr className="border border-b border-b-gray-200 mb-8" />
                    <div>
                        <Typography variant="h6" className="font-semibold text-gray-800 mb-2">
                            Available Appointment Schedules
                        </Typography>
                        {schedules.length > 0 ? (
                            <div className={'max-h-20 overflow-y-auto mb-5 p-4 py-2'}>
                                {schedules.map((schedule, index) => (
                                    <div key={index} className="grid grid-cols-2 gap-2">
                                        {Array.from({ length: schedule.maxAppointments }).map((_, i) => {
                                            const newStartTime = addMinutes(schedule.startTime, i * (APPOINTMENT_DURATION + 10));
                                            return (
                                                <div key={i} onClick={() => setSelectedSchedule( {
                                                    day: schedule.day,
                                                    startTime: newStartTime,
                                                    maxAppointments: schedule.maxAppointments,
                                                })}>
                                                    <Typography className={'bg-green-500 p-2 rounded-full text-white text-xs hover:bg-green-700 hover:cursor-pointer'}>
                                                        {formatTime(newStartTime)} - {formatTime(addMinutes(newStartTime, APPOINTMENT_DURATION))}
                                                    </Typography>
                                                </div>
                                            );
                                        })}
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <Typography className="mb-4 text-xs bg-red-500 p-2 text-white text-center">
                                No available schedules
                            </Typography>
                        )}
                    </div>
                    <div className="grid grid-cols-1 gap-2">
                        <Input label={selectedSchedule ? `${formatTime(selectedSchedule.startTime)} 
                        - ${formatTime(addMinutes(selectedSchedule.startTime, APPOINTMENT_DURATION))}`
                            : 'Select An Appointment Schedule'} disabled />
                        <Input label={selectedDoctor ? `Dr. ${selectedDoctor.firstName}` : 'Doctor Name'} disabled />
                        <Button onClick={handleAppointmentSubmit} disabled={!isScheduleAvailable} className={'bg-green-500'}>
                            Book Appointment
                        </Button>
                    </div>
                </div>
            </Card>
        </div>
    );
}

function DashboardHeader() {
    return (
        <div className="flex justify-between items-center px-6 py-4">
            <div className="grid grid-cols-2 gap-2">
                <Input label="Search" icon={<FontAwesomeIcon icon={faSearch}/>}/>
                <Select label="Rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </Select>
            </div>
            <div className="gap-2 flex">
                <Tooltip placement="bottom" content="Odontalogy">
                    <div className="items-center flex flex-col">
                        <IconButton>
                            <FontAwesomeIcon icon={faTooth}/>
                        </IconButton>
                    </div>
                </Tooltip>
                <Tooltip placement="bottom" content="Neurology">
                    <div className="items-center flex flex-col">
                        <IconButton>
                            <FontAwesomeIcon icon={faBrain}/>
                        </IconButton>
                    </div>
                </Tooltip>
                <Tooltip placement="bottom" content="Cardiology">
                    <div className="items-center flex flex-col">
                        <IconButton>
                            <FontAwesomeIcon icon={faHeartbeat}/>
                        </IconButton>
                    </div>
                </Tooltip>
            </div>
        </div>
    );
}

function DoctorCard({doctor, index , setSelectedDoctor}) {

    return (
        <Card key={index} className="h-[10rem] flex-row mb-2">
            <CardHeader
                shadow={false}
                floated={false}
                className="m-0 w-2/5 shrink-0 rounded-r-none"
            >
                <img
                    src={'https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-white-background_1368-5790.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid'}
                    alt={`${doctor.firstName}-image`}
                    className="h-full w-full object-cover"
                />
            </CardHeader>

            <div className="mt-2 ml-4 pr-2">
                <Typography variant="h5" color="blue-gray" className="font-semibold ">
                    {doctor.firstName} {doctor.lastName}
                </Typography>
                <Typography className="text-xs">{doctor.specialization}</Typography>
                <div className="flex items-center gap-2 mt-2 ">
                    <FontAwesomeIcon icon={faStar} className="text-yellow-600 " />
                    <Typography className="text-xs font-semibold text-gray-800">
                        {(Math.random() * 4 + 1).toFixed(1)} ({Math.floor(Math.random() * 5) + 10} reviews)
                    </Typography>
                </div>
                <div className={'mt-5 justify-center flex'}>
                    <Button className={'bg-green-500 rounded-lg'} size={"sm"} onClick={()=> setSelectedDoctor(doctor)}>Channel Doctor</Button>
                </div>
                {/*<div>*/}
                {/*    <Typography className="text-xs text-justify mt-2">*/}
                {/*        {doctor.description}*/}
                {/*    </Typography>*/}
                {/*</div>*/}
            </div>
        </Card>
    );
}

DoctorCard.propTypes = {
    doctor: PropTypes.shape({
        firstName: PropTypes.string.isRequired,
        lastName: PropTypes.string.isRequired,
        specialization: PropTypes.string.isRequired,
    }).isRequired,
    index: PropTypes.number.isRequired,
    setSelectedDoctor: PropTypes.func.isRequired,
};

DoctorListSection.propTypes = {
    setSelectedDoctor: PropTypes.func.isRequired,
};

DoctorDetailsSection.propTypes = {
    selectedDoctor: PropTypes.shape({
        firstName: PropTypes.string.isRequired,
        lastName: PropTypes.string.isRequired,
        specialization: PropTypes.string.isRequired,
        userId: PropTypes.number.isRequired,
    }),
};

CalendarAndAppointmentSection.propTypes = {
    selectedDoctor: PropTypes.shape({
        userId: PropTypes.number.isRequired,
        firstName: PropTypes.string.isRequired,
    }),
};



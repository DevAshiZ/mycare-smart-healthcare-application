import {useEffect, useState} from "react";
import {Button, Card, Input, Option, Select, Typography} from "@material-tailwind/react";
import {getAllDoctors} from "../../services/doctorService.js";
import toast from "react-hot-toast";
import {createSchedule, getSchedulesByDate} from "../../services/scheduleService.js";
import {formatTime} from "../../utils/helper_functions.js";

const WEEKDAYS = [
    { name: "Monday", value: "MON" },
    { name: "Tuesday", value: "TUE" },
    { name: "Wednesday", value: "WED" },
    { name: "Thursday", value: "THR" },
    { name: "Friday", value: "FRI" },
    { name: "Saturday", value: "SAT" },
    { name: "Sunday", value: "SUN" },
];

const TABLE_HEAD = ["Doctor", "Room", "Start Time", "Duration", "Max Appointments"];

export const ScheduleTab = () => {
    const [selectedTab, setSelectedTab] = useState("add-schedule");

    return (
        <div className={"p-4 bg-gray-100 h-screen"}>
            <Card className={"p-4"}>
                <Typography className={"text-lg text-green-500 font-semibold"}>
                    Schedule Section
                </Typography>
                <div className={"flex gap-5 mt-5"}>
                    <Typography
                        onClick={() => setSelectedTab("add-schedule")}
                        className={`text-sm text-gray-800 font-semibold hover:text-green-400 pb-2 ${
                            selectedTab === "add-schedule" && "border-b-4 border-green-400"
                        }`}
                    >
                        Add Schedule
                    </Typography>
                    <Typography
                        onClick={() => setSelectedTab("view-schedule")}
                        className={`text-sm text-gray-800 font-semibold hover:text-green-400 pb-2 ${
                            selectedTab === "view-schedule" && "border-b-4 border-green-400"
                        }`}
                    >
                        View Schedule
                    </Typography>
                </div>
            </Card>

            {selectedTab === "add-schedule" && <AddSchedule />}
            {selectedTab === "view-schedule" && <ViewSchedule />}

        </div>
    );
};

const ViewSchedule = () => {

    const [selectedDate, setSelectedDate] = useState("MON");
    const [schedules, setSchedules] = useState([]);

    useEffect(() => {
        // Fetch schedules by date
        const fetchSchedules = async () => {
            const response = await getSchedulesByDate(selectedDate);
            console.log(response.data);
            setSchedules(response.data);
        }

        fetchSchedules();
    }, [selectedDate]);


    return (
        <div>
            <Card className={"p-4 mt-2"}>
                <div className={"grid grid-cols-7 gap-2 text-center"}>
                    {WEEKDAYS.map((WEEKDAY) => (
                        <div key={WEEKDAY.value}>
                            <Typography
                                onClick={() => setSelectedDate(WEEKDAY.value)}
                                className={`text-gray-900 hover:text-green-400 font-semibold text-sm pb-2  ${
                                    selectedDate === WEEKDAY.value && "border-b-4 border-green-400"
                                }`}
                            >
                                {WEEKDAY.name}
                            </Typography>
                        </div>
                    ))}
                </div>
            </Card>
            <Card className={'mt-4'}>
                <table className="w-full min-w-max table-auto text-left">
                    <thead>
                    <tr>
                        {TABLE_HEAD.map((head) => (
                            <th
                                key={head}
                                className="border-b border-blue-gray-100 bg-blue-gray-50 p-4"
                            >
                                <Typography
                                    variant="small"
                                    color="blue-gray"
                                    className="font-normal leading-none opacity-70"
                                >
                                    {head}
                                </Typography>
                            </th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {schedules.length > 0 ? schedules.map((schedule, index) => {
    const isLast = index === schedules.length - 1;
    const classes = isLast
        ? "p-4"
        : "p-4 border-b border-blue-gray-50";

    return (
        <tr key={index}>
            <td className={classes}>
                <Typography variant="small" color="blue-gray" className="font-normal">
                    {schedule.doctorId}
                </Typography>
            </td>
            <td className={classes}>
                <Typography variant="small" color="blue-gray" className="font-normal">
                    {schedule.room}
                </Typography>
            </td>
            <td className={classes}>
                <Typography variant="small" color="blue-gray" className="font-normal">
                    {formatTime(schedule.startTime)}
                </Typography>
            </td>
            <td className={classes}>
                <Typography variant="small" color="blue-gray" className="font-normal">
                    {schedule.duration}
                </Typography>
            </td>
            <td className={classes}>
                <Typography variant="small" color="blue-gray" className="font-normal">
                    {schedule.maxAppointments}
                </Typography>
            </td>
        </tr>
    );
}) : (
    <tr>
        <td colSpan={5} className="p-4">
            <Typography variant="small" color="blue-gray" className="font-normal">
                No schedules available
            </Typography>
        </td>
    </tr>
)}
                    </tbody>
                </table>
            </Card>
        </div>
    )
}

const AddSchedule = () => {

    const [doctors, setDoctors] = useState([]);

    const [scheduleData, setScheduleData] = useState({
        doctorId: "",
        day: "",
        startTime: "",
        duration: "",
        maxAppointments: "",
        room: "",
    });

    const handleAddSchedule = async () => {
        if(Object.values(scheduleData).some((item) => item === "")){
            toast.error('Please fill all the fields');
            return;
        }

        setScheduleData({
            ...scheduleData,
            duration: parseInt(scheduleData.duration, 10) * 60,
        })

        await createSchedule(scheduleData);
    }

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setScheduleData({ ...scheduleData, [name]: value });
    }

    // Fetch all doctors
    useEffect(  () => {
        const fetchDoctors = async () => {
            const response = await getAllDoctors();
            setDoctors(response);
        }

        fetchDoctors();
    }, []);

    return (
        <Card className={"p-4 mt-2"}>
            <Typography className={"text-md text-green-500 font-semibold"}>
                Add Schedule
            </Typography>

            <div className={'flex justify-between'}>
                <div className={'flex flex-col gap-2 w-1/2 mt-4'}>
                    <Select label={'Doctor'} name={'doctorId'} onChange={(value) =>
                        setScheduleData({...scheduleData, doctorId: value})
                    }>
                        {doctors.map((doctor, index) => (
                            <Option key={index} value={doctor.userId}>{doctor.firstName} {doctor.lastName}</Option>
                        ))}
                    </Select>
                    <Select label={'Day'} onChange={(value)=> setScheduleData({...scheduleData, day: value})}>
                        <Option value={'MON'}>Monday</Option>
                        <Option value={'TUE'}>Tuesday</Option>
                        <Option value={'WED'}>Wednesday</Option>
                        <Option value={'THR'}>Thursday</Option>
                        <Option value={'FRI'}>Friday</Option>
                        <Option value={'SAT'}>Saturday</Option>
                        <Option value={'SUN'}>Sunday</Option>
                    </Select>
                    <div className={'grid-cols-2 grid gap-2'}>
                        <Input onChange={handleInputChange} type={"time"} name={'startTime'} label={'Start Time'} />
                        <Input onChange={handleInputChange} type={"text"} name={'duration'} label={'Duration'} />
                    </div>
                    <Input onChange={handleInputChange} name={'room'} label={'Room'} />
                    <Input onChange={handleInputChange} name={'maxAppointments'} label={'Max Appointments'} type={'number'} />
                    <Button onClick={handleAddSchedule} className={'mt-4'}>Add Schedule</Button>

                </div>
                <div className={'mr-10'}>
                    <img
                        className={'w-96'}
                        src={'https://img.freepik.com/free-vector/health-professional-team_23-2148479239.jpg?t=st=1728876735~exp=1728880335~hmac=0c324e2282e062c9c2023598cdaedd19aaaae8e396084a83e4ebc352b02ede97&w=996'}
                        alt={'doctor'}/>
                </div>
            </div>
        </Card>
    )
}

import { useState } from "react";
import { Card, Typography } from "@material-tailwind/react";

const WEEKDAYS = [
    { name: "Monday", value: "MON" },
    { name: "Tuesday", value: "TUE" },
    { name: "Wednesday", value: "WED" },
    { name: "Thursday", value: "THR" },
    { name: "Friday", value: "FRI" },
    { name: "Saturday", value: "SAT" },
    { name: "Sunday", value: "SUN" },
];

const TABLE_HEAD = ["Time", "Subject", "Doctor", "Room"];
const TABLE_ROWS = [
    {
        Time: "09:00 AM",
        Day: "MON",
        Subject: "General Checkup",
        Doctor: "Dr. Smith",
        Room: "101",
    },
    {
        Time: "10:30 AM",
        Day: "MON",
        Subject: "Cardiology Consultation",
        Doctor: "Dr. Adams",
        Room: "203",
    },
    {
        Time: "11:15 AM",
        Day: "TUE",
        Subject: "Dermatology Follow-up",
        Doctor: "Dr. Kim",
        Room: "305",
    },
    {
        Time: "12:00 PM",
        Day: "WED",
        Subject: "Pediatrics Consultation",
        Doctor: "Dr. Williams",
        Room: "102",
    },
    {
        Time: "01:30 PM",
        Day: "THU",
        Subject: "Orthopedics Checkup",
        Doctor: "Dr. Johnson",
        Room: "204",
    },
    {
        Time: "02:15 PM",
        Day: "FRI",
        Subject: "Neurology Exam",
        Doctor: "Dr. Lee",
        Room: "306",
    },
    {
        Time: "03:00 PM",
        Day: "MON",
        Subject: "Allergy Test",
        Doctor: "Dr. Martinez",
        Room: "105",
    },
    {
        Time: "03:45 PM",
        Day: "TUE",
        Subject: "Pulmonology Consultation",
        Doctor: "Dr. Brown",
        Room: "207",
    },
    {
        Time: "04:30 PM",
        Day: "WED",
        Subject: "ENT Consultation",
        Doctor: "Dr. Allen",
        Room: "108",
    },
    {
        Time: "05:15 PM",
        Day: "THU",
        Subject: "Arthritis Follow-up",
        Doctor: "Dr. Rodriguez",
        Room: "308",
    },
];

export const ScheduleTab = () => {
    const [selectedTab, setSelectedTab] = useState("view-schedule");
    const [selectedDate, setSelectedDate] = useState("MON");

    // Filter schedules based on the selected date
    const filteredSchedules = TABLE_ROWS.filter(
        (row) => row.Day === selectedDate
    );

    return (
        <div className={"p-4 bg-gray-100 h-screen"}>
            <Card className={"p-4"}>
                <Typography className={"text-lg text-green-500 font-semibold"}>
                    Schedule Section
                </Typography>
                <div className={"flex gap-2 mt-5"}>
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
                    {filteredSchedules.map((row, index) => {
                        const isLast = index === filteredSchedules.length - 1;
                        const classes = isLast
                            ? "p-4"
                            : "p-4 border-b border-blue-gray-50";

                        return (
                            <tr key={index}>
                                <td className={classes}>
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {row.Time}
                                    </Typography>
                                </td>
                                <td className={classes}>
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {row.Subject}
                                    </Typography>
                                </td>
                                <td className={classes}>
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {row.Doctor}
                                    </Typography>
                                </td>
                                <td className={classes}>
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {row.Room}
                                    </Typography>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </Card>
        </div>
    );
};

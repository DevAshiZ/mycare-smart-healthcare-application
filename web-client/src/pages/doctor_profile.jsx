import {DayPicker} from "react-day-picker";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faChevronLeft,
    faChevronRight,
    faMessage,
    faNotesMedical,
    faStar,
    faUser
} from "@fortawesome/free-solid-svg-icons";
import React from "react";
import {Avatar, Card, CardBody, CardHeader, Chip, IconButton, Typography} from "@material-tailwind/react";

export const DoctorProfile = () => {

    const [date, setDate] = React.useState(new Date());
    return (
        <div className="w-full bg-gray-100">

            <div className="flex w-full px-4 justify-between gap-2 h-full pt-4 pb-4">
               <div className="w-9/12">
                   <DoctorDetailsSection/>
                   <PatientListSection/>
               </div>
                <Card className="w-3/12 p-2 items-center h-screen">
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
                                nav: "flex items-center items-center justify-between ",
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
                                IconLeft: ({...props}) => (
                                    <FontAwesomeIcon icon={faChevronLeft} {...props} className="h-4 w-4 stroke-2"/>
                                ),
                                IconRight: ({...props}) => (
                                    <FontAwesomeIcon icon={faChevronRight} {...props} className="h-4 w-4 stroke-2"/>
                                ),
                            }}
                        />
                    </div>
                </Card>
            </div>
        </div>
    )
}

function DoctorDetailsSection() {
    return (
        <div >
            <Card className="w-full h-[15rem] flex-row mb-2">
                <CardHeader
                    shadow={false}
                    floated={false}
                    className="m-0 w-2/5 shrink-0 rounded-r-none"
                >
                    <img
                        src="https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-white-background_1368-5790.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid"
                        alt="doctor-img"
                        className="h-full w-full object-contain"
                    />
                </CardHeader>
                <CardBody>
                    <div className="mb-3 flex items-center justify-between">
                        <div>
                            <Typography variant="h5" color="blue-gray" className="font-semibold font-abril">
                                Dr. John Doe
                            </Typography>
                            <Typography color="gray" className="text-xs font-abril">
                                Cardiologist
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
                            <Typography className="text-sm mt-2 font-bold text-gray-800">116+</Typography>
                            <Typography className="text-xs   text-gray-800">Followers</Typography>
                        </div>
                        <div className="flex flex-col items-center">
                            <IconButton className="rounded-full" variant="outlined">
                                <FontAwesomeIcon icon={faNotesMedical} className="w-4 h-4"/>
                            </IconButton>
                            <Typography className="text-sm mt-2 font-bold text-gray-800">3+</Typography>
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
                            <Typography className="text-sm mt-2 font-bold text-gray-800">96+</Typography>
                            <Typography className="text-xs   text-gray-800">Appointments</Typography>
                        </div>
                    </div>
                </CardBody>

            </Card>
        </div>
    )
}

function PatientListSection() {
    return (
        <div className="w-full">
            <Card className="w-full h-[15rem]">
                <CardBody>
                    <div className="">
                        <Typography variant="h5" color="blue-gray" className="font-semibold font-abril mb-2">
                            Patients List
                        </Typography>

                        <div className="grid grid-cols gap-2">
                            <PatientCard/>
                            <PatientCard/>
                            <PatientCard/>
                        </div>
                    </div>
                </CardBody>
            </Card>
        </div>
    )
}

function PatientCard() {
    return (
        <div className="flex items-center">
            <Avatar
                src={"https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-white-background_1368-5790.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid"}/>
            <div className="ml-2">
                <Typography className="font-semibold font-abril text-gray-800 text-sm">John Doe</Typography>
                <Typography className="font-abril text-xs">john@gmail.com</Typography>
            </div>
            <Chip value="9 A.M" color="yellow" className="ml-4"/>
        </div>
    )
}



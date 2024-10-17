import {Card, Typography} from "@material-tailwind/react";
import {useState} from "react";


const TABLE_HEAD = ["Condition", "Date", "Doctor", "Medications"];

const TABLE_ROWS = [
    {
        "Patient Name": "John Doe",
        Condition: "Fever",
        Date: "12/10/23",
        Doctor: "Dr. Smith",
        Medications: "Paracetamol",
        "Follow-up Date": "19/10/23",
        Actions: "Edit",
    },
    {
        "Patient Name": "John Doe",
        Condition: "Hypertension",
        Date: "08/07/23",
        Doctor: "Dr. Adams",
        Medications: "Amlodipine",
        "Follow-up Date": "08/08/23",
        Actions: "Edit",
    },
    {
        "Patient Name": "John Doe",
        Condition: "Diabetes",
        Date: "22/03/23",
        Doctor: "Dr. Kim",
        Medications: "Metformin",
        "Follow-up Date": "22/04/23",
        Actions: "Edit",
    },
    {
        "Patient Name": "John Doe",
        Condition: "Asthma",
        Date: "15/06/23",
        Doctor: "Dr. Williams",
        Medications: "Albuterol Inhaler",
        "Follow-up Date": "15/09/23",
        Actions: "Edit",
    },
    {
        "Patient Name": "John Doe",
        Condition: "Migraine",
        Date: "05/02/23",
        Doctor: "Dr. Lee",
        Medications: "Ibuprofen",
        "Follow-up Date": "05/03/23",
        Actions: "Edit",
    },
    {
        "Patient Name": "John Doe",
        Condition: "Allergic Reaction",
        Date: "11/11/22",
        Doctor: "Dr. Martinez",
        Medications: "Antihistamine",
        "Follow-up Date": "18/11/22",
        Actions: "Edit",
    },

    {
        "Patient Name": "John Doe",
        Condition: "COVID-19",
        Date: "14/04/21",
        Doctor: "Dr. Allen",
        Medications: "Rest, Vitamins",
        "Follow-up Date": "28/04/21",
        Actions: "Edit",
    },
    {
        "Patient Name": "John Doe",
        Condition: "Arthritis",
        Date: "17/09/23",
        Doctor: "Dr. Rodriguez",
        Medications: "Ibuprofen",
        "Follow-up Date": "17/10/23",
        Actions: "Edit",
    }
];


export const Profile = () => {
    return (
        <div className={'bg-gray-100 flex h-screen gap-4 p-4'}>
            <UserDetailsSection/>
            <UserRecordsSection/>

        </div>
    )
}

const UserDetailsSection = () => {
    return (
        <div className={'w-1/4 h-full'}>
            {/*profile details section*/}
            <Card className={'p-4'}>
                <div>
                    <img
                        src={'https://th.bing.com/th/id/OIP.D9EMgYpJhawCjWVdQoG_jAHaHa?w=500&h=500&rs=1&pid=ImgDetMain'}
                        alt={'profile'} className={'rounded-lg'}/>
                </div>
                <div className={'mt-5 flex justify-between'}>

                    <div>
                        <Typography className={'text-xl font-bold text-gray-800'}>John Doe</Typography>
                        <Typography className={'text-xs font-bold text-gray-800'}>john@example.com</Typography>
                    </div>
                    <Typography className={'text-sm font-bold text-gray-800'}>@John</Typography>
                </div>
                <div className={'mt-2'}>
                    <Typography className={'text-sm  font-bold text-gray-800'}>Age: 22</Typography>
                </div>

            </Card>

        </div>
    )
}

const UserRecordsSection = () => {

    const [currentTab, setCurrentTab] = useState('medical-records');

    return (
        <div className={'w-3/4 h-full'}>
            {/*reservations and stuff*/}
            <Card className={'p-4'}>
                <div className={'flex gap-4 pb-5 border-b border-gray-200'}>
                    <Typography
                        onClick={()=> setCurrentTab('medical-records')}
                        className={`text-sm font-bold text-gray-800 hover:text-green-500 hover:border-green-500 ${currentTab === 'medical-records' && 'text-green-600 border-b-2 border-green-600'}`}>
                        Medical Records
                    </Typography>
                    <Typography
                        onClick={()=> setCurrentTab('payment-records')}
                        className={`text-sm font-bold text-gray-800 hover:text-green-500 hover:border-green-500 ${currentTab === 'payment-records' && 'text-green-600 border-b-2 border-green-600'}`}>
                        Recent Payments
                    </Typography>
                </div>

                <div className={'mt-4'}>
                    {currentTab === 'medical-records' && <MedicalRecords/>}
                    {currentTab === 'payment-records' && <PaymentRecords/>}
                </div>
            </Card>
        </div>
    )
}

const MedicalRecords = () => {
    return (
        <div>
            <Typography className={'text-lg font-bold text-gray-800'}>Medical Records</Typography>

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
                {TABLE_ROWS.map((row, index) => {
                    const isLast = index === TABLE_ROWS.length - 1;
                    const classes = isLast ? "p-4" : "p-4 border-b border-blue-gray-50";

                    return (
                        <tr key={name}>
                            <td className={classes}>
                                <Typography
                                    variant="small"
                                    color="blue-gray"
                                    className="font-normal"
                                >
                                    {row.Condition}
                                </Typography>
                            </td>
                            <td className={classes}>
                                <Typography
                                    variant="small"
                                    color="blue-gray"
                                    className="font-normal"
                                >
                                    {row.Date}
                                </Typography>
                            </td>
                            <td className={classes}>
                                <Typography
                                    variant="small"
                                    color="blue-gray"
                                    className="font-normal"
                                >
                                    {row.Doctor}
                                </Typography>
                            </td>
                            <td className={classes}>
                                <Typography
                                    variant="small"
                                    color="blue-gray"
                                    className="font-normal"
                                >
                                    {row.Medications}
                                </Typography>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
            {/*<div className={'mt-5'}>*/}
            {/*    <Typography className={'text-xs font-normal text-gray-800'}>No records found</Typography>*/}
            {/*</div>*/}
        </div>
    )
}

const PaymentRecords = () => {
    return (
        <div>
            <Typography className={'text-lg font-bold text-gray-800'}>Recent Payments</Typography>
            <div className={'mt-5'}>
                <Typography className={'text-xs font-normal  text-gray-800'}>No payments found</Typography>
            </div>
        </div>
    )
}



import {Card, Typography} from "@material-tailwind/react";
import {useState} from "react";

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
                        src={'https://img.freepik.com/free-vector/gradient-avatar-illustration_52683-142438.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid'}
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
        <div >
            <Typography className={'text-lg font-bold text-gray-800'}>Medical Records</Typography>
            <div className={'mt-5'}>
                <Typography className={'text-xs font-normal text-gray-800'}>No records found</Typography>
            </div>
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



import {useEffect, useState} from "react";
import {Button, Card, Input, Option, Select, Typography} from "@material-tailwind/react";
import {addNewDoctor, getAllDoctors} from "../../services/doctorService.js";
import toast from "react-hot-toast";

const specializations = [
    "Cardiologist (Heart Specialist)",
    "Dermatologist (Skin Specialist)",
    "Neurologist (Brain and Nervous System Specialist)",
    "Pediatrician (Children's Health Specialist)",
    "Gynecologist (Women's Health Specialist)",
    "Oncologist (Cancer Specialist)",
    "Orthopedic Surgeon (Bone and Joint Specialist)",
    "Psychiatrist (Mental Health Specialist)",
    "Endocrinologist (Hormone and Gland Specialist)",
    "Gastroenterologist (Digestive System Specialist)",
    "Pulmonologist (Lung and Respiratory Specialist)",
    "Urologist (Urinary System Specialist)",
    "Ophthalmologist (Eye Specialist)",
    "Rheumatologist (Joint and Autoimmune Disease Specialist)",
    "Nephrologist (Kidney Specialist)",
    "Allergist/Immunologist (Allergy and Immune System Specialist)",
    "Otolaryngologist (ENT Specialist)",
    "Hematologist (Blood Specialist)",
    "Plastic Surgeon (Reconstructive and Cosmetic Surgery Specialist)",
    "Anesthesiologist (Pain Management and Anesthesia Specialist)"
];

export const DoctorTab = () => {

    const [selectedTab, setSelectedTab] = useState('register-doctor');

    return (
        <div className={'p-4 bg-gray-100 h-screen'}>
            <Card className={'p-4'}>
                <Typography className={'text-lg text-green-500 font-semibold'}>Doctor Section</Typography>

                <div className={'flex gap-5 mt-5'}>
                    <Typography onClick={()=>setSelectedTab('register-doctor')} className={`text-sm text-gray-800 font-semibold hover:text-green-400 pb-2 ${selectedTab === 'register-doctor' && 'border-b-4 border-green-400'}`}>Register Doctor</Typography>
                    <Typography onClick={()=>setSelectedTab('view-doctors')} className={`text-sm text-gray-800 font-semibold hover:text-green-400 pb-2 ${selectedTab === 'view-doctors' && 'border-b-4 border-green-400'}`}>View Doctors</Typography>
                </div>
            </Card>
            <div className={'w-full flex gap-5 mt-4'}>
                {selectedTab === 'register-doctor' && <RegisterDoctor/>}
                {selectedTab === 'view-doctors' && <ViewDoctors/>}
            </div>
        </div>
    );
}


const RegisterDoctor = () => {

    const [doctorRegistrationData, setDoctorRegistrationData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        registrationNumber: '',
        specialization: ''
    });

    const handleDoctorRegistrationOnChange = (e) => {
        setDoctorRegistrationData({
            ...doctorRegistrationData,
            [e.target.name]: e.target.value
        });
    }

    const handleDoctorRegistration = async () => {
        if(!doctorRegistrationData.firstName || !doctorRegistrationData.lastName || !doctorRegistrationData.email || !doctorRegistrationData.password || !doctorRegistrationData.registrationNumber || !doctorRegistrationData.specialization){
            toast.error('Please fill all the fields');
            return;
        }
        const response = await addNewDoctor(doctorRegistrationData);
        console.log(response);
    }

    return (
        <Card className={'w-full p-4'}>
            <div className={'mb-4'}>
                <Typography className={'text-lg text-gray-800 font-semibold'}>Register Doctor</Typography>
            </div>
            <div className={'flex justify-between'}>

                <div className={'flex flex-col gap-2 w-1/2'}>
                    <div className={'grid-cols-2 grid gap-2'}>
                        <Input name={'firstName'} label={'First Name'} onChange={handleDoctorRegistrationOnChange}/>
                        <Input name={'lastName'} label={'Last Name'} onChange={handleDoctorRegistrationOnChange}/>
                    </div>
                    <Input name={'email'} label={'Email'} type={'email'} onChange={handleDoctorRegistrationOnChange}/>
                    <Input name={'password'} label={'Password'} type={'password'} onChange={handleDoctorRegistrationOnChange}/>
                    <Input name={'registrationNumber'} label={'Doctor Registration Number'} onChange={handleDoctorRegistrationOnChange}/>
                   <Select label={'Specialization'} name={'specialization'} onChange={(value) =>
                       setDoctorRegistrationData({ ...doctorRegistrationData, specialization: value })
                   }>
                        {specializations.map((specialization, index) => (
                            <Option key={index} value={specialization}>{specialization}</Option>
                        ))}
                   </Select>
                    <Button onClick={handleDoctorRegistration} className={'mt-4'}>Add Doctor</Button>
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

const ViewDoctors = () => {

    const TABLE_HEAD = ["Registration Number","Name", "Specialization", "Email"];
    const [doctors, setDoctors] = useState([{

    }]);

    useEffect(() => {
        const fetchDoctors = async () => {
            const response = await getAllDoctors();
            setDoctors(response);
            console.log(doctors);
        }
        fetchDoctors();
    }, []);
    return (
        <Card className={'w-full p-4'}>
            <div className={'mb-4'}>
                <Typography className={'text-lg text-gray-800 font-semibold'}>All Doctors</Typography>
            </div>
            <div>
                <Card className="h-full w-full overflow-scroll">
                    {doctors.length > 0 ? (   <table className="w-full min-w-max table-auto text-left">
                        <thead>
                        <tr>
                            {TABLE_HEAD.map((head) => (
                                <th key={head} className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
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
                        {doctors.map((doctor, index)  => (
                            <tr key={index} className="even:bg-blue-gray-50/50">
                                <td className="p-4">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {doctor.registrationNumber || 'N/A'}
                                    </Typography>
                                </td>
                                <td className="p-4">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {doctor.firstName} {doctor.lastName}
                                    </Typography>
                                </td>
                                <td className="p-4">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {doctor.specialization}
                                    </Typography>
                                </td>
                                <td className="p-4">
                                    <Typography as="a" href="#" variant="small" color="blue-gray" className="font-medium">
                                        {doctor.email}
                                    </Typography>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>) : (
                        <Typography className={'text-gray-500'}>No Doctors Found</Typography>
                    )}
                </Card>
            </div>
        </Card>
    )
}


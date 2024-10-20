import {Button, Card,  Input, Option, Select, Textarea, Typography} from "@material-tailwind/react";
import {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPlusCircle,  faX} from "@fortawesome/free-solid-svg-icons";
import PropTypes from "prop-types";
import {createPrescription, getDoctorAppointments} from "../services/doctorService.js";
import {useSelector} from "react-redux";
import {formatDateWithTime} from "../utils/helper_functions.js";


const TABLE_HEADERS = [
    'Medication Name',
    'Dosage',
    'Frequency',
    'Duration',
    'Instructions'
]

export const DoctorDashboard = () => {
    const [medicines, setMedicines] = useState([]);

    const [selectedAppointment, setSelectedAppointment] = useState(null);

    return (
        <div className={'bg-gray-100 h-screen p-4'}>
            <Card className={''}>
                <Typography className={'text-center p-2 text-gray-800 font-semibold'}>Doctor Dashboard</Typography>
            </Card>
            <div className={'flex mt-5 gap-2'}>
                <div className={'w-1/3'}>
                    <MainContent
                        medicines={medicines}
                        setMedicines={setMedicines}
                        selectedAppointment={selectedAppointment}
                        setSelectedAppointment={setSelectedAppointment}
                    />
                </div>
                <div className={'w-2/3'}>
                    <PrescriptionList medicines={medicines} selectedAppointment={selectedAppointment}/>
                </div>
            </div>
        </div>
    )
}

const MainContent = ({medicines, setMedicines, selectedAppointment, setSelectedAppointment}) => {
    const [selectedTab, setSelectedTab] = useState('prescription')
    return(
        <Card className={'p-4 h-full'}>
            {/*tab navigation*/}
            <div className={'mt-2 flex flex-row gap-4'}>
                <Typography onClick={()=>setSelectedTab('prescription')} className={`pb-2 text-sm font-semibold text-gray-800 hover:text-green-400 ${selectedTab === 'prescription' && 'border-b-4 border-green-400 text-green-400'}`}>Create Prescription</Typography>
                <Typography onClick={()=>setSelectedTab('patient-history')} className={`pb-2 text-sm font-semibold text-gray-800 hover:text-green-400 ${selectedTab === 'patient-history' && 'border-b-4 border-green-400 text-green-400'}`}>View Appointments</Typography>
            </div>
            {/*tab content*/}
            <div>
                {selectedTab === 'prescription' && <Prescription medicines={medicines} setMedicines={setMedicines}/>}
                {selectedTab === 'patient-history' && <Patients setSelectedAppointment={setSelectedAppointment} selectedAppointment={selectedAppointment}/>}
            </div>
        </Card>
    )
}

const Prescription = ({medicines,setMedicines }) => {
    return(
        <div className={'mt-4'}>
            <Typography className={'text-gray-800 font-semibold'}>Create Prescription: <span className={'text-green-600'}>PatientName</span></Typography>
            <div className={'mt-2'}>
              <div className={'flex gap-2'}>
                  {medicines && medicines.map((medicine, index)  => {
                      return (
                          <div key={index} className={'flex gap-2 items-center px-4 p-2 mb-2 border-2 border-gray-200 rounded-full w-44 justify-between'}>
                              <Typography className={'text-xs text-gray-800 font-semibold hover:text-green-400 cursor-pointer'}>{medicine.medicineName}</Typography>
                              <FontAwesomeIcon icon={faX} className={'text-red-600 w-3 h-3 cursor-pointer'} onClick={() => {
                                  setMedicines(medicines.filter((_, i) => i !== index));
                              }}/>
                          </div>
                      )
                  })}
              </div>
                <div
                    className={'w-48 flex items-center gap-2 text-gray-800 p-2 border-dotted border-2 border-gray-900 rounded-full group hover:bg-green-200 hover:text-gray-900'}>
                    <FontAwesomeIcon icon={faPlusCircle}/>
                    <Typography className={'text-sm'}>Add New Medicine</Typography>
                </div>
                <MedicineForm setMedicines={setMedicines} medicines={medicines}/>
            </div>
        </div>
    )
}

const MedicineForm = ({setMedicines, medicines}) => {

    const [medicine, setMedicine] = useState({
        medicineName: '',
        dosage: '',
        frequency: '',
        duration: '',
        instructions: ''
    });

    const handleAddMedicine = () => {
        setMedicines([...medicines, medicine]);
        setMedicine({
            medicineName: '',
            dosage: '',
            frequency: '',
            duration: '',
            instructions: ''
        })
    }

    const handleChange = (e) => {
        setMedicine({
            ...medicine,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div className={'flex flex-col gap-2 mt-4'}>
            <Input onChange={handleChange} label={'Medication Name'} name={'medicineName'}/>
            <Input onChange={handleChange} label={'Dosage'} name={'dosage'}/>

            <Select label={'Frequency'} name={'frequency'} onChange={(value) =>
                setMedicine({ ...medicine, frequency: value })
            }>
                <Option  value={'Once A Day'}>
                    Once a day
                </Option>
                <Option  value={'Twice A Day'}>
                    Twice a day
                </Option>
                <Option  value={'Thrice a day'}>
                    Thrice a day
                </Option>
            </Select>
            <Input onChange={handleChange} label={'Duration'} name={'duration'}/>
            <Textarea onChange={handleChange} label={'Instructions'} name={'instructions'}/>

            <Button onClick={handleAddMedicine}>Add Medicine</Button>
        </div>
    )
}


const PrescriptionList = ({medicines, selectedAppointment}) => {

    const {userId} = useSelector(state => state.auth);
    
    const [prescription, setPresctiption] = useState(
        {
            patientId: selectedAppointment?.patient.userId,
            doctorId: userId,
            issueDate: new Date().toISOString(),
            medicines: medicines
        }
    );
    
    useEffect(() => {
        setPresctiption({
            ...prescription,
            patientId: selectedAppointment?.patient.userId,
            doctorId: userId,
            issueDate:  new Date().toISOString().replace('Z', '+00:00'),
            medicines: medicines
        })
    }, [medicines, selectedAppointment]);
  
    const handlePrescribe = async() => {
        if(medicines.length === 0) return;
        console.log('Sent Prescription: ', prescription)
        
        const response = await createPrescription(prescription);
        if(response) {
            console.log('Prescription Created', response);
        }
    }

    return(
        <Card>
            <div className={'p-4 flex justify-between'}>
                <Typography className={'text-gray-800 font-semibold'}>Prescription</Typography>
                <div>
                    <Button size={"sm"} onClick={handlePrescribe} color={'green'}>Prescribe</Button>
                </div>
            </div>
            <div className={'p-4'}>
                <Typography className={'text-gray-800 font-semibold text-sm mb-2'}>Patient: <span className={'font-normal'}>{selectedAppointment?.patient.firstName} {selectedAppointment?.patient.lastName}</span></Typography>
                <Typography className={'text-gray-800 font-semibold text-sm mb-2'}>Doctor: <span className={'font-normal'}>Dr. {selectedAppointment?.doctor.firstName} {selectedAppointment?.doctor.lastName}</span></Typography>
                <Typography className={'text-gray-800 font-semibold text-sm mb-4'}>Date: <span className={'font-normal'}>{new Date().toLocaleDateString()}</span></Typography>
                <Typography className={'text-gray-800 font-semibold text-sm mb-2'}>Medicines</Typography>
                <table className="w-full min-w-max table-auto text-left">
                    <thead>
                    <tr className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
                        {TABLE_HEADERS.map((header, index) => {
                            return (
                                <td key={index} className="leading-none opacity-80 p-2 text-gray-900 text-sm">{header}</td>
                            )
                        }
                        )}
                    </tr>
                    </thead>
                    <tbody>
                    {medicines && medicines.map((medicine, index) => {
                        const isLast = index === medicine.length - 1;
                        const classes = isLast ? "p-3 text-sm text-gray-900" : "p-3 border-b border-blue-gray-50 text-sm text-gray-900";
                        return (
                            <tr key={index}>
                                <td className={classes}>{medicine.medicineName}</td>
                                <td className={classes}>{medicine.dosage}</td>
                                <td className={classes}>{medicine.frequency}</td>
                                <td className={classes}>{medicine.duration}</td>
                                <td className={classes}>{medicine.instructions}</td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
            </div>
        </Card>
    )
}

const Patients = ({selectedAppointment, setSelectedAppointment }) => {

    const [appointments, setAppointments] = useState([]);
    const {userId} = useSelector(state => state.auth);
    useEffect(() => {
        const fetchAppointments = async() => {
            const response = await getDoctorAppointments(userId);
            if(response) {
                setAppointments(response);
                console.log('Appointments', response);
            }
        }

        fetchAppointments();
    }, [userId]);

    console.log('Selected Appointment', selectedAppointment);

    return (
        <div className={'p-4 mt-4 h-full overflow-y-auto'}>
            <Typography className={'text-gray-800 font-semibold mb-2'}>Patient History</Typography>
            <div className={'h-96 overflow-y-auto'}>
                {appointments && appointments?.map((appointment, index) => {
                    return (
                        <div key={index}
                             className={`p-4 border-2 border-gray-200 bg-gray-100 mb-2 rounded-lg flex flex-row gap-5 ${selectedAppointment?.patient.userId === appointment.patient.userId && 'border-green-400'}`}>

                            <div className={''}>
                                <Typography className={'text-sm text-gray-900'}> <strong className={'font-semibold'}>Patient: </strong> {appointment.patient.firstName} {appointment.patient.lastName}</Typography>
                                <Typography className={'text-sm text-gray-900'}>
                                    <strong className={'font-semibold'}>Appointment Date & Time : </strong>
                                    {formatDateWithTime(appointment.appointmentStart)}
                                </Typography>
                                <Button size={'sm'} className={'mt-4'} onClick={() => setSelectedAppointment(appointment)}>View Prescription</Button>
                            </div>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}



PrescriptionList.propTypes = {
    medicines: PropTypes.array.isRequired,
    selectedAppointment: PropTypes.object,
};

Prescription.propTypes = {
    medicines: PropTypes.array.isRequired,
    setMedicines: PropTypes.func.isRequired
};

MainContent.propTypes = {
    medicines: PropTypes.array.isRequired,
    setMedicines: PropTypes.func.isRequired,
    selectedAppointment: PropTypes.object,
    setSelectedAppointment: PropTypes.func.isRequired
};

MedicineForm.propTypes = {
    setMedicines: PropTypes.func.isRequired,
    medicines: PropTypes.array.isRequired,
};

Patients.propTypes = {
    selectedAppointment: PropTypes.object,
    setSelectedAppointment: PropTypes.func.isRequired
};



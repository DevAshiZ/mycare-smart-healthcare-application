import {Avatar, Button, Card, IconButton, Input, Option, Select, Textarea, Typography} from "@material-tailwind/react";
import {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPlusCircle,  faTrash,  faX} from "@fortawesome/free-solid-svg-icons";
import PropTypes from "prop-types";


const PATIENTS = [
    {
        id: 1,
        name: 'Patient 1',
        age: 23,
    },
    {
        id: 2,
        name: 'Patient 2',
        age: 25,
    },
    {
        id: 3,
        name: 'Patient 3',
        age: 27,
    },
    {
        id: 4,
        name: 'Patient 4',
        age: 29,
    },
    {
        id: 5,
        name: 'Patient 5',
        age: 31,
    },
    {
        id: 6,
        name: 'Patient 6',
        age: 33,
    },
]

export const DoctorDashboard = () => {
    const [medicines, setMedicines] = useState([]);
    const [selectedPatient, setSelectedPatient] = useState(null);

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
                        setSelectedPatient={setSelectedPatient}
                        selectedPatient={selectedPatient}
                    />
                </div>
                <div className={'w-2/3'}>
                    <PrescriptionList medicines={medicines}/>
                </div>
            </div>
        </div>
    )
}

const MainContent = ({medicines, setMedicines, selectedPatient, setSelectedPatient}) => {
    const [selectedTab, setSelectedTab] = useState('prescription')
    return(
        <Card className={'p-4 h-screen'}>
            {/*tab navigation*/}
            <div className={'mt-2 flex flex-row gap-4'}>
                <Typography onClick={()=>setSelectedTab('prescription')} className={`pb-2 text-sm font-semibold text-gray-800 hover:text-green-400 ${selectedTab === 'prescription' && 'border-b-4 border-green-400 text-green-400'}`}>Create Prescription</Typography>
                <Typography onClick={()=>setSelectedTab('patient-history')} className={`pb-2 text-sm font-semibold text-gray-800 hover:text-green-400 ${selectedTab === 'patient-history' && 'border-b-4 border-green-400 text-green-400'}`}>View Patient History</Typography>
            </div>
            {/*tab content*/}
            <div>
                {selectedTab === 'prescription' && <Prescription medicines={medicines} setMedicines={setMedicines}/>}
                {selectedTab === 'patient-history' && <Patients selectedPatient={selectedPatient} setSelectedPatient={setSelectedPatient}/>}
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
                              <Typography className={'text-xs text-gray-800 font-semibold hover:text-green-400 cursor-pointer'}>{medicine.medicationName}</Typography>
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
        medicationName: '',
        dosage: '',
        frequency: '',
        duration: '',
        instructions: ''
    });

    const handleAddMedicine = () => {
        setMedicines([...medicines, medicine]);
        setMedicine({
            medicationName: '',
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
            <Input onChange={handleChange} label={'Medication Name'} name={'medicationName'}/>
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

const PrescriptionList = ({medicines}) => {

    const [prescription, setPrescription] = useState(
        {
            patient: 'Patient_Name',
            doctor: 'Doctor_Name',
            date: new Date().toLocaleDateString(),
            medicines: medicines
        }
    );

    const handlePrescribe = () => {
        {/*TODO: Should send the data to the database*/}
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
                <Typography className={'text-gray-800 font-semibold text-sm mb-2'}>Patient: <span className={'font-normal'}>Patient_Name</span></Typography>
                <Typography className={'text-gray-800 font-semibold text-sm mb-2'}>Patient: <span className={'font-normal'}>Doctor_Name</span></Typography>
                <Typography className={'text-gray-800 font-semibold text-sm mb-4'}>Date: <span className={'font-normal'}>{new Date().toLocaleDateString()}</span></Typography>
                <Typography className={'text-gray-800 font-semibold text-sm mb-2'}>Medicines</Typography>
                <table className="w-full min-w-max table-auto text-left">
                    <thead>
                    <tr className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
                        <td className="leading-none opacity-80 p-2 text-gray-900 text-sm">Medication Name</td>
                        <td className="leading-none opacity-80 p-2 text-gray-900 text-sm">Dosage</td>
                        <td className="leading-none opacity-80 p-2 text-gray-900 text-sm">Frequency</td>
                        <td className="leading-none opacity-80 p-2 text-gray-900 text-sm">Duration</td>
                        <td className="leading-none opacity-80 p-2 text-gray-900 text-sm">Instructions</td>
                    </tr>
                    </thead>
                    <tbody>
                    {medicines && medicines.map((medicine, index) => {
                        const isLast = index === medicine.length - 1;
                        const classes = isLast ? "p-3 text-sm text-gray-900" : "p-3 border-b border-blue-gray-50 text-sm text-gray-900";
                        return (
                            <tr key={index}>
                                <td className={classes}>{medicine.medicationName}</td>
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

const Patients = ({selectedPatient, setSelectedPatient}) => {
    return (
        <div className={'p-4 mt-4 h-full overflow-y-auto'}>
            <Typography className={'text-gray-800 font-semibold mb-2'}>Patient History</Typography>
            <div className={'h-full overflow-y-auto'}>
                {PATIENTS.map((patient, index) => {
                    return (
                        <div key={index}
                             className={`p-4 border-2 border-gray-200 bg-gray-100 mb-2 rounded-lg flex flex-row gap-5 ${selectedPatient === patient.id && 'border-green-400'}`}>
                            <Avatar
                                src={'https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?t=st=1728899190~exp=1728902790~hmac=457545dc36783c0395845b35391d6d42fea87f5cae65595b2e5ef13efd29bfbd&w=740'}/>
                            <div>
                                <Typography
                                    className={'text-gray-800 font-semibold text-sm'}>{patient.name}</Typography>
                                <Typography className={'text-gray-800 font-semibold text-xs'}>{patient.age}</Typography>
                            </div>
                            <div className={'flex items-center justify-end gap-2'}>
                                <Button variant={"outlined"} size={'sm'} onClick={()=> setSelectedPatient(patient.id)}>
                                    Select Patient
                                </Button>
                                <IconButton variant={"text"}>
                                    <FontAwesomeIcon icon={faTrash} className={'text-red-600'}/>
                                </IconButton>
                            </div>

                        </div>
                    )
                })}
            </div>
        </div>
    )
}



PrescriptionList.propTypes = {
    medicines: PropTypes.array.isRequired
};

Prescription.propTypes = {
    medicines: PropTypes.array.isRequired,
    setMedicines: PropTypes.func.isRequired
};

MainContent.propTypes = {
    medicines: PropTypes.array.isRequired,
    setMedicines: PropTypes.func.isRequired

};

MedicineForm.propTypes = {
    setMedicines: PropTypes.func.isRequired,
    medicines: PropTypes.array.isRequired
};

Patients.propTypes = {
    selectedPatient: PropTypes.object,
    setSelectedPatient: PropTypes.func.isRequired
};



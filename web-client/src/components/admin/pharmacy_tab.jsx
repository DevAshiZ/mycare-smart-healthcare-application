import {useState} from "react";
import {Button, Card, Input, Typography} from "@material-tailwind/react";
import toast from "react-hot-toast";

export const PharmacyTab = () => {

    const [selectedTab, setSelectedTab] = useState('register-pharmacy');

    return (
        <div className={'p-4 bg-gray-100 h-screen'}>
            <Card className={'p-4'}>
                <Typography className={'text-lg text-green-500 font-semibold'}>Pharmacy Section</Typography>
                <div className={'flex gap-2 mt-5'}>
                    <Typography onClick={()=>setSelectedTab('register-pharmacy')} className={`text-sm text-gray-800 font-semibold hover:text-green-400 pb-2 ${selectedTab === 'register-pharmacy' && 'border-b-4 border-green-400'}`}>Register Pharmacy</Typography>
                    <Typography onClick={()=>setSelectedTab('view-pharmacies')} className={`text-sm text-gray-800 font-semibold hover:text-green-400 pb-2 ${selectedTab === 'view-pharmacies' && 'border-b-4 border-green-400'}`}>View Pharmacies</Typography>
                </div>
            </Card>
            <div className={'w-full flex gap-2 mt-4'}>
                {selectedTab === 'register-pharmacy' && <RegisterPharmacy/>}
                {selectedTab === 'view-pharmacies' && <ViewPharmacies/>}
            </div>
        </div>
    );
}


const RegisterPharmacy = () => {

    const [pharmacyRegistrationData, setPharmacyRegistrationData] = useState({
        email: '',
        password: '',
        pharmacyName: '',
        pharmacyAddress: ''
    });

    const handlePharmacyOnChange = (e) => {
        setPharmacyRegistrationData({
            ...pharmacyRegistrationData,
            [e.target.name]: e.target.value
        });
    }

    const handlePharmacyRegistration = async () => {
        if(!pharmacyRegistrationData.email || !pharmacyRegistrationData.password || !pharmacyRegistrationData.pharmacyName || !pharmacyRegistrationData.pharmacyAddress){
            toast.error('Please fill all the fields');
            return;
        }
        // const response = await addNewDoctor(pharmacyRegistrationData);
        // console.log(response);
    }

    return (
        <Card className={'w-full p-4'}>
            <div className={'mb-4'}>
                <Typography className={'text-lg text-gray-800 font-semibold '}>Register Pharmacy</Typography>
            </div>
            <div className={'flex justify-between items-center'}>
                <div className={'flex flex-col gap-2 w-1/2'}>
                    <Input name={'pharmacyName'} label={'Pharmacy Name'} onChange={handlePharmacyOnChange}/>
                    <Input name={'email'} label={'Email'} type={'email'} onChange={handlePharmacyOnChange}/>
                    <Input name={'password'} label={'Password'} type={'password'} onChange={handlePharmacyOnChange}/>
                    <Input name={'pharmacyAddress'} label={'Pharmacy Address'} onChange={handlePharmacyOnChange}/>

                    <Button onClick={handlePharmacyRegistration} className={'mt-4'}>Add Pharmacy</Button>
                </div>
                <div className={'mr-10'}>
                    <img
                        className={'w-96'}
                        src={'https://img.freepik.com/free-vector/flat-design-pharmacist-serving-customers_23-2148222816.jpg?t=st=1728889757~exp=1728893357~hmac=05d1af544bf28d3d66e27b8209c6692b8eeaa8e6a54a34c4139fd64e352caf8a&w=740'}
                        alt={'doctor'}/>
                </div>
            </div>
        </Card>
    )
}

const ViewPharmacies = () => {
    return (
        <div>
            pharmacy data
        </div>
    )
}

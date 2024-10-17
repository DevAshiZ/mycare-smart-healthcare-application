import {useEffect, useState} from "react";
import {Button, Card, Input, Typography} from "@material-tailwind/react";
import toast from "react-hot-toast";
import {getPharmacies, registerPharmacy} from "../../services/pharmacyService.js";

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
        firstName: '',
        lastName: '',
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
        if( !pharmacyRegistrationData.firstName || !pharmacyRegistrationData.lastName ||!pharmacyRegistrationData.email || !pharmacyRegistrationData.password || !pharmacyRegistrationData.pharmacyName || !pharmacyRegistrationData.pharmacyAddress){
            toast.error('Please fill all the fields');
            return;
        }
        console.log(pharmacyRegistrationData);
        await registerPharmacy(pharmacyRegistrationData);
    }

    return (
        <Card className={'w-full p-4'}>
            <div className={'mb-4'}>
                <Typography className={'text-lg text-gray-800 font-semibold '}>Register Pharmacy</Typography>
            </div>
            <div className={'flex justify-between items-center'}>
                <div className={'flex flex-col gap-2 w-1/2'}>
                    <Typography className={'text-sm text-gray-800 font-semibold'}>Pharmacy Details</Typography>
                    <Input name={'pharmacyName'} label={'Pharmacy Name'} onChange={handlePharmacyOnChange}/>
                    <Input name={'pharmacyAddress'} label={'Pharmacy Address'} onChange={handlePharmacyOnChange}/>
                    <Typography className={'text-sm text-gray-800 font-semibold'}>Pharmacy Owner Details</Typography>
                    <div className={'grid grid-cols-2 gap-2'}>
                        <Input name={'firstName'} label={'First Name'} onChange={handlePharmacyOnChange}/>
                        <Input name={'lastName'} label={'Last Name'} onChange={handlePharmacyOnChange}/>
                    </div>
                    <Input name={'email'} label={'Email'} type={'email'} onChange={handlePharmacyOnChange}/>
                    <Input name={'password'} label={'Password'} type={'password'} onChange={handlePharmacyOnChange}/>


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

    const TABLE_HEAD = ['Pharmacy Name', 'Pharmacy Address', 'Owner Name', 'Owner Email'];

    const [pharmacies, setPharmacies] = useState([]);

    useEffect(() => {
        const fetchPharmacies = async () => {
            const response = await getPharmacies()
            setPharmacies(response.data);
        }
        fetchPharmacies();
    }, []);
    return (
        <Card className={'mt-4 w-full'}>
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
                {pharmacies.map((row, index) => {
                    const isLast = index === pharmacies.length - 1;
                    const classes = isLast
                        ? "p-4"
                        : "p-4 border-b border-blue-gray-50";

                    return (
                        <tr key={index}>
                            <td className={classes}>
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {row.pharmacyName}
                                </Typography>
                            </td>
                            <td className={classes}>
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {row.pharmacyAddress}
                                </Typography>
                            </td>
                            <td className={classes}>
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {row.firstName} {row.lastName}
                                </Typography>
                            </td>
                            <td className={classes}>
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {row.email}
                                </Typography>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </Card>
    )
}

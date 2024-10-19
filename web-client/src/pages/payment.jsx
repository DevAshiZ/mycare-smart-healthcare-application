import {Button, Card, Chip, Input, Typography} from "@material-tailwind/react";
import {paymentScreenStyles} from "../assets/styles/payment.styles.js";
import {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCreditCard} from "@fortawesome/free-solid-svg-icons";


function formatCardNumber(value) {
    const val = value.replace(/\s+/g, "").replace(/[^0-9]/gi, "");
    const matches = val.match(/\d{4,16}/g);
    const match = (matches && matches[0]) || "";
    const parts = [];

    for (let i = 0, len = match.length; i < len; i += 4) {
        parts.push(match.substring(i, i + 4));
    }

    if (parts.length) {
        return parts.join(" ");
    } else {
        return value;
    }
}

function formatExpires(value) {
    return value
        .replace(/[^0-9]/g, "")
        .replace(/^([2-9])$/g, "0$1")
        .replace(/^(1{1})([3-9]{1})$/g, "0$1/$2")
        .replace(/^0{1,}/g, "0")
        .replace(/^([0-1]{1}[0-9]{1})([0-9]{1,2}).*/g, "$1/$2");
}

export const PaymentPage = () => {

    const [paymentMethod, setPaymentMethod] = useState('card');


    return (
        <div className={paymentScreenStyles.paymentScreen}>
            <Card className={paymentScreenStyles.paymentFormSection.container}>
                {/* Payment Form */}
                    <Typography  className={'text-left text-sm font-bold font-abril text-gray-800 mb-5'}>
                        Select Card Payment Method
                    </Typography>
                <div className={'flex gap-4 mb-5'}>
                    <Typography
                        onClick={()=> setPaymentMethod('card')}
                        className={`mt-2 text-xs font-semibold  cursor-pointer hover:border-b-4 hover:border-green-400 hover:text-green-400 hover:font-semibold ${paymentMethod === 'card' && 'border-b-4 border-green-400 '}`}>
                        Card Payment
                    </Typography>
                    <Typography
                        onClick={()=> setPaymentMethod('cash')}
                        className={`mt-2 text-xs font-semibold cursor-pointer hover:border-b-4 hover:border-green-400 hover:text-green-400 hover:font-semibold ${paymentMethod === 'cash' && 'border-b-4 border-green-400 '}`}>
                        Cash Payment
                    </Typography>
                </div>
                {/* Payment Form Container*/}
                <div>
                    {paymentMethod === 'card' ? <CardPaymentForm/> : <CashPaymentForm/>}
                </div>
            </Card>
            <Card className={paymentScreenStyles.billInfoSection.container}>
                {/* Bill Information */}
                <Typography className={'text-center text-sm font-bold font-abril text-gray-800 mb-10'}>Bill
                    Summary</Typography>

                <div className={'flex justify-between  px-4'}>
                    <Typography className={'text-sm text-gray-800'}>Payment Status</Typography>
                    <Chip value={'Pending'} color={'orange'}/>
                </div>
                <div className={'border-b border-gray-200 px-8 mt-4 mb-2'}>

                </div>
                <div className={'flex justify-between  px-4'}>
                    <Typography className={'text-sm text-gray-800'}>Total Amount</Typography>
                    <Typography className={'text-sm text-gray-800'}>Rs. 1900.00</Typography>
                </div>
            </Card>
        </div>
    )
}

const CardPaymentForm = () => {

    const [cardNumber, setCardNumber] = useState('');
    const [cardExpires, setCardExpires] = useState('');



    return (
        <div className={'flex items-center justify-between p-4'}>
        <img alt={'card payment image'} className='w-96' src={'https://img.freepik.com/free-vector/concept-credit-card-payment-landing-page_23-2148298985.jpg?t=st=1728837495~exp=1728841095~hmac=d4d7c8eb33104f03a9aca5e1b5c6ce0a28f72be2c5a6689d45d6dafba225b555&w=740'}/>
            <div className='w-96'>
                <Typography className={'text-md font-bold text-gray-800 mb-2'}>Enter Card Details</Typography>

                <Typography className={'text-sm font-bold text-gray-800 mb-2'}>Card Number</Typography>
                <Input
                    maxLength={19}
                    onChange={(event) => setCardNumber(event.target.value)}
                    value={formatCardNumber(cardNumber)}
                    icon={
                        <FontAwesomeIcon icon={faCreditCard} className="absolute left-0 h-4 w-4 text-blue-gray-300"/>
                    }
                    placeholder="0000 0000 0000 0000"
                    className=" !border-t-blue-gray-200 focus:!border-t-gray-900 "
                    labelProps={{
                        className: "before:content-none after:content-none",
                    }}
                />

                <div className={'flex w-full gap-2 mt-2'}>
                    <div>
                        <Typography className={'text-sm font-bold text-gray-800 mb-2'}>Card Expires</Typography>
                        <Input
                            maxLength={5}
                            value={formatExpires(cardExpires)}
                            onChange={(event) => setCardExpires(event.target.value)}
                            containerProps={{className: "min-w-[72px]"}}
                            placeholder="00/00"
                            className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                            labelProps={{
                                className: "before:content-none after:content-none",
                            }}
                        />
                    </div>
                    <div>
                        <Typography className={'text-sm font-bold text-gray-800 mb-2'}>CVV</Typography>
                        <Input
                            maxLength={3}
                            containerProps={{className: "min-w-[72px]"}}
                            placeholder="000"
                            className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                            labelProps={{
                                className: "before:content-none after:content-none",
                            }}
                        />
                    </div>
                </div>

                <div>
                    <Typography className={'text-sm font-bold text-gray-800 mb-2 mt-2'}>Card Holder</Typography>
                    <Input
                        labelProps={{
                            className: "before:content-none after:content-none",
                        }}
                        className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                    />
                </div>

                <Button fullWidth className={'mt-5'}>Make Payment</Button>
            </div>
        </div>
    )
}

const CashPaymentForm = () => {
    return (
        <div className={'flex items-center justify-between p-4'}>
            <img alt={'cash payment image'} className='w-96'
                 src={'https://img.freepik.com/free-vector/person-sending-money-using-phone-flat-vector-illustration-hands-holding-cash-smartphone-with-banking-app-its-screen-money-transferring-concept-banner-website-design-landing-web-page_74855-25256.jpg?t=st=1728838351~exp=1728841951~hmac=7ce946c04e76733044e3e63811e1ee0bf83db761561b0675e4fc12e664cac3eb&w=996'}/>
            <div className='w-96'>
                <Typography className={'text-sm font-bold text-gray-800 mb-2'}>Email</Typography>
                <Input
                    className=" !border-t-blue-gray-200 focus:!border-t-gray-900 "
                    labelProps={{
                        className: "before:content-none after:content-none",
                    }}
                />
                <Typography className={'text-sm font-bold text-gray-800 mb-2 mt-2'}>Phone Number</Typography>
                <Input
                    className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                    labelProps={{
                        className: "before:content-none after:content-none",
                    }}
                />
                <Button fullWidth className={'mt-5'}>Proceed Payment</Button>
            </div>
        </div>
    )
}


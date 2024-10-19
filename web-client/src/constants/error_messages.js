const ERRORS = {
    'UNKNOWN_ERROR': 'Please Contact the Admin',
    'INVALID_CREDENTIALS': 'Invalid Credentials',
    'DOCTOR_AVAILABILITY_ERROR': 'Error fetching doctor availability',
    'APPOINTMENT_ERROR': 'Error creating appointment. Please try again',
    'APPOINTMENT_FETCH_ERROR': 'Error fetching appointments',
    'ADD_DOCTOR_ERROR': 'Error adding new doctor',
    'GET_DOCTORS_ERROR': 'Error fetching doctors',
    'SCHEDULE_ADD_ERROR': 'Error adding schedule',
    'SCHEDULE_GET_ERROR': 'Error fetching schedules',
    'APPOINTMENT_ADD_ERROR' : 'Error adding appointment',
    'PAYMENT_ERROR' : 'Error processing payment',
}

Object.freeze(ERRORS);

export default ERRORS;
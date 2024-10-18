
// Utility function to convert 24-hour time to 12-hour format with A.M./P.M.
export const formatTime = (time) => {
    const [hour, minute] = time.split(':');
    const hourInt = parseInt(hour, 10);
    const period = hourInt >= 12 ? 'P.M.' : 'A.M.';
    const formattedHour = hourInt % 12 || 12;
    return `${formattedHour}:${minute} ${period}`;
};

// Utility function to add minutes to a given time
export const addMinutes = (time, minutes) => {
    const [hour, minute] = time.split(':');
    const date = new Date(0, 0, 0, hour, minute);
    date.setMinutes(date.getMinutes() + minutes);
    return date.toTimeString().slice(0, 5);
};

const daysOfWeek = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];

// Function to get the next occurrence of the selected day with start time
export function getNextAppointmentDate(selectedSchedule) {
    const { day, startTime } = selectedSchedule;

    // Get today's date
    const today = new Date();
    const todayDayIndex = today.getDay(); // 0 for Sunday, 1 for Monday, etc.

    // Find the index of the desired day
    const targetDayIndex = daysOfWeek.indexOf(day);

    // Calculate how many days until the next occurrence of the target day
    let daysUntilNext = targetDayIndex - todayDayIndex;
    if (daysUntilNext < 0) {
        daysUntilNext += 7; // If the target day is earlier in the week, go to the next week
    }

    // Get the date of the next occurrence of the target day
    const nextAppointmentDate = new Date();
    nextAppointmentDate.setDate(today.getDate() + daysUntilNext);

    // Split the start time into hours and minutes
    const [hours, minutes] = startTime.split(':').map(Number);

    // Set the time on the next appointment date
    nextAppointmentDate.setHours(hours, minutes, 0, 0); // Set time (HH:mm:00)

    return nextAppointmentDate;
}

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

// Helper to get the next occurrence of the selected day
export const getNextDay = (dayOfWeek) => {
    const dayMapping = { 'MON': 1, 'TUE': 2, 'WED': 3, 'THU': 4, 'FRI': 5, 'SAT': 6, 'SUN': 0 };
    const today = new Date();
    const currentDay = today.getDay(); // 0-6 representing Sunday-Saturday
    const dayDiff = (dayMapping[dayOfWeek] + 7 - currentDay) % 7; // Calculate how many days until the next `dayOfWeek`
    const nextDate = new Date(today);
    nextDate.setDate(today.getDate() + dayDiff);
    return nextDate;
};
import React, { useState } from 'react';

const ScheduleWalkReminder = () => {
    const [hours, setHours] = useState('');
    const [minutes, setMinutes] = useState('');
    const [reminderMessage, setReminderMessage] = useState('');

    const handleScheduleReminder = async (e) => {
        e.preventDefault();
        const scheduleWalkReminderAPI = `http://localhost:8080/walking/scheduleWalkReminder?hours=${hours}&minutes=${minutes}`;

        try {
            const response = await fetch(scheduleWalkReminderAPI, { method: 'POST' });
            if (!response.ok) throw new Error("Failed to schedule reminder");

            const message = await response.text();
            setReminderMessage(message);
        } catch (error) {
            console.error(error);
            setReminderMessage("Failed to schedule reminder!");
        }
    };

    return (
        <div>
            <form onSubmit={handleScheduleReminder}>
                <label>
                    Hours:
                    <input
                        type="number"
                        value={hours}
                        onChange={e => setHours(e.target.value)}
                        placeholder="Enter hours"
                    />
                </label>
                <label>
                    Minutes:
                    <input
                        type="number"
                        value={minutes}
                        onChange={e => setMinutes(e.target.value)}
                        placeholder="Enter minutes"
                    />
                </label>
                <button type="submit">Schedule Reminder</button>
            </form>
            {reminderMessage && <p>{reminderMessage}</p>}
        </div>
    );
};
export default ScheduleWalkReminder;
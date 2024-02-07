import { getSleepDataForUser, addSleepDataForUser } from "../../api/sleepApi";
import { useEffect, useState } from "react";
import Header from "../HomePage/Header/Header";
import NavBar from "../HomePage/NavBar/NavBar";
import "./SleepPage.css"

const SleepPage = () => {
    const [items, setItems] = useState([]);
    const [actualBedtime, setActualBedtime] = useState("");
    const [actualWakeupTime, setActualWakeupTime] = useState("");
    const [targetBedtime, setTargetBedtime] = useState("");
    const [targetWakeupTime, setTargetWakeupTime] = useState("");
    const [refresh, setRefresh] = useState(null);


    useEffect(() => {
        const user = "divin";
        getSleepDataForUser(user)
            .then((data) => {
                console.log("sleep data: ", data);
                setItems(data)
            })
            .catch((error) => {
                console.error("Error fetching sleep data: ", error);
            });
    }, [refresh]);

    const handleTargetBedtimeChange = (event) => {
        setTargetBedtime(event.target.value);
    };

    const handleTargetWakeupTimeChange = (event) => {
        setTargetWakeupTime(event.target.value);
    };

    const handleActualBedtimeChange = (event) => {
        setActualBedtime(event.target.value);
    };

    const handleActualWakeupTimeChange = (event) => {
        setActualWakeupTime(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const user = "divin";
        const newSleepData = { "targetBedtime": targetBedtime, "targetWakeUpTime": targetWakeupTime, "actualBedtime": actualBedtime, "actualWakeupTime": actualWakeupTime,
        	                    "user":{ "name": user}};
        addSleepDataForUser(newSleepData)
            .then((response) => {
                // Update UI or show success message
                console.log("Sleep data updated successfully:", response);
                setRefresh(response);
            })
            .catch((error) => {
                console.error("Error updating sleep data: ", error);
            });
        console.log("Sleep Record Submitted: ", JSON.stringify(newSleepData));
    };

    return (
        // <div className="SleepPage">
        <div className="desktop">
            <Header />
            <NavBar />
            <h1>Sleep</h1>
            <div>Your sleep details are below</div>
            <ul>
                {items.map((item) => {
                    const bedTime = new Date(item.actualBedtime);
                    const wakeupTime = new Date(item.actualWakeupTime);
                    const date = new Date(item.actualBedtime);
                    return (
                        <li key={item.id}>
                            {date.toLocaleDateString([], {})}:&nbsp;
                            Bed Time: {bedTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })},
                            Wake-Up Time: {wakeupTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                        </li>
                    );
                })}
            </ul>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Target Bedtime:
                        <input type="datetime-local" value={targetBedtime} onChange={handleTargetBedtimeChange} />
                    </label>
                </div>
                <div>
                    <label>
                        Target Wakeup Time:
                        <input type="datetime-local" value={targetWakeupTime} onChange={handleTargetWakeupTimeChange} />
                    </label>
                </div>
                <div>
                    <label>
                        Actual Bedtime:
                        <input type="datetime-local" value={actualBedtime} onChange={handleActualBedtimeChange} />
                    </label>
                </div>
                <div>
                    <label>
                        Actual Wakeup Time:
                        <input type="datetime-local" value={actualWakeupTime} onChange={handleActualWakeupTimeChange} />
                    </label>
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    )
}

export default SleepPage;

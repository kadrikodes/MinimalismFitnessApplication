import "./SleepContainer.css";

const SleepContainer = (props) => {

    const { actualBedtime = '', actualWakeupTime = ''} = props.sleepData || {};

   

    const bedTime = new Date(actualBedtime);
    const wakeupTime = new Date(actualWakeupTime);
    const sleepTime = wakeupTime - bedTime;

    
    const hours = Math.floor(sleepTime / (60 * 60 * 1000));
    const minutes = Math.floor((sleepTime % (60 * 60 * 1000)) / (60 * 1000));


    return (
    <div className="SleepContainer"> 
        
        <h1>Sleep</h1>
        <h2>Bed Time: {bedTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</h2>
        <h2>Wake-Up Time: {wakeupTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</h2>
        <h2>Sleep Time: {hours}hrs {minutes}min </h2>
    
    </div>);
}

export default SleepContainer;
export function getSleepDataForUser(user) {

    const url = 'http://localhost:8080/sleeptracker/name/' + user;
    console.debug("url", url)
    return fetch(url)
        .then(response => {
            if(response.ok) {
                return response.json();
            } else {
                console.error(`Response error in getSleepDataForUser: ${response.status}`);
            }
        }).catch((e) => console.error(`Exception in getSleepDataForUser: ${JSON.stringify(e)}`));
}

export function addSleepDataForUser(newSleepData) {
    const url = 'http://localhost:8080/sleeptracker/addSleepData';

    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newSleepData),
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            console.error(`Response error in addSleepDataForUser: ${response.status}`);
            throw new Error(`Response error in addSleepDataForUser: ${response.status}`);
        }
    })
    .catch(error => {
        console.error(`Exception in addSleepDataForUser: ${error.message}`);
        throw error;
    });
}


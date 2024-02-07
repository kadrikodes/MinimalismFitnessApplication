const fetchWalkingDataByUserName = () => {
    const findByUserName = `http://localhost:8080/walking/name/Rais`;

    fetch(findByUserName)
    .then((response) => {return response.json();})
};

export { fetchWalkingDataByUserName };

import "./UserContainer.css";

const UserContainer = (props) => {

    const { name = '', height = '', weight = '' } = props.userData || {};
    return (
    <div className="UserContainer">
        <h1>Welcome {name}!</h1>
        <h2>Height: {height}cm</h2>
        <h2>Weight: {weight}kg</h2>
    </div>);
}

export default UserContainer;
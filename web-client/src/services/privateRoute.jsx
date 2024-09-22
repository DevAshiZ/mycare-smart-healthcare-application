import {useSelector} from "react-redux";
import {Navigate, Outlet} from "react-router-dom";

/**
 * PrivateRoute component : This component is used to check if the user is authenticated or not.
 * If the user is authenticated, it will return the Outlet component, otherwise it will redirect the user to the login page.
 * @returns {JSX.Element}
 * @constructor
 */

export const PrivateRoute = () => {

    const {user} = useSelector(state => state.auth);
    if(!user) {
        return <Navigate to={"/"}/>
    }else {
        return <Outlet/>;
    }
}

import { Sidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import {Link} from "react-router-dom";

function AdminNavbar() {
    const styleSheet = {
        sidebar : {
            backgroundColor: "black",
            width: "200px",
            height: "100vh",
            position: "fixed",
            top: "0",
            left: "0",
            zIndex: "1000",
        },
    }

    return (
        <Sidebar style={styleSheet.sidebar}>
            <Menu>
                <SubMenu label="Registration Forms">
                    <MenuItem component={<Link to={"/admin/doctor-register"} />}>Doctor</MenuItem>
                    <MenuItem component={<Link to={"/admin/pharmacy-register"} />}>Pharmacy</MenuItem>
                </SubMenu>
                <MenuItem component={<Link to={"/admin/summary"} />}>Summary</MenuItem>
                <MenuItem> Calendar </MenuItem>
            </Menu>
        </Sidebar>
    )
}

export default AdminNavbar;
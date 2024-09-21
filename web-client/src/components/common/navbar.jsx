import React, { useState } from "react";

import { IMAGE_CONVENTIONS, LOGO } from "../../constants/theme_constraints";
import {
  Navbar,
  Collapse,
  Typography,
  Avatar,
  Button,
  Menu,
  MenuHandler,
  MenuItem,
  MenuList,
  IconButton, Dialog, Card, CardHeader, CardBody, Input, Checkbox, CardFooter,
} from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBars,
  faX,
  faChevronDown,
  faCog,
  faMailBulk,
  faSignOut,
  faUser,
} from "@fortawesome/free-solid-svg-icons";
import {loginUser} from "../../services/userService.js";

function NavList() {
  return (
    <ul className="my-2 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6">
      <Typography
        as="li"
        variant="small"
        color="blue-gray"
        className="p-1 font-medium font-abril"
      >
        <a
          href="#"
          className="flex items-center hover:text-blue-500 transition-colors"
        >
          Pages
        </a>
      </Typography>
      <Typography
        as="li"
        variant="small"
        color="blue-gray"
        className="p-1 font-medium"
      >
        <a
          href="#"
          className="flex items-center hover:text-blue-500 transition-colors"
        >
          Account
        </a>
      </Typography>
      <Typography
        as="li"
        variant="small"
        color="blue-gray"
        className="p-1 font-medium"
      >
        <a
          href="#"
          className="flex items-center hover:text-blue-500 transition-colors"
        >
          Blocks
        </a>
      </Typography>
      <Typography
        as="li"
        variant="small"
        color="blue-gray"
        className="p-1 font-medium"
      >
        <a
          href="#"
          className="flex items-center hover:text-blue-500 transition-colors"
        >
          Docs
        </a>
      </Typography>
    </ul>
  );
}

export function NavigationBar() {
  const [openNav, setOpenNav] = React.useState(false);

  const handleWindowResize = () =>
    window.innerWidth >= 960 && setOpenNav(false);

  React.useEffect(() => {
    window.addEventListener("resize", handleWindowResize);

    return () => {
      window.removeEventListener("resize", handleWindowResize);
    };
  }, []);

  return (
    <div className="w-full  h-auto px-6 py-3 rounded-none ">
      <div className="flex items-center justify-between text-blue-gray-900 w-full ">
        <div className="h-10 flex items-center justify-center">
          <img
            src={LOGO.MAIN_LOGO}
            alt="logo"
            className="h-28 object-contain"
          />
        </div>

        <div className="hidden lg:block">
          <NavList />
        </div>

        <div className="flex gap-2">
            <LoginBtn />
          <ProfileMenu />
        </div>
        <IconButton
          variant="text"
          className="ml-auto h-6 w-6 text-inherit hover:bg-transparent focus:bg-transparent active:bg-transparent lg:hidden"
          ripple={false}
          onClick={() => setOpenNav(!openNav)}
        >
          {openNav ? (
            <FontAwesomeIcon icon={faX} className="h-6 w-6" />
          ) : (
            <FontAwesomeIcon icon={faBars} className="h-6 w-6" />
          )}
        </IconButton>
      </div>
      <Collapse open={openNav}>
        <NavList />
      </Collapse>
    </div>
  );
}

// profile menu component
const profileMenuItems = [
  {
    label: "My Profile",
    icon: faUser,
  },
  {
    label: "Settings",
    icon: faCog,
  },
  {
    label: "Inbox",
    icon: faMailBulk,
  },

  {
    label: "Sign Out",
    icon: faSignOut,
  },
];

function ProfileMenu() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const closeMenu = () => setIsMenuOpen(false);

  return (
    <Menu open={isMenuOpen} handler={setIsMenuOpen} placement="bottom-end">
      <MenuHandler>
        <Button
          variant="text"
          color="blue-gray"
          className="flex items-center gap-1 rounded-full py-0.5 pr-2 pl-0.5 "
        >
          <Avatar
            variant="circular"
            size="sm"
            alt="user-image"
            className="border border-gray-800 p-0.5"
            src={IMAGE_CONVENTIONS.PROFILE_IMG}
          />
          <FontAwesomeIcon
            icon={faChevronDown}
            className={`h-3 w-3 text-gray-800 transition-transform ${
              isMenuOpen ? "rotate-180" : ""
            }`}
          />
        </Button>
      </MenuHandler>
      <MenuList className="p-1">
        {profileMenuItems.map(({ label, icon }, key) => {
          const isLastItem = key === profileMenuItems.length - 1;
          return (
            <MenuItem
              key={label}
              onClick={closeMenu}
              className={`flex items-center gap-2 rounded ${
                isLastItem
                  ? "hover:bg-red-500/10 focus:bg-red-500/10 active:bg-red-500/10"
                  : "text-gray-900 hover:bg-gray-200 focus:bg-gray-200 active:bg-gray-200"
              }`}
            >
              <FontAwesomeIcon
                icon={icon}
                className={`h-4 w-4 ${isLastItem ? "text-red-500" : ""}`}
              />

              <Typography
                as="span"
                variant="small"
                className="font-normal"
                color={isLastItem ? "red" : "inherit"}
              >
                {label}
              </Typography>
            </MenuItem>
          );
        })}
      </MenuList>
    </Menu>
  );
}

function LoginBtn() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen((cur) => !cur);

  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  })

    const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prev) => ({
      ...prev,
      [name]: value,
    }));
    }

    const handleLoginSubmit = async () => {
      if(loginData.email === "" || loginData.password === ""){
        alert("Please enter your email and password");
        return;
      }
      await loginUser(loginData);
    }

  return (
      <div>
        <Dialog
            size="lg"
            open={open}
            handler={handleOpen}
            className="bg-transparent shadow-none flex justify-center items-center"
        >
          <Card className="mx-auto   flex-row">
            <CardHeader
                shadow={false}
                floated={false}
                className="m-0 w-3/5 shrink-0 rounded-r-none"
            >
              <img
                  src="https://img.freepik.com/free-photo/confident-female-doctor-hospital-room_9975-22979.jpg?t=st=1726923139~exp=1726926739~hmac=3a5c19ca31ab48973cb89cf0282d2597c62b408adca365498cb83dd1b468ed86&w=1060"
                  alt="card-image"
                  className="h-full w-full object-cover"
              />
            </CardHeader>
            <div>
              <CardBody className="flex flex-col gap-4">
                <Typography variant="h4" color="blue-gray">
                  Sign In
                </Typography>
                <Typography
                    className="mb-3 font-normal"
                    variant="paragraph"
                    color="gray"
                >
                  Enter your email and password to Sign In.
                </Typography>
                <Typography className="-mb-2" variant="h6">
                  Your Email
                </Typography>
                <Input name="email" label="Email" size="lg" onChange={handleLoginChange}/>
                <Typography className="-mb-2" variant="h6">
                  Your Password
                </Typography>
                <Input name="password" label="Password" size="lg" onChange={handleLoginChange}/>
                <div className="-ml-2.5 -mt-3">
                  <Checkbox label="Remember Me" />
                </div>
              </CardBody>
              <CardFooter className="pt-0">
                <Button variant="gradient" onClick={handleLoginSubmit} fullWidth>
                  Sign In
                </Button>
                <Typography variant="small" className="mt-4 flex justify-center">
                  Don&apos;t have an account?
                  <Typography
                      as="a"
                      href="#signup"
                      variant="small"
                      color="blue-gray"
                      className="ml-1 font-bold"
                      onClick={handleOpen}
                  >
                    Sign up
                  </Typography>
                </Typography>
              </CardFooter>
            </div>
          </Card>
        </Dialog>
        <Button onClick={handleOpen} variant="text">
          Sign In
        </Button>
      </div>
  );
}

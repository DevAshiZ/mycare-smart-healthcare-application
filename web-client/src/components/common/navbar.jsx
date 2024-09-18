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
  IconButton,
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

        <div>
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

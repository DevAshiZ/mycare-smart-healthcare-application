import React from "react";
import {
  COLORS,
  IMAGE_CONVENTIONS,
  LOGO,
  NAMING_CONVENTIONS,
} from "../constants/theme_constraints";
import {
  Button,
  Typography,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
} from "@material-tailwind/react";

export default function Home() {
  return (
    <div>
      <HeroSection />
      <ServicesSection />
    </div>
  );
}

function HeroSection() {
  return (
    <div
      className=" h-96 flex justify-between items-center gap-2 bg-cover bg-bottom "
      style={{
        backgroundImage: `url('${IMAGE_CONVENTIONS.HOME_HERO_IMG}')`,
      }}
    >
      <div className=" bg-black bg-opacity-10 w-full h-full items-center justify-center flex">
        <div className="flex-col items-center justify-center flex p-2">
          <Typography
            color="white"
            className="font-abril text-center"
            variant="h1"
          >
            Welcome to {NAMING_CONVENTIONS.APP_NAME}
          </Typography>
          <Typography color="white" className="font-abril text-center">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa
            provident excepturi corporis magni totam hic. Voluptas autem,
            reiciendis perferendis quia et, sapiente nisi, recusandae corporis
            nihil totam exercitationem maiores deleniti!
          </Typography>

          <Button className="mt-2">Get Started</Button>
        </div>
      </div>
    </div>
  );
}

function ServicesSection() {
  return (
    <div className="mt-2 justify-center flex flex-col">
      <Typography variant="h4" className="text-center text-gray-800 font-abril">
        Our Services
      </Typography>

      <div className="flex items-center justify-center">
        <div className="grid grid-cols-3 gap-4 mt-2">
          <ServiceCard />
          <ServiceCard />
          <ServiceCard />
        </div>
      </div>
    </div>
  );
}

function ServiceCard() {
  return (
    <Card className="mt-6 w-96">
      <CardHeader color="blue-gray" className="relative h-56">
        <img
          src="https://images.unsplash.com/photo-1540553016722-983e48a2cd10?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=800&q=80"
          alt="card-image"
        />
      </CardHeader>
      <CardBody>
        <Typography variant="h5" color="blue-gray" className="mb-2 font-abril">
          Lorem ipsum dolor sit amet consectetur.
        </Typography>
        <Typography className="text-sm font-abril">
          Lorem ipsum, dolor sit amet consectetur adipisicing elit. Molestiae
          debitis itaque et perspiciatis harum cumque natus sequi animi ex ipsum
          excepturi ducimus quae esse, provident magni nesciunt tempora, impedit
          maiores.
        </Typography>
      </CardBody>
      <CardFooter className="pt-0 flex items-center justify-center">
        <Button>Read More</Button>
      </CardFooter>
    </Card>
  );
}

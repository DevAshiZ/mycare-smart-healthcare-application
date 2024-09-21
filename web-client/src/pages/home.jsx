import {
  IMAGE_CONVENTIONS,
  NAMING_CONVENTIONS,
} from "../constants/theme_constraints";
import {
  Button,
  Typography,
  Card,
  CardBody,
  CardFooter,
} from "@material-tailwind/react";
import PropTypes from "prop-types";

const CARD_CONTENTS = [
    {
        IMAGE: "https://img.freepik.com/free-photo/doctor-nurses-special-equipment_23-2148980721.jpg?size=626&ext=jpg&ga=GA1.1.836062334.1725166554&semt=ais_hybrid",
        CARD_TITLE: "Find a Doctor",
        CARD_DESCRIPTION: "Easily search for specialists and trusted healthcare providers from our vast network of doctors and hospitals."
    },
    {
        IMAGE:"https://img.freepik.com/premium-photo/fridge-with-many-bottles-medicine-it_1033022-52816.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid",
        CARD_TITLE: "Online Pharmacy",
        CARD_DESCRIPTION: "Order your medications online and have them delivered swiftly with our reliable delivery service."
    },
    {
        IMAGE:"https://img.freepik.com/free-photo/doctor-doing-their-work-pediatrics-office_23-2149224139.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid",
        CARD_TITLE: "Medical Consultation",
        CARD_DESCRIPTION: "Get expert advice from certified doctors through free consultations and personalized healthcare recommendations."
    },
    {
        IMAGE: "https://img.freepik.com/free-photo/stethoscope-prescription-laptop_1232-4439.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid",
        CARD_TITLE: "Health Information",
        CARD_DESCRIPTION: "Access detailed medical information and personalized care tips to stay informed about your health."
    },
    {
        IMAGE:"https://img.freepik.com/free-photo/african-male-paramedic-face-protective-medical-mask-standing-front-ambulance-car_627829-4934.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid",
        CARD_TITLE: "Emergency Services",
        CARD_DESCRIPTION: "Receive immediate 24/7 emergency care for you and your family, ensuring prompt attention when it matters most."
    },
    {
        IMAGE:"https://img.freepik.com/premium-photo/two-smart-watches-with-time-4-15_1274269-164358.jpg?uid=R103831228&ga=GA1.1.836062334.1725166554&semt=ais_hybrid",
        CARD_TITLE: "Health Tracking",
        CARD_DESCRIPTION: "Monitor your medical history and track your health progress effortlessly with our secure health data management system."
    }
]

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
              {NAMING_CONVENTIONS.HOME.HERO_TITLE}
          </Typography>
          <Typography color="white" className="font-abril text-center">
              {NAMING_CONVENTIONS.HOME.HERO_CONTEXT}
          </Typography>

          <Button className="mt-2">Get Started</Button>
        </div>
      </div>
    </div>
  );
}

function ServicesSection() {
  return (
    <div className="mt-4 justify-center flex flex-col">
      <Typography variant="h4" className="text-center text-gray-800 font-abril">
        Our Services
      </Typography>
        <Typography variant="small" className="text-center text-gray-800 font-abril mb-2 justify-center">
            {NAMING_CONVENTIONS.HOME.SERVICES.SERVICES_DESCRIPTION}
        </Typography>


      <div className="flex items-center justify-center">
        <div className="grid grid-cols-3 gap-4 mt-2">
         {
            CARD_CONTENTS.map((card_content, index) => (
                <ServiceCard key={index} CARD_CONTENT={card_content} />
            ))
         }
        </div>
      </div>
    </div>
  );
}

function ServiceCard({CARD_CONTENT}) {
  return (
    <Card className="mt-6 w-80">
        <CardBody>
            <img
                src={CARD_CONTENT.IMAGE}
                alt="card-image "
                className=" object-cover w-full h-40 rounded-lg mb-2"
            />
            <Typography variant="h5" color="blue-gray" className="mb-2 font-abril">
                {CARD_CONTENT.CARD_TITLE}
            </Typography>
            <Typography className="text-sm font-abril">
                {CARD_CONTENT.CARD_DESCRIPTION}
            </Typography>
        </CardBody>
        <CardFooter className="pt-0 flex items-center justify-end">
            <Button variant="text">Read More</Button>
        </CardFooter>
    </Card>
  );
}

ServiceCard.propTypes = {
    CARD_CONTENT: PropTypes.shape({
        IMAGE: PropTypes.string.isRequired,
        CARD_TITLE: PropTypes.string.isRequired,
        CARD_DESCRIPTION: PropTypes.string.isRequired,
    }).isRequired,
};

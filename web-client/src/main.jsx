import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { ThemeProvider } from "@material-tailwind/react";
import {Provider} from "react-redux";
import store, {persistor} from "./redux/store.js";
import {PersistGate} from "redux-persist/integration/react";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <ThemeProvider>
     <Provider store={store}>
         <PersistGate persistor={persistor} loading={null}>
            <App />
         </PersistGate>
     </Provider>
    </ThemeProvider>
  </StrictMode>
);

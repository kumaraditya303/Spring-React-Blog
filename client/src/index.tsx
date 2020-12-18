import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import App from "./components/LoginForm";
import "./index.scss";
import store from "./store";
import "bootstrap";
ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById("root")
);

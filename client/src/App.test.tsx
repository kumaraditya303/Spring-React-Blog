import { render } from "@testing-library/react";
import React from "react";
import { Provider } from "react-redux";
import App from "./components/App";
import store from "./store";
test("renders without crashing", () => {
  render(
    <Provider store={store}>
      <App />
    </Provider>
  );
});

import { render } from "@testing-library/react";
import React from "react";
import App from "./components/App";

test("renders without crashing", () => {
  render(<App />);
});

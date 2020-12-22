import React, { Component } from "react";
import { BrowserRouter, Switch } from "react-router-dom";
import PublicRoute from "../../utils/PublicRoute";
import Home from "../Home";
import Layout from "../Layout";
import LoginForm from "../LoginForm";
class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <Layout>
          <Switch>
            <PublicRoute exact path="/login/">
              <LoginForm />
            </PublicRoute>
            <PublicRoute exact path="/">
              <Home />
            </PublicRoute>
          </Switch>
        </Layout>
      </BrowserRouter>
    );
  }
}

export default App;

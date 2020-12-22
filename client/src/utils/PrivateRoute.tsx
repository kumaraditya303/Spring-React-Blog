import React from "react";
import { connect } from "react-redux";
import { Redirect, Route } from "react-router-dom";
import { RootState } from "../store";
const PrivateRoute = ({ children, authenticated, ...rest }: any) => (
  <Route
    {...rest}
    render={({ location }) =>
      authenticated ? (
        children
      ) : (
        <Redirect
          to={{
            pathname: "/login",
            state: { from: location },
          }}
        />
      )
    }
  />
);

const mapStateToProps = (state: RootState) => ({
  authenticated: state.auth.token !== null,
});

export default connect(mapStateToProps)(PrivateRoute);

import React from "react";
import { connect } from "react-redux";
import { Redirect, Route } from "react-router-dom";
import { RootState } from "../store";
const PublicRoute = ({ children, authenticated, ...rest }: any) => (
  <Route
    {...rest}
    render={({ location }) =>
      authenticated ? (
        <Redirect
          to={{
            pathname: (location as any).state?.from.pathname ?? "/",
            state: { from: location },
          }}
        />
      ) : (
        children
      )
    }
  />
);

const mapStateToProps = (state: RootState) => ({
  authenticated: state.auth.token !== null,
});
export default connect(mapStateToProps)(PublicRoute);

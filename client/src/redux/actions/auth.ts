import axios from "axios";
import { getUser } from "../../utils/utils";
import { AuthActionType, Dispatch } from "../types/types";
export const loginUser = (username: string, password: String) => (dispatch: Dispatch) => {
  axios
    .post("/login", { username, password })
    .then((res) => {
      const token = res.data.token;
      console.log(token);
      sessionStorage.setItem("token", token);
      getUser().then((user) => dispatch({ type: AuthActionType.AUTH_LOGIN, token, user }));
    })
    .catch((error) => dispatch({ type: AuthActionType.AUTH_ERROR, error: error.response.data.error }));
};

export const logoutUser = () => (dispatch: Dispatch) => {
  sessionStorage.clear();
  dispatch({ type: AuthActionType.AUTH_LOGOUT });
};

export const registerUser = (
  username: string,
  password: string,
  firstName: string,
  lastName: string,
  email: string
) => (dispatch: Dispatch) => {
  axios
    .post("/register", { username, password, firstName, lastName, email })
    .then((res) => {
      const token = res.data.token;
      getUser().then((user) => {
        dispatch({ type: AuthActionType.AUTH_LOGIN, token, user });
      });
    })
    .catch((error) => dispatch({ type: AuthActionType.AUTH_ERROR, error: error.response.data.error }));
};

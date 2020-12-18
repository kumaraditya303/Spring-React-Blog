import type { Action } from "../types/types";
import { AuthActionType } from "../types/types";
export type AuthState = {
  user: Record<string, any> | null;
  token: string | null;
  error: Record<string, any> | null;
};

const initialState: AuthState = {
  user: null,
  token: null,
  error: null,
};

const reducer = (state: AuthState = initialState, action: Action): AuthState => {
  switch (action.type) {
    case AuthActionType.AUTH_LOGIN:
      return { ...state, token: action.token, user: action.user };
    case AuthActionType.AUTH_LOGOUT:
      return { ...state, token: null, user: null };
    case AuthActionType.AUTH_ERROR:
      return { ...state, token: null, user: null, error: action.error };
    default:
      return state;
  }
};

export default reducer;

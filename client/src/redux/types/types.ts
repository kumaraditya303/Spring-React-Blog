export enum AuthActionType {
  AUTH_LOGIN,
  AUTH_LOGOUT,
  AUTH_ERROR,
}
type AuthLoginAction = {
  type: AuthActionType.AUTH_LOGIN;
  token: string;
  user: Record<string, any>;
};
type AuthLogoutAction = {
  type: AuthActionType.AUTH_LOGOUT;
};
type AuthErrorAction = {
  type: AuthActionType.AUTH_ERROR;
  error: Record<string, any>;
};
export type Action = AuthLoginAction | AuthLogoutAction | AuthErrorAction;
export type Dispatch = (dispatch: Action | ThunkAction | PromiseAction) => any;
export type GetState = () => Object;
export type ThunkAction = (dispatch: Dispatch, getState: GetState) => any;
export type PromiseAction = Promise<Action>;

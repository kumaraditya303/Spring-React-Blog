import classnames from "classnames";
import { ChangeEvent, Component, FormEvent } from "react";
import { connect } from "react-redux";
import { loginUser } from "../../redux/actions/auth";
import { RootState } from "../../store";
import styles from "./style.module.scss";
type State = {
  [x: string]: string;
};
type Props = {
  error: Record<string, any> | null;
  login: (username: string, password: string) => void;
};

class LoginForm extends Component<Props, State> {
  state = {
    username: "",
    password: "",
  };
  handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    this.props.login(this.state.username, this.state.password);
  };
  handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    this.setState({ [event.target.name]: event.target.value });
  };
  render() {
    const { error } = this.props;
    return (
      <div className={styles.login__form}>
        <form onSubmit={this.handleSubmit}>
          <h1 className="display-1 text-center mb-3">Login</h1>
          <div className="form-floating mb-3">
            <input
              type="text"
              className={classnames("form-control", { "is-invalid": error?.username })}
              id="usernameField"
              name="username"
              placeholder="username"
              required
              // minLength={8}
              onChange={this.handleChange}
            />
            <label htmlFor="usernameField">{error?.username ?? "Username"}</label>
          </div>
          <div className="form-floating mb-3">
            <input
              type="password"
              className={classnames("form-control", { "is-invalid": error?.password })}
              id="passwordField"
              placeholder="password"
              required
              name="password"
              // minLength={8}
              onChange={this.handleChange}
            />
            <label htmlFor="passwordField">{error?.password ?? "Password"}</label>
          </div>
          <div className="d-grid gap-2">
            <input type="submit" className="btn btn-primary" value="Login" />
          </div>
        </form>
      </div>
    );
  }
}

const mapStateToProps = (state: RootState) => ({
  error: state.auth.error,
});

const mapDispatchToProps = (dispatch: any) => ({
  login: (username: string, password: string) => dispatch(loginUser(username, password)),
});

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);

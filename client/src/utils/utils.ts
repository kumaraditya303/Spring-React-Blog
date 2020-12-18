import axios from "axios";
export const getUser = async (): Promise<Record<string, any>> => {
  const res = await axios.get("/user", {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  });
  const user = res.data;
  sessionStorage.setItem("user", JSON.stringify(user));
  return user;
};

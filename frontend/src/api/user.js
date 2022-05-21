import axios from "./http.js";

function insertUser(user, success, fail) {
  console.log("inser 들어옴");
  console.log(user);
  axios.post(`/user/`, JSON.stringify(user)).then(success).catch(fail);
}

// JSON.stringify -> JavaScript 값이나 객체를 JSON 문자열로 변환
async function login(user, success, fail) {
  console.log("api - login");
  console.log(JSON.stringify(user));
  await axios
    .post("/user/login", JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(id, success, fail) {
  axios.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await axios.get(`/user/info/${id}`).then(success).catch(fail);
}

export { insertUser, login, findById };

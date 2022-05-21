import api from "./http.js";

// Read ( List )
function listBoard(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

// Read ( One )
function selectBoard(no, success, fail) {
  api.get(`/board/${no}`).then(success).catch(fail);
}

// Create
function insertBoard(board, success, fail) {
  api.post(`/board`, JSON.stringify(board)).then(success).catch(fail);
}

// Update
function updateBoard(board, success, fail) {
  api
    .put(`/board/${board.no}`, JSON.stringify(board))
    .then(success)
    .catch(fail);
}

// Delete
function deleteBoard(no, success, fail) {
  api.delete(`/board/${no}`).then(success).catch(fail);
}

export { listBoard, selectBoard, insertBoard, updateBoard, deleteBoard };

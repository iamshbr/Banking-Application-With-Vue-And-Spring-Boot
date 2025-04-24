export default {
  ADD_TRANSACTION(state, data) {
    state.user.transactions.unshift({
      amount: data.amount,
      date: data.date
    })

    const userData = state.user;
    sessionStorage.setItem("user", JSON.stringify(userData));
  },
  LOGIN(state, data) {
    state.user = data["user"];
    state.status = true;
    sessionStorage.setItem("token", data["token"]);
    sessionStorage.setItem("user", JSON.stringify(data["user"]));
  },
  LOGOUT(state, data) {
    state.user = null;
    state.status = false;
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("user");
  },
  SET_LOGIN_ERROR(state, message) {
    state.loginError = message;
  },
  SET_TRANSFER_ERROR(state, message) {
    state.transferError = message;
  },
  SET_LOAN_ERROR(state, message) {
    state.loanError = message;
  },
  SET_ACCOUNT_CLOSE_ERROR(state, message) {
    state.accountCloseError = message;
  },
  RESET_TIMER(state, timer) {
    clearInterval(state.timeInterval);
    state.timer = timer;
    state.timeInterval = null;
  },
  START_TIMER(state) {
    state.timeInterval = setInterval(() => {
      state.timer--;
    }, 1000)
  }
}
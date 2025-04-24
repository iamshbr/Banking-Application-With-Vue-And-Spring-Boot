export default {
  getUserStatus: state => state.status,
  getUser: state => state.user,
  getLoginError: state => state.loginError,
  getTransferError: state => state.transferError,
  getAccountCloseError: state => state.accountCloseError,
  getLoanError: state => state.loanError,
  getTimer: state => state.timer,
}
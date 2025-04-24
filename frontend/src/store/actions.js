
import commonFetch from "../composable/commonFetch";
import { API_URLS } from "../utils/baseURL";

export default {
  addTransaction({ commit }, transaction) {
    commit('ADD_TRANSACTION', transaction)
  },
  async login({ commit }, { username, password }) {
    try {
      const response = await commonFetch(API_URLS.login, "post", { "Content-Type": 'application/json' }, { username, password });
      if (response.ok) {
        const data = await response.json();
        commit('LOGIN', data);
        commit("START_TIMER");
      } else {
        const text = await response.text();
        throw new Error(text);
      }
    } catch (error) {
      commit("SET_LOGIN_ERROR", error.message);
    }
  },

  userLoggedIn({ commit }, userData) {
    commit('LOGIN', userData);
  },

  logout({ commit }, data) {
    commit('LOGOUT', data);
    commit('RESET_TIMER', 60 * 3)
  },

  async transferMoney({ commit }, { sender, receiver, amount }) {
    try {
      const headers = { "Content-Type": 'application/json', "Authorization": `Bearer ${sessionStorage.getItem("token")}` };
      const response = await commonFetch(API_URLS.transferMoney, "post", headers, { sender, receiver, amount });
      if (response.ok) {
        const transactionData = await response.json();
        commit('ADD_TRANSACTION', transactionData);
      } else {
        const text = await response.text();
        throw new Error(text);
      }
    } catch (error) {
      commit('SET_TRANSFER_ERROR', error.message);
    }
  },

  async loanRequest({ commit }, { requestor, amount }) {
    try {
      const headers = { "Content-Type": 'application/json', "Authorization": `Bearer ${sessionStorage.getItem("token")}` };
      const response = await commonFetch(API_URLS.loanRequest, "post", headers, { requestor, amount });
      if (response.ok) {
        const transactionData = await response.json();
        commit('ADD_TRANSACTION', transactionData);
      } else {
        commit('SET_LOAN_ERROR', "Some problem occurred!");
      }
    } catch (error) {
      console.error(error)
      commit('SET_LOAN_ERROR', error.message);
    }
  },

  async accountClose({ commit }, { username, password }) {
    try {
      const headers = { "Content-Type": 'application/json', "Authorization": `Bearer ${sessionStorage.getItem("token")}` };
      const response = await commonFetch(API_URLS.accountClose, "post", headers, { username, password });
      if (response.ok) {
        const isUserActive = await response.text();
        if (isUserActive === "1") {
          commit('SET_ACCOUNT_CLOSE_ERROR', "User is now InActive");
          setTimeout(() => commit('LOGOUT', null), 2000)
        } else {
          commit('SET_ACCOUNT_CLOSE_ERROR', "Some problem occurred!");
        }
      } else {
        const text = await response.text();
        throw new Error(text);
      }
    } catch (error) {
      commit('SET_ACCOUNT_CLOSE_ERROR', error.message);
    }
  },

  updateTransferErrorValue({ commit }, errText) {
    commit("SET_TRANSFER_ERROR", errText);
  },

  updateLoanRequestErrorValue({ commit }, errText) {
    commit("SET_LOAN_ERROR", errText);
  },

  updateAccountCloseErrorValue({ commit }, errText) {
    commit('SET_ACCOUNT_CLOSE_ERROR', errText);
  },
}
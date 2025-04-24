const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export const API_URLS = {
  login: `${BASE_URL}/auth/login`,
  logout: `${BASE_URL}/auth/logout`,
  transferMoney: `${BASE_URL}/transaction/transferMoney`,
  loanRequest: `${BASE_URL}/transaction/loanRequest`,
  accountClose: `${BASE_URL}/users/accountClose`,
};
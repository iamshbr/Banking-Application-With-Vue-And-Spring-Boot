import { createStore } from 'vuex';
import { actions, mutations, state, getters } from "./";

const store = createStore({
  state,
  mutations,
  actions,
  getters
})

export const { addTransaction } = store._actions;
export default store;

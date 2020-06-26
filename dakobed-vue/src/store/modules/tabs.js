import axios from 'axios';

const state = {
  tabs: [],

};

const getters = {
  getTabs: state => state.tabs,

};

const actions = {
  
  async fetchTabs({ commit }) {
    const TABS_IP = process.env('')
    console.log("SNOTEL IP " + SNOTEL_IP)
    const response = await axios.get('http://localhost:8085/products/');
    commit('setTabs', response.data);
  },

  };

const mutations = {
    setTabs: (state, tabs) => (state.tabs = tabs),

};

export default {
  state,
  getters,
  actions,
  mutations
};

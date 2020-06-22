import axios from 'axios';

const state = {
  locations: [],

};

const getters = {
  g: state => state.locations,

};

const actions = {
  
  async fetchProducts({ commit }) {
    const SNOTEL_IP = process.env('')
    console.log("SNOTEL IP " + SNOTEL_IP)
    const response = await axios.get('http://localhost:8085/products/');
    commit('setLocations', response.data);
  },

  };

const mutations = {
    setLocations: (state, locations) => (state.locations = locations),

};

export default {
  state,
  getters,
  actions,
  mutations
};

import axios from 'axios';

const state = {
  loadedProducts: [],

};

const getters = {
  allProducts: state => state.loadedReports,

};

const actions = {

  async fetchProducts({ commit }) {
    const response = await axios.get('http://localhost:8085/products/');
    commit('setReports', response.data);
  },

  };

const mutations = {
  setProducts: (state, products) => (state.loadedProducts = products),

};

export default {
  state,
  getters,
  actions,
  mutations
};

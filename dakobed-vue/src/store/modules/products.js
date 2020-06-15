import axios from 'axios';

const state = {
  loadedProducts: [],

};

const getters = {
  allProducts: state => state.loadedProducts,

};

const actions = {

  async fetchProducts({ commit }) {
    const response = await axios.get('http://localhost:8085/products/');
    commit('setProducts', response.data);
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

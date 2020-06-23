import axios from 'axios';

const state = {
  locations: [],
  pipelines_selection: 'tweets'

};

const getters = {
  g: state => state.locations,
  getPipelineSelection: state => state.pipelines_selection

};

const actions = {
  
  async fetchProducts({ commit }) {
    const SNOTEL_IP = process.env('')
    console.log("SNOTEL IP " + SNOTEL_IP)
    const response = await axios.get('http://localhost:8085/products/');
    commit('setLocations', response.data);
  },

  async setPipelineSelection({ commit }, selection) {
    commit('setPipelineSelection', selection);
  },


};

const mutations = {
    setLocations: (state, locations) => (state.locations = locations),
    setPipelineSelection: (state,selection) => (state.pipelines_selection = selection)

};

export default {
  state,
  getters,
  actions,
  mutations
};

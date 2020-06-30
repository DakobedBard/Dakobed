import axios from 'axios';

const state = {
  tabs: [],

};

const getters = {
  getTranscription: state => state.transcription,

};

const actions = {
  
    async fetchTranscription({ commit }) {
        const response = await axios.get('http://localhost:8081/hello');
        commit('setTranscription', response.data);
      },

  };

const mutations = {
    setTranscription: (state, transcription) => (state.transcription = transcription),

};

export default {
  state,
  getters,
  actions,
  mutations
};

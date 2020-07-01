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
        const response2 = await axios.get('http://localhost:8081/transcription');
        console.log(response2)
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

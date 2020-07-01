import axios from 'axios';

const state = {
  transcription: [],

};

const getters = {
  getTranscription: state => state.transcription,

};

const actions = {
  
  async fetchTranscription({ commit }) {
    const response = await axios.get('http://localhost:8081/transcription');
    commit('setTranscription', response.data);
  },

  async postTripreport({commit}){
    axios.get('http://localhost:8081/transcription').then((response) => {
      
    commit('createReport',response.data)
      console.log(response);
    }, (error) => {
      console.log(error);
    });
  } 


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

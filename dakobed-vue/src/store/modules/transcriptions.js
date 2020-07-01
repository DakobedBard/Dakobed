import axios from 'axios';

const state = {
  transcription: [],
  notes: []

};

const getters = {
  getTranscription: state => state.transcription,
  getNotes: state => state.notes
};

const actions = {
  
  async fetchTranscription2({ commit }) {
    const response = await axios.get('http://localhost:8081/transcription');
    commit('setTranscription', response.data);
  },

  async fetchTranscription({commit}){
    axios.get('http://localhost:8081/transcription').then((response) => {
      
      commit('setTranscription',response.data)

      var response_string = JSON.stringify(response.data.notes)
      var notes = JSON.parse(response_string)
      
      commit('setNotes', notes)

      console.log(notes);
    }, (error) => {
      console.log(error);
    });
  } 


};

const mutations = {
    setTranscription: (state, transcription) => (state.transcription = transcription),
    setNotes: (state, notes) => (state.notes = notes),

};

export default {
  state,
  getters,
  actions,
  mutations
};

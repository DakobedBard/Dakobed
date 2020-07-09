import axios from 'axios';
// import { compile } from 'vue-template-compiler';

const state = {
  notes: [],
  trainingData:[]
};

const getters = {
  getNotes: state => state.notes,
  getTrainingData: state => state.trainingData
};

const actions = {
  
  async fetchTrainingData({commit}){
      axios.get("http://localhost:8081/guitarset").then((response) => {

        var response_string = JSON.stringify(response.data)
        var data = JSON.parse(response_string)
        commit('setTrainingData', data)
        console.log("the length of the data " + data.length)
      }, (error) => {
        console.log(error);
      });
  },

  async getS3Transcription({commit}){
    var notesArray = []
    commit('setNotes', notesArray)

    // axios.get("https://dakobed-guitarset.s3-us-west-2.amazonaws.com/fileID" + fileID + "/" + fileID + "transcription.json").then((response) => {
      axios.get("https://dakobed-guitarset.s3-us-west-2.amazonaws.com/fileID3/3transcription.json").then((response) => {
      console.log(response)
      var response_string = JSON.stringify(response.data)
      var notes = JSON.parse(response_string)
      console.log("The notes array is " + notes.coronavirusconstructor == Array)
      var a = typeof notes
      console.log("the type of notes is " + a)
      console.log(notes[0].midi)
      var nnotes = notes.length
      var notesArray = []
      var i ;
      var note;
      for (i = 0; i < nnotes; i++) {
        // console.log(notes[i])
        note = notes[i]
        notesArray.push([note.measure, note.beat, note.midi, note.string])
      } 
      console.log("The number of notes is " + notes.length)
      commit('setNotes', notesArray)

    }, (error) => {
      console.log(error);
    });

  },


  async fetchTranscription({commit}){
    axios.get('http://localhost:8081/transcription').then((response) => {
      
      var response_string = JSON.stringify(response.data.notes)
      var notes = JSON.parse(response_string)
      // console.log("The notes array is " + notes.coronavirusconstructor == Array)
      // var a = typeof notes
      // console.log("the type of notes is " + a)
      // console.log(notes[0].midi)
      var nnotes = notes.length
      var notesArray = []
      var i ;
      var note;
      for (i = 0; i < nnotes; i++) {
        // console.log(notes[i])
        note = notes[i]
        notesArray.push([note.measure, note.beat, note.midi, note.string])
      } 

      commit('setNotes', notesArray)

    }, (error) => {
      console.log(error);
    });
  } 


};

const mutations = {

    setNotes: (state, notes) => (state.notes = notes),
    setTrainingData: (state, trainingData) => (state.trainingData = trainingData),
};

export default {
  state,
  getters,
  actions,
  mutations
};

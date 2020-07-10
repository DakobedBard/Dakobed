import axios from 'axios';


const state = {
  notes: [],
  trainingData:[],
  maestroTrainingData:[],
  lines:[],
  nmeasures:-1
};

const getters = {
  getNotes: state => state.notes,
  getTrainingData: state => state.trainingData,
  getLines:state => state.lines,
  getNMeasures: state => state.getNMeasures,
  getMaestroTraningData: state => state.maestroTrainingData
};

const actions = {

    
  async fetchMaestroTranscription({commit}){
    axios.get("http://localhost:8081/maestroExample").then((response) => {

      var response_string = JSON.stringify(response.data)
      var notes = JSON.parse(response_string)

      var notesArray = []
      var i ;
      var note;
      for (i = 0; i < notes.length; i++) {
        console.log(notes[i])
        note = notes[i]
        notesArray.push([note.measure, note.beat, Math.floor(note.midi), note.string])
      } 


      commit('setNotes', notesArray)

    }, (error) => {
      console.log(error);
    });
},

async fetchMaestroTrainingData({commit}){
  axios.get("http://localhost:8081/maestro").then((response) => {
    
    var response_string = JSON.stringify(response.data)
    var data = JSON.parse(response_string)
    console.log("yess indeed " + data.length )
    commit('setMaestroTrainingData', data)

  }, (error) => {
    console.log(error);
  });
},



  async fetchTrainingData({commit}){
      axios.get("http://localhost:8081/guitarset").then((response) => {

        var response_string = JSON.stringify(response.data)
        var data = JSON.parse(response_string)
        commit('setTrainingData', data)

      }, (error) => {
        console.log(error);
      });
  },

  async getS3Transcription({commit}, fileID){

    // axios.get("https://dakobed-guitarset.s3-us-west-2.amazonaws.com/fileID" + fileID + "/" + fileID + "transcription.json").then((response) => {
      axios.get("https://dakobed-guitarset.s3-us-west-2.amazonaws.com/fileID" + fileID+"/"+fileID +"transcription.json").then((response) => {
      console.log(response)
      var response_string = JSON.stringify(response.data)
      var notes = JSON.parse(response_string)
      console.log(notes[0].midi)
      var nnotes = notes.length
      var notesArray = []
      var i ;
      var note;
      for (i = 0; i < nnotes; i++) {
        // console.log(notes[i])
        note = notes[i]
        notesArray.push([note.measure, note.beat, Math.floor(note.midi), note.string])
      } 
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
        notesArray.push([note.measure, note.beat, Math.floor(note.midi), note.string])
      } 

      commit('setNotes', notesArray)

    }, (error) => {
      console.log(error);
    });
  } 


};

const mutations = {

    setNotes: (state, notes) => {
      state.notes = notes

      var nmeasures = notes[notes.length -1][0] 
      var measures_per_line = 3
      var nlines = Math.floor(nmeasures/measures_per_line)
      if(nmeasures % 4 != 0){
          nlines +=1
      }
      var lines = []
      var i
      var current_note_index = 0;
      var lowest_measure = 0;
      var highest_measure = measures_per_line;
      var current_measure = 0;
      for(i =0; i < nlines; i++){
        var line = []
        while(current_measure >= lowest_measure && current_measure < highest_measure && current_note_index < notes.length ) {
            current_measure = notes[current_note_index][0]
            current_note_index+=1
            line.push(notes[current_note_index])   
        } 
        lines.push({id:i, notes:line})
        lowest_measure += measures_per_line
        highest_measure += measures_per_line
      }
      state.nmeasures = nmeasures
      state.lines = lines

    },
    setTrainingData: (state, trainingData) => (state.trainingData = trainingData),
    setMaestroTrainingData: (state, trainingData) => (state.maestroTrainingData = trainingData),
};

export default {
  state,
  getters,
  actions,
  mutations
};

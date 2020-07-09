<template>
    <div class="text-center">
      
      <v-card>
        <v-card-title>
            GuitarSet transcription training dataddf

         </v-card-title>
      </v-card>
      
      <v-data-table dense :headers="headers" :items="data" item-key="fileID" class="elevation-1"></v-data-table>



      <TranscriptionDetail v-bind:fileID="3" />
    </div>
</template>

<script>

import TranscriptionDetail from './TranscriptionDetail'
import { mapGetters, mapActions } from "vuex";
import axios from 'axios';


export default {
    
    created(){
      this.fetchTrainingData()
      axios.get("http://localhost:8081/guitarset").then((response) => {

        var response_string = JSON.stringify(response.data)
        var data = JSON.parse(response_string)
        this.data = data
        console.log("the length of the this.data " + this.data.length)
      }, (error) => {
        console.log(error);
      });
    },

    components:{
        TranscriptionDetail

    },
    mounted() {
      console.log("Hello")
      console.log(this.$el.textContent) // I'm text inside the component.
    },

    computed:{
      ...mapGetters(["getTrainingData"])
    },

    methods:{
      ...mapActions(["fetchTrainingData"]),    

      nextPage(){
         this.pageNumber++;
      },
      prevPage(){
        this.pageNumber--;
    }
  },
  props:{

  },

  data(){
    return {

        trainingData: [],

        page: 1,
      
      headers: [
        {
          text: 'Training Example',
          align: 'start',
          sortable: false,
          value: 'title',
        },


      ],
    }
  }


  
}
</script>
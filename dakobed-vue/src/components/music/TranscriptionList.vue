<template>
    <div class="text-center">
      
      <v-card>
        <v-card-title>
            GuitarSet Transcription Training Examples

         </v-card-title>
      </v-card>
      
    <v-data-table
        v-model="selected"  
        :headers="headers"
        :items="getTrainingData"
      > 
      <template v-slot:item="{ item }">
            <tr @click="rowClicked(item)">
                <td>{{item.title}}</td>
            </tr>

        </template>

      </v-data-table>

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

    },

    computed:{
      ...mapGetters(["getTrainingData"])
    },

    methods:{

      rowClicked(item){
        console.log(item)
      },
      ...mapActions(["fetchTrainingData"]),    

  },
  props:{

  },

  data(){
    return {
      selected:[],
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
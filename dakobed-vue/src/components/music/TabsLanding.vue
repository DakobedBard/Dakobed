<template>
  <div>

    <v-container fluid>
     <v-layout row>
        <v-flex md9>
          <div v-if="transcription_type=='guitar'">
              Hey
          </div>


          <Transcription v-bind:transcription_type="transcription_type" v-bind:notes="getNotes"/>          
        </v-flex>
        <v-flex md3>

  
        </v-flex>
      </v-layout> 
    </v-container>

  </div>
</template>

<script>


import { mapGetters, mapActions } from "vuex";

import Transcription from './Transcription'
export default {

    components:{
        Transcription
    },
    created(){
      //this.fetchTranscription()
      if(this.transcription_type == "guitar"){
        this.getS3Transcription(2)
      }else{
        this.fetchMaestroTranscription()
      }
    },
    data () {

      return {
        transcription_type:"guitar"
      
      }
    },

    
    methods:{
        ...mapActions(["fetchTranscription"]),
        ...mapActions(["getS3Transcription"]),
        ...mapActions(["fetchMaestroTranscription"]),        
        selectMenuItem(selection){

          this.setGallerySelection(selection)
        },
        // changeSelection(item_selection){
        //     this.selection = item_selection
        //     console.lo
        // },
    },
    computed: {
      ...mapGetters(["getNotes"])
    }
  }
</script>

<style>
  .spacing-playground .v-select .v-input__prepend-outer, .spacing-playground .v-select .v-input__append-outer{
    margin-top: 0.55rem;
    margin-right: 0.2rem;
  }
</style>
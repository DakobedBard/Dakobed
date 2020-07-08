<template>
    <div class="text-center">
      
      <v-card>
        <v-card-title>
            GuitarSet transcription training data      
         </v-card-title>
      </v-card>
      
      <v-pagination dark
        v-model="page"
        :length="8"

        circle
      ></v-pagination>
      <TranscriptionDetail v-bind:fileID="3" />
    </div>
</template>

<script>

import TranscriptionDetail from './TranscriptionDetail'

export default {
    
    components:{
        TranscriptionDetail

    },


    methods:{


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
        page: 1,
    }
  }
}
</script>
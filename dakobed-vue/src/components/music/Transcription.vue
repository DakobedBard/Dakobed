<template>
  <v-container>
    <v-layout>
      <v-flex>
        <div v-if="transcription_type=='guitar'">
          <v-card flat class="pa-3" v-for="line in this.lines" :key="line.id" >
            <TabLine  v-bind:notes="line.notes"/>
          </v-card>
        </div>
        <div v-if="transcription_type=='piano'">

          <v-card flat class="pa-3" v-for="line in this.lines" :key="line.id" >
            <PianoLine  v-bind:notes="line.notes"/>
          </v-card>
        </div>


      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>

import TabLine from './TabLine'
import PianoLine from './PianoLine'

export default {
    components:{
      TabLine,
      PianoLine
    },
  
    props:{
      notes:Array,
      transcription_type:String
    },
    created(){
        this.nmeasures = this.notes[this.notes.length -1][0] 
        var measures_per_line = 3
          var nlines = Math.floor(this.nmeasures/measures_per_line)
          if(this.nmeasures % 4 != 0){
              nlines +=1
          }
          this.lines = []
          var i
          var current_note_index = 0;
          var lowest_measure = 0;
          var highest_measure = measures_per_line;
          var current_measure = 0;
          for(i =0; i < nlines; i++){
            var line = []
            while(current_measure >= lowest_measure && current_measure < highest_measure
            && current_note_index < this.notes.length ) {
              current_measure = this.notes[current_note_index][0]
              current_note_index+=1
              line.push(this.notes[current_note_index])   
            } 
            this.lines.push({id:i, notes:line})
            lowest_measure += measures_per_line
            highest_measure += measures_per_line
          }
    },

}
</script>
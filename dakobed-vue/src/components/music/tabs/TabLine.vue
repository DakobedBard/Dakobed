<template>
    
  <v-container>
    <v-layout>
      <v-flex>  
          <div id="tab">
        </div>
      </v-flex>
    </v-layout>
  </v-container>

</template>

<script>

import Vex from 'vexflow';
// import { mapGetters, mapActions } from "vuex";

const GuitarMidiFret = [
    [65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,70],
    [59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74],
    [55,56,57,58,59,50,51,52,53,54,55,56,57,58,59,60],
    [50,51,52,53,54,55,56,57,58, 59,50,51,53,54,55],
    [45,46,47,48,49,40,41,42,43,44,45,46,47,48,49,50],
    [40, 41,42,43,43,44,45,46,47,48,49,50,51,52,53,54,55]    
]






export default {

    props:{
        notes:Array
    },

    data () {
      return {
      
      
      }
    },    
    
    
    created(){
        console.log(GuitarMidiFret)

    },



    mounted(){

        const VF = Vex.Flow;
        var div = document.getElementById("tab")
        var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
        // Size our SVG:
        renderer.resize(1000, 130);
        // And get a drawing context:
        var context = renderer.getContext();
        // Create a stave at position 10, 40 of width 400 on the canvas.
        
        var notesArray = []
        this.notes.forEach(note=>{
            var midi = note[2]
            var string = note[3]
            var fret = GuitarMidiFret[string].indexOf(midi)
            console.log("note measure " + midi + "  " + string)
            console.log("The fret at which " + midi +  " occurs on string " + string + " is at " + fret)


            var tabnote = new VF.TabNote({
                positions: [{str: string+1, fret: fret}],
                duration: "q"
                })
            
            notesArray.push(tabnote)


        })
        
        // var notesArray = [
        // // A single note
        // new VF.TabNote({
        //     positions: [{str: 0, fret: 2}],
        //     duration: "q"}),
        // ];

        var stave = new VF.TabStave(10, 0, 900);
        stave.addClef("tab").setContext(context).draw();

        VF.Formatter.FormatAndDraw(context, stave, notesArray);

    },
    computed: {

    }
}
</script>
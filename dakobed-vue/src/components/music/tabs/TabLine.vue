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


export default {

    props:{

    },

    data () {
      return {
      
      
      }
    },

    methods:{

    
    },
    created(){


    },



    mounted(){
        this.nmeasures = 4
        var nlines = Math.floor(this.nmeasures/4)
        if(this.nmeasures % 4 != 0){
            nlines +=1
        }
        console.log("The number of lines is " + nlines)

        const VF = Vex.Flow;
        var div = document.getElementById("tab")
        var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
        // Size our SVG:
        renderer.resize(1000, 120);
        // And get a drawing context:
        var context = renderer.getContext();
        // Create a stave at position 10, 40 of width 400 on the canvas.
        var notesArray = [
        // A single note
        new VF.TabNote({
            positions: [{str: 3, fret: 7}],
            duration: "q"}),
        new VF.TabNote({
            positions: [{str: 3, fret: 7}],
            duration: "q"}),

        new VF.TabNote({
            positions: [{str: 3, fret: 7}],
            duration: "q"}),
        new VF.TabNote({
            positions: [{str: 3, fret: 7}],
            duration: "q"}),
        // A chord with the note on the 3rd string bent

        ];

        var stave = new VF.TabStave(10, 0, 900);
        stave.addClef("tab").setContext(context).draw();

        console.log(notesArray)
        VF.Formatter.FormatAndDraw(context, stave, notesArray);

    },
    computed: {

    }
}
</script>
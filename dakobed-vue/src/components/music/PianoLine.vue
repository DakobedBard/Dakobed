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
        notes:Array
    },

    data () {
      return {
       
      }
    },    
    
    created(){
        
    },

    mounted(){
        
        while(this.notes[this.notes.length-1] == undefined){
          this.notes.pop()
        }
        
        const VF = Vex.Flow;
        var div = document.getElementById("tab")
        var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
        renderer.resize(1500, 180);
        var context = renderer.getContext();

        var pianostave2 = new VF.Stave(10, 0, 1200);
        pianostave2.addClef("treble").addTimeSignature("4/4");
        pianostave2.setContext(context).draw();


        var pianostave = new VF.Stave(10, 80, 1200);
        pianostave.addClef("bass").addTimeSignature("4/4");
        pianostave.setContext(context).draw();


        var notes = [
        new VF.StaveNote({clef: "treble", keys: ["c/4"], duration: "q" }),
        new VF.StaveNote({clef: "treble", keys: ["d/4"], duration: "q" }),
        new VF.StaveNote({clef: "treble", keys: ["b/4"], duration: "qr" }),
        new VF.StaveNote({clef: "treble", keys: ["c/4", "e/4", "g/4"], duration: "q" })
      ];

      // Create a voice in 4/4 and add the notes from above
      var voice = new VF.Voice({num_beats: 4,  beat_value: 4});
      voice.addTickables(notes);

      // Format and justify the notes to 400 pixels.
      var formatter = new VF.Formatter().joinVoices([voice]).format([voice], 400);
      // console.log(formatter)
      // Render voice
      voice.draw(context, pianostave2);
      formatter.getElementById
    },
    computed: {

    }
}
</script>
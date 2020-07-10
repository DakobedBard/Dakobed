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

MidiKeyMap = {33:'a1',34:'a1',35:'b1',36:'c2',37:'c2',38:'d2', 39:'d2', 40:'e2',41:'f2',42:'f2',
  43:'g2',44:'g2',45:'a2',46:'a2','b2':47,'c3':48, 49:'c3',50:'d3',51:'d3',52:'e3',53:'f3',54:'f3',
  55:'g3',56:'g3',57:'a3',58:'a3', 59:'b3',60:'c4',61:'c4',62:'d4',63:'d4', 64:'e4', 65:'f4',66:'g4',
  67:'g4', 68:'a4',69:'a4',70:'b4',71:'c5',72:'c5',73:'d5',74:'d5',75:'e5', 76:'f5', 77:'g5', 78:'g5', 
  79:'a5', 80:'a5', 81:'b5', 82:'c6'
}



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



        var notesArray = []
        // console.log(notesArray)
        var i =0
        while(i< 5){//this.notes.length){
        
          var keys = []
          console.log(keys)  
          var j =i
          var note
          while(this.notes[j]!= undefined && this.notes[j][1] == this.notes[i][1]){
            note = this.notes[j]
            console.log(note)
            // console.log("this.notes[j] " + this.notes[j][3] )

            // positions.push({str: Math.floor(this.notes[j][3])+1, fret: fret})
            j+=1
          }
          var pianonote = new VF.StaveNote({clef: "treble", keys: ["c/4"], duration: "q" })
          // var tabnote = new VF.TabNote({positions: positions, duration: "q"})
          notesArray.push(pianonote)   
          i+=1
          
        }
      //   var notes = [
      //   new VF.StaveNote({clef: "treble", keys: ["c/4"], duration: "q" }),
      //   new VF.StaveNote({clef: "treble", keys: ["d/4"], duration: "q" }),
      //   new VF.StaveNote({clef: "treble", keys: ["b/4"], duration: "qr" }),
      //   new VF.StaveNote({clef: "treble", keys: ["c/4", "e/4", "g/4"], duration: "q" })
      //  ];

      // Create a voice in 4/4 and add the notes from above
      var voice = new VF.Voice({num_beats: 5,  beat_value: 4});
      voice.addTickables(notesArray);

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
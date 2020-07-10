<template>
    
  <v-container>
    <v-layout>
      <v-flex>  
          <div id="tab">  
          </div>
          {{firstMeasure}}
      </v-flex>
    </v-layout>
  </v-container>

</template>

<script>

import Vex from 'vexflow';
// import { mapGetters, mapActions } from "vuex";

const MidiKeyMap = {28:'e/1',29:'f/1',30:'f/1',31:'g/1',32:'g/1', 33:'a/1',34:'a/1',35:'b/1',36:'c/2',37:'c/2',38:'d/2', 39:'d/2', 40:'e/2',41:'f/2',42:'f/2',
  43:'g/2',44:'g/2',45:'a/2',46:'a/2',47:'b/2',48:'c/3', 49:'c/3',50:'d/3',51:'d/3',52:'e/3',53:'f/3',54:'f/3',
  55:'g/3',56:'g/3',57:'a/3',58:'a/3', 59:'b/3',60:'c/4',61:'c/4',62:'d/4',63:'d/4', 64:'e/4', 65:'f/4',66:'g/4',
  67:'g/4', 68:'a/4',69:'a/4',70:'b/4',71:'c/5',72:'c/5',73:'d/5',74:'d/5',75:'e/5', 76:'f/5', 77:'g/5', 78:'g/5', 
  79:'a/5', 80:'a/5', 81:'b/5', 82:'c/6',83:'c/6',84:'d/6',85:'d/6',86:'e/6'
}

const DurationMap = {0:'16', 1:'8',2:'q', 3:'q', 4:'h',5:'h',6:'h'}

console.log(MidiKeyMap[44])
console.log(DurationMap)

export default {

    props:{
        notes:Array
    },

    data () {
      return {
        firstMeasure:this.notes[0][0]
      }
    },    
    
    created(){
        
    },

    mounted(){
        
        while(this.notes[this.notes.length-1] == undefined){
          this.notes.pop()
        }

        this.firstMeasure = this.notes[0][0]
        var currentBeat =0
        var beats_per_measure = 4
        console.log(currentBeat + beats_per_measure)
        const VF = Vex.Flow;
        var div = document.getElementById("tab")
        var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
        renderer.resize(1500, 180);
        var context = renderer.getContext();

        var trebleStave = new VF.Stave(10, 0, 1200);
        trebleStave.addClef("treble").addTimeSignature("4/4");
        trebleStave.setContext(context).draw();


        var bassStave = new VF.Stave(10, 80, 1200);
        bassStave.addClef("bass").addTimeSignature("4/4");
        bassStave.setContext(context).draw();

        var trebleArray = []
        var bassArray = []
        console.log("BaseArray " + bassArray)
        console.log("trebleArray " + trebleArray)
        console.log("notesarray " + notesArray)
        var notesArray = []
        // console.log(notesArray)
        var i =0
        while(i< this.notes.length){
        
          var keys = []
          console.log(keys)
          var treblekeys = []
          console.log(treblekeys)    

          var basskeys = []
          console.log(basskeys)    
          var j =i
          var note
          var duration

          while(this.notes[j]!= undefined && this.notes[j][1] == this.notes[i][1]){

            note = this.notes[j]            
            currentBeat = note[1]
            duration = note[3]

            console.log("duration " +  duration + " DurationMap " + DurationMap[duration] )

          
            if(note[2] <= 56){
              basskeys.push(MidiKeyMap[note[2]])
            }else{
              treblekeys.push(MidiKeyMap[note[2]])
            }
            // console.log("this.notes[j] " + this.notes[j][3] )

            // positions.push({str: Math.floor(this.notes[j][3])+1, fret: fret})
            j+=1
          }

          console.log("Note[2] " + note[2])
          var treblenote = new VF.StaveNote({clef: "treble", keys: treblekeys, duration: DurationMap[duration] })
          var bassnote = new VF.StaveNote({clef: "bass", keys: basskeys, duration: DurationMap[duration] })

// var tabnote = new VF.TabNote({positions: positions, duration: "q"})

        
          trebleArray.push(treblenote)
          bassArray.push(bassnote)

          // else{
          //   console.log("note [2] " + note[2] )
          //   var basenote = new VF.StaveNote({stem_direction: 1,clef: "bass", keys:[MidiKeyMap[39]], duration: "q" })
          //   bassArray.push(basenote)
          // }


          i=j
          
        }
      //   var notes = [
      //   new VF.StaveNote({clef: "treble", keys: ["c/4"], duration: "q" }),
      //   new VF.StaveNote({clef: "treble", keys: ["d/4"], duration: "q" }),
      //   new VF.StaveNote({clef: "treble", keys: ["b/4"], duration: "qr" }),
      //   new VF.StaveNote({clef: "treble", keys: ["c/4", "e/4", "g/4"], duration: "q" })
      //  ];

      // Create a voice in 4/4 and add the notes from above
      // var voice = new VF.Voice({num_beats: 5,  beat_value: 4});
      // voice.addTickables(notesArray);
      // // Format and justify the notes to 400 pixels.
      // var formatter = new VF.Formatter().joinVoices([voice]).format([voice], 400);
      // voice.draw(context, trebleStave);
      // formatter.getElementById


      var treble_beams = VF.Beam.generateBeams(trebleArray);
      VF.Formatter.FormatAndDraw(context, trebleStave, trebleArray);
      treble_beams.forEach(function(b) {b.setContext(context).draw()})



      var beams = VF.Beam.generateBeams(bassArray);
      VF.Formatter.FormatAndDraw(context, bassStave, bassArray);
      beams.forEach(function(b) {b.setContext(context).draw()})

      // var basevoice = new VF.Voice({num_beats: 5,  beat_value: 4});
      // basevoice.addTickables(bassArray);
      // var bassformatter = new VF.Formatter().joinVoices([basevoice]).format([basevoice], 400);
      // basevoice.draw(context, bassStave);
      // bassformatter.getElementById



    },
    computed: {

    }
}
</script>
<template>
    
  <v-container>
    <v-layout>
      <v-flex>
          <div id="boo">
            <v-card flat class="pa-3" v-for="item in this.lines" :key="item" >
              <TabLine />
            </v-card>

            

        </div>
      </v-flex>
    </v-layout>
  </v-container>

</template>

<script>

// import Vex from 'vexflow';
import TabLine from './TabLine'
// import { mapGetters, mapActions } from "vuex";


export default {

  components:{
    TabLine
  },
  
    props:{
      notes:Array
    },

    data () {
      return {
      
      
      }
    },

    methods:{
        // ...mapActions(["fetchTranscription"]),items
        // parse_measures(){
        //     var notes = this.getNotes()
        //     console.log(notes)
        //     notes.forEach(element => {
        //         console.log(element)
        //     });
        // }
    
    },
    created(){
        console.log("The notes array is in the componenet " + this.notes.constructor == Array)
        // var a = typeof this.notes
        // console.log(this.notes.length)
        // console.log("the type of notes in the ocmponenet is  " + this.notes)
        this.notes.forEach(note => {
            console.log("The measure is " + note[0])
        });
        this.nmeasures = this.notes[this.notes.length -1][0] 

        var measures_per_line = 3
          var nlines = Math.floor(this.nmeasures/measures_per_line)
          if(this.nmeasures % 4 != 0){
              nlines +=1
          }
          console.log("The number of lines is " + nlines)
          this.lines = []

          console.log(this.notes)
          
          var i
          var current_note_index = 0;
          var lowest_measure = 0;
          var highest_measure = measures_per_line;
          var current_measure = 0;
          for(i =0; i < nlines; i++){
            console.log(i)
            var line = []
            while(current_measure >= lowest_measure && current_measure < highest_measure
            && current_note_index < this.notes.length ) {
              
              current_measure = this.notes[current_note_index][0]
              console.log("yes")
              current_note_index+=1
              line.push(this.notes[current_note_index])
              
            } 
            this.lines.push(line)
            lowest_measure += measures_per_line
            highest_measure += measures_per_line
          }

          var nnotes = 0
          var dumb = 0
          this.lines.forEach(line => {
            console.log("There are " + line.length)
            line.forEach(note => {
              nnotes +=1
              if(note){
                dumb += note[0]
              }
              
            })
          })
          this.notes.forEach(note => {
            console.log(note[0])
          })

          console.log("There are nnotes " + nnotes)
          console.log("The number of notes " + this.notes.length)
          console.log("The  of notes " + dumb)



      //   console.log("The last measure is " + this.notes[this.notes.length -1][0])
      // var response_string = JSON.stringify(this.notes)
      // var notes1 = JSON.parse(response_string)
      // console.log(this.notes)
      // this.notes.forEach(element => {
      //   var response_string = JSON.stringify(element)
      //   var notes1 = JSON.parse(response_string)
      //   console.log(notes1)
      // });
    },


    mounted(){
  
        // console.log("the number lof lines " + this.lines.length)
        
        // this.lines.forEach(line => {
        //   console.log("There are n notes in the line " + line)
        // })


        // console.log("The last measure has notes ")
      // this.notes.forEach(note => {
      //   var response_string = JSON.stringify(element)
      //   var notes1 = JSON.parse(response_string)
      //   const measure = note[0]
      //   console.log(measure)
      // });
        // const VF = Vex.Flow;
        // var div = document.getElementById("boo")
        // var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
        // // Size our SVG:
        // renderer.resize(800, 500);
        // // And get a drawing context:
        // var context = renderer.getContext();
        // // Create a stave at position 10, 40 of width 400 on the canvas.
        // var notesArray = [
        // // A single note
        // new VF.TabNote({
        //     positions: [{str: 3, fret: 7}],
        //     duration: "q"}),
        // new VF.TabNote({
        //     positions: [{str: 3, fret: 7}],
        //     duration: "q"}),

        // new VF.TabNote({
        //     positions: [{str: 3, fret: 7}],
        //     duration: "q"}),
        // new VF.TabNote({
        //     positions: [{str: 3, fret: 7}],
        //     duration: "q"}),
        // // A chord with the note on the 3rd string bent

        // ];
        // console.log(notesArray)


        // var i;
        // var stave;
        // for (i = 0; i < nlines; i++) {
        //     // console.log(notes[i])
        //     stave = new VF.TabStave(10, 100 * i, 700);
        //     stave.addClef("tab").setContext(context).draw();
        //     VF.Formatter.FormatAndDraw(context, stave, notesArray);
        // } 
        

        // Add a clef and time signature.
        // var stave = new VF.TabStave(10, 0, 700);
        // stave.addClef("tab").setContext(context).draw();

        // var stave2 = new VF.TabStave(10, 100, 700);
        // stave2.addClef("tab").setContext(context).draw();
        // stave.drawVerticalBar(100, true);

        // var notes = [
        // A single note
        // new VF.TabNote({
        //     positions: [{str: 3, fret: 7}],
        //     duration: "q"}),

        // // A chord with the note on the 3rd string bent
        // new VF.TabNote({
        //     positions: [{str: 2, fret: 10},
        //                 {str: 3, fret: 9}],
        //     duration: "q"}).
        // addModifier(new VF.Bend("Full"), 1),

        // // A single note with a harsh vibrato
        // new VF.TabNote({
        //     positions: [{str: 2, fret: 5}],
        //     duration: "h"}).
        // addModifier(new VF.Vibrato().setHarsh(true).setVibratoWidth(70), 0)
        // ];

        // VF.Formatter.FormatAndDraw(context, stave, notes);
        // VF.Formatter.FormatAndDraw(context, stave2, notes);


    },
    computed: {
        // ...mapGetters(["getTranscription"]),
        // ...mapGetters(["getNotes"])

    }
}
</script>
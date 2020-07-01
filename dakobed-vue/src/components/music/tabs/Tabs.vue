<template>
    
  <v-container>
    <v-layout>
      <v-flex>
        {{ notes[0] }}      
          <div id="boo">
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

    methods:{
        // ...mapActions(["fetchTranscription"]),
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
        console.log(this.notes.length)
        console.log("the type of notes in the ocmponenet is  " + this.notes)
        this.notes.forEach(note => {
            console.log("The measure is " + note[0])
        });
    //   var response_string = JSON.stringify(this.notes)
    //   var notes1 = JSON.parse(response_string)
    //   console.log(this.notes)
    //   this.notes.forEach(element => {
    //     var response_string = JSON.stringify(element)
    //     var notes1 = JSON.parse(response_string)
    //     console.log(notes1)
    //   });
    },



    mounted(){

        const VF = Vex.Flow;
        var div = document.getElementById("boo")
        var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
        // Size our SVG:
        renderer.resize(800, 500);
        // And get a drawing context:
        var context = renderer.getContext();
        // Create a stave at position 10, 40 of width 400 on the canvas.

        // Add a clef and time signature.
        var stave = new VF.TabStave(10, 40, 700);
        stave.addClef("tab").setContext(context).draw();

        var stave2 = new VF.TabStave(10, 150, 700);
        stave2.addClef("tab").setContext(context).draw();
        stave.drawVerticalBar(100, true);

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
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
        // var context = renderer.getContext();
        // Create a stave at position 10, 40 of width 400 on the canvas.
        
        // var notesArray = []
        
        var i =-0
        // var note;
        // var positions;
        while(i< this.notes.length){
          if(this.notes[i] != undefined){
            
            // note = this.notes[i]
            // positions = []
            while(i < this.notes.length){
              if(this.notes[i] == undefined){
                // console.log("say ghaadfdfdfdf")
                break
              }else{
                var j = i
                while(this.notes[j]!= undefined && this.notes[j][1] == this.notes[i][1]){
                  // if(j!= i){

                  // }
                  j+=1
                }
                if(j != i+1){
                  console.log("j " + j + " and i " + i)
                  if(this.notes[j] != undefined && this.notes[i] != undefined){
                      console.log("The beat at which i occurs is " + this.notes[i][1] + " and the note at which j occurs is " + this.notes[j-1][1])
                  }
                  
                }

                // console.log("The beat at which the note occurs " + this.notes[i][1])

              }
              // var fret = GuitarMidiFret[note[3]].indexOf(note[2])
              // positions.push({str: note[3]+1, fret: fret})
              // beat = this.notes[i+1]
                    
              // if(this.notes[i] != undefined && this.notes[i+1] != undefined && note[1] == this.notes[i+1]){
              //    console.log("I am terrafucked")
              // }
              // else{
              //   console.log("I am undefined ")
              //   break
              // }
              i +=1
            }  
            
            //   var tabnote = new VF.TabNote({
            //     positions: positions,
            //     duration: "q"
            //   })
            //   notesArray.push(tabnote)
            
            
            }else{
              console.log("undefined")
            }
        
          i +=1
        }

          // note = this.notes[i]
          // positions = []
          // while(i < this.notes.length){
            
          //   var fret = GuitarMidiFret[note[3]].indexOf(note[2])
          //   positions.push({str: note[3]+1, fret: fret})
          //   // beat = this.notes[i+1]
            // i +=1
                  
            // if(i+1 >= this.notes.length | this.notes[i] == undefined | this.notes[i+1] == undefined| this.notes[i+1][1] != note[1]){
            //    break
            // }
            
          
          // var tabnote = new VF.TabNote({
          //   positions: positions,
          //   duration: "q"
          // })
        
          // notesArray.push(tabnote)
          
         

        
        // this.notes.forEach(note=>{
        //     var midi = note[2]
        //     var string = note[3]
        //     var fret = GuitarMidiFret[string].indexOf(midi)
        //     console.log("note measure " + midi + "  " + string)
        //     console.log("The fret at which " + midi +  " occurs on string " + string + " is at " + fret)


        //     var tabnote = new VF.TabNote({
        //         positions: [{str: string+1, fret: fret}],
        //         duration: "q"
        //         })
            
        //     notesArray.push(tabnote)


        // })
        
        // var notesArray = [
        // // A single note
        // new VF.TabNote({
        //     positions: [{str: 0, fret: 2}],
        //     duration: "q"}),
        // ];

        // var stave = new VF.TabStave(10, 0, 900);
        // stave.addClef("tab").setContext(context).draw();
        // console.log("the number of notes is " + notesArray.length)
        // VF.Formatter.FormatAndDraw(context, stave, notesArray);

    },
    computed: {

    }
}
</script>
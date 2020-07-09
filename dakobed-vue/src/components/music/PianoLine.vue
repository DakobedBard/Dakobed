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



    },
    computed: {

    }
}
</script>
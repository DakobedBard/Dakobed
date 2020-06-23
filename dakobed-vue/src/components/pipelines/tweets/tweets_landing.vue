<template>
<v-container>
  <v-layout>
    <v-flex>
     <v-card>
        <v-card-subtitle> What are people tweeting about?

        </v-card-subtitle>
         <v-card flat> 
              <GmapMap
      :center="{lat:10, lng:10}"
      :zoom="7"
      map-type-id="terrain"
      style="width: 500px; height: 300px"
    >
    <GmapMarker
      :key="index"
      v-for="(m, index) in markers"
      :position="m.position"
      :clickable="true"
      :draggable="true"
      @click="center=m.position"
    />
</GmapMap>
    </v-card>
     </v-card> 

    </v-flex>
  </v-layout>
    
</v-container>
</template>
<script>


import { mapGetters, mapActions } from "vuex";

export default {
  
    methods:{
    ...mapActions(["setPipelineSelection"]),
  },

  data(){
    return{
      selection:1  
    }
  },


  components:{

  },
  computed: {
    ...mapGetters(["getPipelineSelection"]),

  }
}
</script>

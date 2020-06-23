<template>
<v-container>
  <v-layout>
    <v-flex>
     <v-card>
        <v-card-subtitle> What are people tweeting about?

        </v-card-subtitle>
         <v-card flat> 
        <GmapMap
      :center="center"
      :zoom="4"
      map-type-id="terrain"
      style="width: 700px; height: 450px"
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
      selection:1,
      markers:[],
      latSelection:-98,
      lngSelection:4,
      center:{lat:40,lng:-98}

    }
  },


  components:{

  },
  computed: {
    ...mapGetters(["getPipelineSelection"]),

  }
}
</script>

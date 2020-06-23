<template>
<v-container>
    <v-layout row>
        <v-flex md7>
          <v-card tile flat >
            <v-card-text>#1</v-card-text>
            <div id="map" ref="map"></div>
          </v-card>
        </v-flex>
        <v-flex md5>
          <v-card dark tile flat color="pink darken-4">
            <v-card-text>#2</v-card-text>
          </v-card>
        </v-flex>
      </v-layout> 
  <v-layout>
    <v-flex>
     <v-card>
        <v-card-subtitle> What are people tweeting about?

        </v-card-subtitle>
         <v-card flat> 

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

  },
  mounted(){
    this.map = new window.google.maps.Map(this.$refs["map"],{
      center: {lat:40, lng:-98 },
      zoom: 4
    })
  }
}
</script>
<style scoped>
  #map {
    height:600px;
    background:gray;
  }
</style>

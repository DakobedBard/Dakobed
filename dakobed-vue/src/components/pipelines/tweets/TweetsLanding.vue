<template>
<v-container>
  <v-layout row>
    <v-flex md7>
      <v-card tile flat >
        <v-card-text>#1</v-card-text>
          <div id="map" ref="map">

          </div>
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
  props:{

  },


  methods:{
    ...mapActions(["setPipelineSelection"]),
    getMap(callback) {
			let vm = this
				function checkForMap() {
					if (vm.map) callback(vm.map)
					else setTimeout(checkForMap, 200)
				}
				checkForMap()
		}
  },

  data(){
    return{
      map:null,
      marker:null,
      selection:1,
      markers:[],
      latSelection:40,
      lngSelection:-98,
      zoomSelection:4

    }
  },


  components:{

  },
  computed: {
    ...mapGetters(["getPipelineSelection"]),

  },
  mounted(){
    this.map = new window.google.maps.Map(this.$refs["map"],{
      center: {lat:this.latSelection, lng:this.lngSelection },
      zoom: this.zoomSelection
    }),
    this.marker = new window.google.maps.Marker({
					position: { lat: 50, lng: -98 },
					map: this.map
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

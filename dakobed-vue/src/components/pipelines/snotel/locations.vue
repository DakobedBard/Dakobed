<template>
      <div class="locations">
      <v-card dark>
        <v-card-title dark>
          Stream Flow Location
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
       
    </v-card-title>
     </v-card>
    <v-data-table dark 
      :headers="headers"
      :items="locations"
      :search="search"
      @click:row="locationClick"
    >
    
    <template v-slot:default>
    <thead style="height:1000px">
        <tr>
          <th class="text-left">Location</th>
          <th class="text-left">Elevation</th>
          <th class="text-left">Region</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="location in locations" :key="location.location"  @click="showAlert(props.item)" >
          <td>{{ location.location }}</td>
          <td> {{ location.elevation  }} </td>
          <td> Wenatchee </td>
        </tr>
      </tbody>
    </template>
    </v-data-table>
  </div>

</template>
<script>
import axios from 'axios'
import router from '../../../router'
export default {
  name: 'LocationsList',
  created(){
    this.locationRequest()

  },

  data: function() {
    var location_array = [];
    axios.get('http://localhost:8088/data/location/')
                .then(response => {location_array = response.data})
                .catch((error) => console.log(error))
    
    console.log("Locations " + location_array.length)
    
    return {
        search: '',
        headers: [
          {
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: 'Name', value: 'location_name' },
          { text: 'Elevation', value: 'elevation' },

        ],


            attributeA: 'valueA',
            locations: []
		};
	}
    ,
  methods: {

      locationClick(location){
        console.log("Clicked on the location " + location.location_id)
        const id = location.location_id
        const name = location.location_name
        router.push({ name: 'location_detail', params: { id:id, location_name: name } })
      },
        locationRequest: function(){
          axios.get('http://localhost:8086/data/location/')
                .then(response => {this.locations = response.data})
                .catch((error) => console.log(error))
        },

        getLocations: function (text){
            console.log("You suck! " + text + " " + this.name )  
            console.log("The number of cars is " + this.locations.length)
        }
    }
  }
</script>




<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}


</style>
<template>
  <div>
  <v-container>
      <v-layout>
          <v-flex>
            <v-card >
                <v-card-title>
                
                    {{ location_name }}
                </v-card-title>
            </v-card>
          </v-flex>
      </v-layout>

      <v-layout>
          <v-flex>
            <v-card >
                <v-card-title>
                
                    {{ location_name }}
                </v-card-title>
            </v-card>
          </v-flex>
      </v-layout>

  </v-container>
  
  </div>
</template>
<script>

export default {
    props:{
        location_name: String
    },
    data: function() {
        return{
            msg: 'fucki'
        }
    }
}
</script>
<template>
    <div>
      <v-container>
        <v-layout>
          <v-flex>
            <h2>
                Snotel 
            </h2>
            <locations />
          </v-flex>
        </v-layout>
      </v-container>
    </div>
</template>

<script>

import locations from './locations'

export default {
    components:{
        locations
    },
    date(){
        return{
            
        }   
    }
}
</script>
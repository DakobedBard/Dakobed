<template>
  <div>

    <v-container fluid>
     <v-layout row>
        <v-flex md9>
            <Tabs v-bind:notes="getNotes"/>

          
        </v-flex>
        <v-flex md3>

        <v-card dark flat class="mx-auto" max-width="400">
          <v-list :flat="flat"> <v-list-item-group  v-model="model" :multiple="multiple" :mandatory="mandatory "
            color="grey">
            <v-list-item v-for="item in menuItems" :key="item.title"  @click="selectMenuItem(item.selection)">
              <v-list-item-icon>
                <v-icon v-text="item.title"></v-icon>
              </v-list-item-icon>
          
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>

            </v-list>
          </v-card>
  
        </v-flex>
      </v-layout> 
    </v-container>

  </div>
</template>

<script>


import { mapGetters, mapActions } from "vuex";

import Tabs from './Tabs'
export default {

    components:{
        Tabs
    },
    created(){
      //this.fetchTranscription()
      this.getS3Transcription()
    },
    data () {

      return {
        selection:'gallery',  
        menuItems:[
          {selection:'tabs',title:'Tab Generator'},
          {selection:'covers',title: 'Guitar Covers'},
          {selection:'covers',title: 'Piano Covers'},
        ],
        directions: ['t', 'b', 'l', 'r', 's', 'e', 'x', 'y', 'a'],
        paddingSizes: ['auto', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        marginSizes: [
          'auto',
          '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12',
          'n1', 'n2', 'n3', 'n4', 'n5', 'n6', 'n7', 'n8', 'n9', 'n10', 'n11', 'n12',
        ],
        paddingDirection: 'a',
        paddingSize: '2',
        marginDirection: 'a',
        marginSize: '2',
        playgroundText: 'Use the controls above to try out the different spacing helpers',
        model: 1,
        multiple: false,
        mandatory: false,
        flat: false,
        dense: false,
        count: 4,
      
      }
    },

    
    methods:{
        ...mapActions(["fetchTranscription"]),
        ...mapActions(["getS3Transcription"]),        
        selectMenuItem(selection){

          this.setGallerySelection(selection)
        },
        // changeSelection(item_selection){
        //     this.selection = item_selection
        //     console.lo
        // },
    },
    computed: {
      ...mapGetters(["getNotes"])
    }
  }
</script>

<style>
  .spacing-playground .v-select .v-input__prepend-outer, .spacing-playground .v-select .v-input__append-outer{
    margin-top: 0.55rem;
    margin-right: 0.2rem;
  }
</style>
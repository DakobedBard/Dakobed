<template>
  <div>

    <v-container fluid>
     <v-layout row>
        <v-flex md9>

          
        </v-flex>
        <v-flex md3>

        <v-card flat class="mx-auto" max-width="400">
        <v-list :flat="flat"> 
          <!-- :dense="dense" > -->
          <v-list-item-group  v-model="model" 
          :multiple="multiple"
           :mandatory="mandatory"
            color="grey">
            
            <v-list-item v-for="item in menuItems" :key="item.title" @click="selectMenuItem(item.selection)">
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
    <!-- <v-container fluid>
      <v-card flat height="350px"  >
        <div>

            <div v-if="selection === 'characters'">
              <CharacterList />
            </div>
            <div v-else-if="selection === 'maps'">
              <Maps />
            </div>
            <div v-else-if="selection === 'stories'">
              <Stories />
            </div>
            <div v-else>
            Not A/B/C
            </div>

        </div>
      </v-card>
    </v-container> -->
        <!-- <v-navigation-drawer absolute permanent right floating>
          <v-list right>
            <v-list-item  v-for="item in menuItems" :key="item.title" >
              <v-list-item-content  >
                  
                <v-list-item-title style="'align-items':'center'" class="font-weight-bold grey--text subtitle-2" 
                @click="changeSelection(item.selection)"
                >  {{ item.title }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-navigation-drawer> 
      </v-card>
    </v-container> -->
    
  </div>
</template>

<script>

// import CharacterList from './characters/CharacterList'
// import Stories from './stories/Stories'
// import Maps from './maps/Maps'

export default {

    components:{
        // CharacterList,
        // Stories,
        // Maps
    },
    data () {
      return {
        selection:'tweets',  
        menuItems:[
          {title:'Snotel', selection:'snotel' },
          {title:'WTA Trip Reports', selection:'wta' },
          {title:'Tweets Tracker', selection:'tweets' },
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
        selectMenuItem(id){
          console.log("Fuck YOU! " +id)
        },
        changeSelection(item_selection){
            this.selection = item_selection
        }
    }
  }
</script>

<style>
  .spacing-playground .v-select .v-input__prepend-outer, .spacing-playground .v-select .v-input__append-outer{
    margin-top: 0.55rem;
    margin-right: 0.2rem;
  }
</style>
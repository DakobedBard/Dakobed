<template>
  <div>
    <v-container>
        <v-card flat class="mx-auto" max-width="400">
        <v-list :flat="flat"> 
          <!-- :dense="dense" > -->
          <v-list-item-group  v-model="model" 
          :multiple="multiple"
           :mandatory="mandatory"
            color="grey">
            
            <v-list-item v-for="item in menuItems" :key="item.title">
              <v-list-item-icon>
                <v-icon v-text="item.title"></v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>

        </v-list>
        <!-- <v-list dense>
            <v-list-item center v-for="item in menuItems" :key="item.title" >
              <v-list-item-content >
                <v-list-item-title class="font-weight-bold grey--text subtitle-2" @click="changeSelection(item.selection)">{{ item.title }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list> -->
      </v-card>


    </v-container>
  </div>
</template>


      <!-- <v-card height="350px" flat>
        <div>

            <div v-if="selection === 'snotel'">
              <snotel />
            </div>
            <div v-else-if="selection === 'tweets'">
                <tweets />
            </div>

            <div v-else>
            Not A/B/C
            </div>
        </div>
      </v-card> -->

      <!-- <div>
      <v-card 
        class="mx-auto"
        max-width="400"
      >
        <v-list
          :flat="flat"
          :dense="dense"
        >
          <v-list-item-group
            v-model="model"
            :multiple="multiple"
            :mandatory="mandatory"
            color="indigo"
          >
            <v-list-item
              v-for="i in count"
              :key="i"
            >
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div> -->
<!-- 
        </div>
        <v-navigation-drawer absolute permanent right floating class="ml-6">
          <v-list dense>
            <v-list-item center v-for="item in menuItems" :key="item.title" >
              <v-list-item-content >
                <v-list-item-title class="font-weight-bold grey--text subtitle-2" @click="changeSelection(item.selection)">{{ item.title }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>
      </v-card>
    </v-container> -->



<script>

// import snotel from './snotel/snotel'
// import tweets from './tweets/tweets'
export default {

    components:{
        // snotel,
        // tweets
    },
    data () {
      return {
        selection:'tweets',  
        menuItems:[
          {id:1,title:'Snotel', selection:'snotel' },
          {id:2,title: 'Tweets Tracker', selection:'tweets'},
        ],

      item: {
        icon: 'mdi-wifi',
        text: 'Wifi',
      },
      model: 1,
      multiple: false,
      mandatory: false,
      flat: false,
      dense: false,
      count: 4,
      }
    },
    methods:{
        changeSelection(item_selection){
            console.log("Fuck YOU! " + item_selection)
            this.selection = item_selection
        }
    }
  }
</script>

<style>
  .v-list-item-title input {
    font-size: 5em;
  }
</style>
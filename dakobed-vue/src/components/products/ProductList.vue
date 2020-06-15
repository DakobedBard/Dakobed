<template>
  <div>

    <v-container fluid>
     <v-layout row>
        <v-flex md9>
            <Tabs />

          
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
export default {
    data () {
      return {
          msg: 'dfd'
      }
    }
}
</script>
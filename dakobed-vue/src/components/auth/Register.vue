<template>
  <div>
    <!-- <div v-if="getCurrentUser == '-1'"> -->
  <v-container>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-card >
            <v-card-text>
                <v-container>
                    <v-form>
                        <v-layout>
                        <v-flex xs12>
                             <v-text-field name ="email" label = "Mail" id = "email" v-model ="email"></v-text-field>
                                <v-text-field name ="username" label = "username" id = "username" v-model ="username"></v-text-field>
                                <v-text-field name ="password" label = "password" id = "password" v-model ="password"></v-text-field>
                                <v-text-field name ="password2" label = "password2" id = "password2" v-model ="password2"></v-text-field>
                        </v-flex>
                      </v-layout>
                      <v-btn @click="Login()">Login</v-btn>
                    </v-form>
                </v-container>
            </v-card-text>

        </v-card>
      </v-flex>
    </v-layout>
  </v-container>

  </div> 
</template>
<script>

import { mapGetters, mapActions } from "vuex";

export default {
    data(){
        return{
            title: '',
            content: '',
        }
    },
    methods:{
        ...mapActions(["registerUser"]),
        onRegisterPost(){
            
            // this.postReport(this.title, this.region, this.content, this.mileage, this.elevation)
        },
        // onSaveChanges(){
        //     const newDate = new Date(this.start)
        // }
    },
    computed:{
        ...mapGetters(["getCurrentUser"]),
    },
    mixins:[]
}
</script>


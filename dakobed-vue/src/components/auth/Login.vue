<template>
<div>
  <v-container>
    <v-layout>
      <v-flex>
        <v-card>
          <v-form class ="px-3">
            <v-text-field label ="Email" v-model="title"></v-text-field>
            <v-text-field label ="Password" v-model="region"></v-text-field>
            <v-btn @click="Login()">Login</v-btn>
          </v-form>
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
        ...mapActions(["setReportThumbnail"]),
        onReportPost(){
            
            // this.postReport(this.title, this.region, this.content, this.mileage, this.elevation)
        },
        // onSaveChanges(){
        //     const newDate = new Date(this.start)
        // }
    },
    computed:{
        ...mapGetters(["getThumbail"]),
    },
    mixins:[]
}
</script>


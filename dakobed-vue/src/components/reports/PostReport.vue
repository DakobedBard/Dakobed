<template>
    <v-container class="my-5" > 
      <v-card>
        <v-card-title>
        <h2>
            Post Trip Report
        </h2>
            </v-card-title>
            <v-card-text>
            <v-form class ="px-3">
                <v-text-field label ="Title" v-model="title"></v-text-field>
                <v-text-field label ="Region" v-model="region"></v-text-field>
                <v-textarea label = "Report" v-model="content" ></v-textarea>
                <v-text-field label ="Mileage" v-model="mileage"></v-text-field>
                <v-text-field label ="Elevation gain" v-model="elevation"></v-text-field>
          <v-layout>
 
          <UploadImage button_title="Report" /> 
          </v-layout>
                <v-flex>
                    <v-btn @click="onReportPost()">Post</v-btn>
                </v-flex>
            </v-form>
        </v-card-text>

      </v-card>
      </v-container>
</template>
<script>

import { mapGetters, mapActions } from "vuex";
import UploadImage from '../uploads/UploadImage'

// import tripsMixin from '../mixins/tripsMixin';
export default {
    created(){
        
      console.log("I get rendered..")  
    },
    components:{
        UploadImage
    },
    data(){
        return{
            title: '',
            content: '',
            region:'',
            mileage:'',
            elevation:'',
            start_date:new Date().toISOString().substr(0, 10),

        }
    },
    methods:{
        ...mapActions(["setReportThumbnail"]),
        ...mapActions(["postTripreport"]),
        onReportPost(){
            const report = {
                name:this.title,
                region:this.region,
                report:this.content,
                distance:this.mileage,
                elevationGain:this.elevation
            }
            this.postTripreport(report)
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
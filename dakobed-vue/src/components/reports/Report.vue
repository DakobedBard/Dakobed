<template>
 <div class="trip">
   <v-container fluid> 
     <v-layout>
      <v-flex offset-sm2>
        <v-card   height="100%" width="84%" style="top: 28px; margin-top: -24px">
          <v-card-title class="display-2 "> {{ getCurrentReport.name }} </v-card-title>
        <v-divider></v-divider>

          
            <!-- <v-img class="white--text align-end" height="100%" width="80%" v-bind:src="getCurrentReport.imageURL">  </v-img>  -->

        <v-divider></v-divider>
        <v-card-text>
          {{ getCurrentReport.report}}
        </v-card-text>

        <v-card flat class="pa-3" v-for="report in allReports" :key="report.id" >
          {{report.name}}
        
        </v-card>



  <v-row>
    <v-col cols="12" sm="12" offset-sm="">
      <v-card flat> 
        <!-- <v-container >
          <v-row >
            <v-col
              v-for="n in 3"
              :key="n"
              class="d-flex child-flex"
              cols="4"
            >
              <v-card flat tile class="d-flex">
                <v-img class="white--text align-end" height="100%" width="80%" v-bind:src="getCurrentReport.imageURL">  </v-img>

              </v-card>
            </v-col>
          </v-row>
        </v-container> -->
      </v-card>
    </v-col>
  </v-row>



    </v-card>
    </v-flex>
   </v-layout>
    <v-layout>
      <v-flex offset-sm2>
        <v-card >
          <CommentList v-bind:comments="comments"/>
        </v-card>
      </v-flex>
    </v-layout>


    
  </v-container>
 </div>
</template>

<script>
// import tripsMixin from '../../mixins/tripsMixin';
import router from '../../router'
import CommentList from '../comments/CommentList'

import { mapGetters, mapActions } from "vuex";

export default {
    name: 'Report',
    created(){
        // this.getReportDetail(this.reportID)
        this.getReport(this.reportID)
        console.log("The reportID is " + this.reportID)

    },
    props:{
      reportID:String
    },
    components:{
      CommentList
    },
    data(){
        return{
          report:{},
          comments:[
            {id:1, username: 'billyJoe',comment:'You suck' },
            {id:2, username: 'Donald J',comment:'You are bac' },
            {id:3, username: 'Harold A',comment:'You are sick' },
          ]
        }        
    },
    methods:{
      ...mapActions(["getReport"]),
      delReport(id){
        this.deleteReport(id)
        router.go(-1)
      },

    },
      computed: {
    ...mapGetters(["getCurrentReport"]),
    ...mapGetters(["allReports"]),

    }
    // mixins: [tripsMixin]
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

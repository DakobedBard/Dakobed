<template>
    <div class="reports" >
      <v-container class="my-5" > 
        <v-card flat class="pa-3" v-for="report in reports" :key="report.id" >
              <v-layout row wrap @click="clickRow(report.id)"> 
                <v-flex xs12 md6>
                  <div class ="caption grey--text"> Trip Name </div>
                   <router-link v-bind:to="'/report/' + report.id" >
                    <div> {{ report.name}} </div>
                   </router-link>
                </v-flex>
                <v-flex xs6 sm4 md2>
                    <div class="caption grey--text">Region</div>
                    <div> {{ report.region}} </div>
                </v-flex>

                <v-flex xs6 sm4 md2>
                    <div class="caption grey--text">Distance</div>
                    <div>{{report.distance}}</div>
                </v-flex>


                <v-flex xs6 sm4 md2>
                    <div class="caption grey--text">Elevation Gain</div>
                    <div>{{report.elevationGain}} </div>
                </v-flex>
            </v-layout>
        </v-card>
        <v-flex>
        <v-btn to= "/postReport">Post Trip Report</v-btn>
        </v-flex>
        </v-container>
    </div>
</template>

<script>

import tripsMixin from '../mixins/tripsMixin';

export default {
    created(){
        this.getReports()
    },
    components:{

    },
    data() {
        return {
            reports: [],
            characters: [],
            search: '',
            headers: [
            {
                align: 'start',
                sortable: false,
                value: 'name',
            },
            { text: 'First Name', value: 'firstName' },
            { text: 'Last Name', value: 'lastName' },
            { text: 'Region', value: 'region' },
            { text: 'Race', value: 'race' },
            ],

        }
    },
    methods:{
        clickRow(id){
            console.log("clicked on row w/ ID " + id)
        }
    },
    computed: {
      cols () {
        const { lg, sm } = this.$vuetify.breakpoint
        return lg ? [3, 9] : sm ? [9, 3] : [6, 6]
      },
    },
    mixins:[tripsMixin]
}
</script>
/*eslint no-unused-vars: "error"*/

<template>
  <v-app>
    <nav>
    <div>
      <v-toolbar dark prominent  height="340px" src="https://dakobed.s3-us-west-1.amazonaws.com/panorama.jpg">

      </v-toolbar>
    </div>

      <v-toolbar flat dark class="py-0 mt">
        
        <v-app-bar-nav-icon class ="grey--text" @click="drawer = !drawer"></v-app-bar-nav-icon>
          <v-toolbar-title class="grey--text">
            <span class="font-weight-light">Mathias</span>
            <span>PORTFOLIO</span>
          </v-toolbar-title>
          <v-spacer></v-spacer>
      <v-toolbar-items class = "hidden-xs-only">
        <v-btn  v-for="item in menuItems" :key="item.title" @click="selectRoute(item.route)" class ="grey--text" >
        <!-- <v-btn  v-for="item in menuItems" :key="item.title" :to= "item.route" class ="grey--text" > -->
          <v-icon left >
          </v-icon>
          {{item.title}}
        </v-btn>
      </v-toolbar-items>
      
      <v-spacer></v-spacer>
      </v-toolbar>
    </nav>

    <router-view></router-view>
  </v-app>
</template>

<script>

import { mapGetters, mapActions } from "vuex";
import router from './router'


export default {
  name: 'App',

  components: {
    
  },
  data(){
    return {
        drawer: false,
              items: [
        { title: 'Click Me' },
        { title: 'Click Me' },
        { title: 'Click Me' },
        { title: 'Click Me 2' },
      ],
        menuItems:[
          {title:'Trip Reports', icon:'image-filter-hdr', route:'/landing/' },
          {title:'Gallery', icon:'image-filter-hdr', route:'/gallery/' }, 
          {title:'Data Pipelines', icon:'image-filter-hdr', route:'/pipelines/' }, 
          {title:'Authentication', icon:'image-filter-hdr', route:'/authentication/' }, 
          {title:'Locations', icon:'image-filter-hdr', route:'/locations/' }, 
          {title:'Series', icon:'image-filter-hdr', route:'/series/' },
          {title:'Tabs', icon:'image-filter-hdr', route:'/tabs/' }, 

        ],

        color: 'primary',
        colors: [
          'primary',
          'blue',
          'success',
          'red',
          'teal',
        ],
        right: false,
        permanent: false,
        miniVariant: false,
        expandOnHover: false,
        background: false,
      }
    },
    methods: {
      ...mapActions(["setSelection"]),
      selectRoute(route){ // eslint-disable-line no-unused-vars
        this.setSelection("landing")
        router.push(route).catch(err => err)
      }

    },
    computed: {
    ...mapGetters(["allReports"]),
    ...mapGetters(["reportSelection"]),
    // mixins:[tripsMixin]
    }
};
</script>

<template>
  <div>

    <v-container fluid>
        <template>
          <v-card
            color="grey lighten-4 " flat  tile>
            <v-toolbar flat extended >
              <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->
              <!-- <v-app-bar-nav-icon class ="grey--text">
              </v-app-bar-nav-icon> -->

              <v-spacer></v-spacer>

         <v-toolbar-items class = "hidden-xs-only">

         </v-toolbar-items>

            </v-toolbar>
          </v-card>
        </template>
      <v-layout row wrap>


          <v-flex md6 offset-1>

          </v-flex>
          <v-flex md3 offset-1>

            <v-card  tile flat >
    
              <v-btn icon>
                <v-icon @click="selectRoute('/cart')">mdi-cart</v-icon>
              </v-btn>
        
            </v-card>
          </v-flex>

          <v-flex xs2 md3 offset-sm1 v-for="item in allProducts" v-bind:key=item.id>
            <ProductCard v-bind:productID="item.id" v-bind:productName="item.productName" 
            v-bind:imageURL="item.imageURL" v-bind:productPrice="item.price"/>
              <v-card >
                  <v-img :src="item.imageURL" height="200px" width="100"></v-img>
                  what
              </v-card>
              
          </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

    
import { mapGetters, mapActions } from "vuex";
import ProductCard from './ProductCard'
import router from '../../router'


export default {
  components:{
    ProductCard
  },
    created(){

        this.fetchProducts();
    },
    methods:{
        ...mapActions(["fetchProducts"]),
        
        selectRoute(route){ // eslint-disable-line no-unused-vars
          router.push(route).catch(err => err)
      }

    },
    data(){
        return {
            reportID:-1,
            reports: []
        }
    },
    computed: {
        ...mapGetters(["allProducts"]),
    }
    
}
</script>
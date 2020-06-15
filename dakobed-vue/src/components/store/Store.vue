<template>
  <div>
    <v-container fluid>
      <v-layout row wrap>
        
          <v-flex xs6 md3 offset-sm1 v-for="item in allProducts" v-bind:key=item.id>
            <ProductCard v-bind:productName="item.productName" v-bind:imageURL="item.imageURL" v-bind:productPrice="item.price"/>
              <!-- {{item.productName}} dfdf
              <v-card >
                  <v-img :src="item.imageURL" height="200px" width="100"></v-img>
                  what
              </v-card> -->
              
          </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

    
import { mapGetters, mapActions } from "vuex";
import ProductCard from './ProductCard'

export default {
  components:{
    ProductCard
  },
    created(){

        this.fetchProducts();
    },
    methods:{
        ...mapActions(["fetchProducts"]),
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
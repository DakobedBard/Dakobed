<template>
  <v-container>
    <v-layout>
      <v-flex md6 offset-3>
        <v-card>
          {{products }}
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {

    created(){
        
        const products = []
        console.log(products)
        const productsMap = this.getCart
        console.log(productsMap)
        console.log("whattt")
        productsMap.forEach(data => products.push(data))
        console.log(products)
    },


    computed:{
        ...mapGetters(["getCart"]),
    },
    methods:{
        ...mapActions(["changeProductCartCount"]),
    }
}

</script>

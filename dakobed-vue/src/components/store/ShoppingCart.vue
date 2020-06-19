<template>
  <v-container>
    <v-layout>
      <v-flex md6 offset-3>
        <v-card>
          {{products }}
        </v-card>

        <v-card class="mx-auto" max-width="800" tile flat>
            
            <v-subheader><h2>Shopping Cart</h2></v-subheader>
                <v-card flat class="pa-3" v-for="product in products" :key="product.id" >
                    {{product.id}}
                </v-card>
        </v-card>


      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {

    created(){
        
        let productIDs = Array.from( this.getCart.keys() );
        let quantities = Array.from(this.getCart.values())
        console.log("products " + productIDs )
        console.log("quantities " + quantities )
        // productIDs.forEach(data => console.log("the product id is " + data))
        // quantities.forEach(data => console.log("the quantity is " + data))
        // console.log(productIDs.indexOf(0))
        // console.log(quantities.indexOf(0))

        // console.log("The value is " + productIDs.indexOf(0))
        // console.log("The productIDs " + productIDs.length)
        // const products = []
        // const productsMap = this.getCart
        // this.productsMap = productsMap


        // productsMap.forEach(data => products.push(data))
        // console.log(products)
        // this.products=products
    },

    data () {
     return {

        products:[]
     }
  },
    computed:{
        ...mapGetters(["getCart"]),
    },
    methods:{
        ...mapActions(["changeProductCartCount"]),
    }
}

</script>

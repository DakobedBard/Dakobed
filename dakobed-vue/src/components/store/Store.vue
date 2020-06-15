<template>
  <div>
    <v-container fluid>

      <v-layout>

      <v-layout row wrap>
        {{ allProducts.length }}
          <v-flex xs6 md2 v-for="item in allProducts" v-bind:key=item.id>
              {{item.productName}} dfdf
              <v-card >
                  <v-img :src="item.imageURL" height="200px" width="100"></v-img>
              </v-card>
          </v-flex>
      </v-layout>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

    
import { mapGetters, mapActions } from "vuex";

export default {
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
<template>
  <div>
    <v-container fluid>
      <v-layout>
        <v-flex>
            <v-card>
                Hello
            </v-card>
        </v-flex>
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
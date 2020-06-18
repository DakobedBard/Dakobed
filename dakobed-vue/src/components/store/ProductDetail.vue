<template>
    <v-container>
      <v-layout row wrap>
        <v-flex md6>
          <v-card dark tile flat color="error">
            <v-card-text>Product Detail</v-card-text>
          </v-card>
        </v-flex>
        <v-flex md6>
          <v-card  tile flat >
            <v-flex>
      
                <v-card-text> <h1>{{ productName }} </h1> </v-card-text>
            </v-flex>

            <v-flex>
     
                <v-card-text> <h3> $ {{ price }} </h3> </v-card-text>

            </v-flex>

            <v-flex>
     
                <v-btn color="error" @click="onStyleSubmit">Add to Cart</v-btn>
            </v-flex>

              <!-- <v-text-field v-model="foo" type="number" label="Number" append-outer-icon="add"
              @click:append-outer="increment" prepend-icon="remove" @click:prepend="decrement"></v-text-field> -->
          </v-card>
        </v-flex>
      </v-layout> 
    </v-container>
</template>
<script>
export default {
    created(){
        console.log('The id is: ' + this.$route.params.id);
    },

    data () {
     return {
       productName: "Atmos 65",
       price:"180.00"
     }
  },

}
</script>
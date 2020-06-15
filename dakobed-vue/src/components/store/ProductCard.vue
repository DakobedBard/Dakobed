<template>
  <div>
    
    <v-layout row wrap>
        <v-flex md6>
            
          <v-card tile flat @click="productClick()">
            <v-card-text @click="productClick()">
                <h2><router-link tag="span" :to="'/tabs'">{{productName}}</router-link></h2>
            </v-card-text>
            <v-img :src="imageURL" height="200px" width="120" @click="productClick()"></v-img>
          </v-card>
        </v-flex>
        <v-flex md6>
          <v-card tile flat >
            <v-card-text> $ {{ productPrice }}</v-card-text>
          </v-card>
        </v-flex>
      </v-layout> 

    <router-view></router-view>
  </div>
</template>
<script>



export default {
    props:{
        productID:String,
        imageURL:String,
        productName: String,
        productPrice: Number
    },
    methods:{
        productClick(){
            console.log("I get clicked")
        }
    }
}
</script>
<style scoped>
li a {
    text-decoration: none;
}
</style>
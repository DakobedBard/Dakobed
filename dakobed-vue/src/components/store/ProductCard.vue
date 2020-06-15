<template>
  <div>
    
    <v-layout row wrap>
        <v-flex md6>
          <v-card dark tile flat color="error">
            <v-card-text>#1</v-card-text>
          </v-card>
        </v-flex>
        <v-flex md6>
          <v-card dark tile flat color="pink darken-4">
            <v-card-text>#2</v-card-text>
          </v-card>
        </v-flex>
      </v-layout> 


    <v-card >
        <v-card-title>
            {{ productName }}
        </v-card-title>
      <v-img :src="imageURL" height="200px" width="100"></v-img>
        
     </v-card>
  
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
}
</script>
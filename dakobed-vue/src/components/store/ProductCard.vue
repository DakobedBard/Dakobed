<template>
  <div>
    
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
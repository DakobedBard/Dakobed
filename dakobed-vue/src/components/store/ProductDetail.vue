<template>
    <v-container>
      <v-layout row wrap>
        <v-flex md6>
          <v-card dark tile flat color="error">
            <v-card-text>Product Detail</v-card-text>
          </v-card>
        </v-flex>
        <v-flex md6>
          <v-card dark tile flat color="pink darken-4">
            <v-card-text>#2</v-card-text>
          </v-card>
        </v-flex>
      </v-layout> 
    </v-container>
</template>
<script>
export default {
    created(){
        console.log('The id is: ' + this.$route.params.id);
    }
    
}
</script>
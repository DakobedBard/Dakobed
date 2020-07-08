<template>
    <div class="text-center">
      
      <v-card>
        <v-card-title>
            GuitarSet transcription training data      
         </v-card-title>
      </v-card>
      
      <v-pagination dark
        v-model="page"
        :length="8"

        circle
      ></v-pagination>
    </div>
</template>

<script>
export default {
    methods:{


      nextPage(){
         this.pageNumber++;
      },
      prevPage(){
        this.pageNumber--;
    }
  },
  props:{

  },

  data(){
    return {
        page: 1,
    }
  }
}
</script>
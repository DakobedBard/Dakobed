<template>
  <div>
    <v-card-title class="display-1 "> Leave a Comment </v-card-title>
      <v-form>
        <v-layout>
          <v-flex xs12>
            <v-form>
    
              <v-text-field v-model="email"  label="Email"  style="min-height: 46px"> </v-text-field>
              <v-text-field v-model="name" label="Name" style="min-height: 46px"> </v-text-field>

              <v-textarea v-model="message" height=200 width =900 filled label="Leave a message" rows="1"></v-textarea>
              <v-btn @click="postComment()">Post Comment </v-btn>
            </v-form>
         </v-flex>
       </v-layout>         
    </v-form>
  </div>

</template>
<script>
export default {



    data(){
        return{
          email: '',
          name:'',
          message: ''
        }        
    },
    methods:{
        postComment(){

        }
    }

}
</script>
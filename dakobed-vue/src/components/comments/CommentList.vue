<template>
  <div>
        Comments
    <v-list > 
        <v-list-item v-for="comment in comments" :key="comment.id" >
          <v-flex xs12 sm6 offset-sm3 >
                <v-list-item-content>
                    <v-list-item-title v-text="comment.username">

                    </v-list-item-title>
                </v-list-item-content>
            </v-flex>
      </v-list-item>
    </v-list>

    <v-container>
        <v-layout>
            <v-flex>
                <CommentPost />
            </v-flex>
        </v-layout>
    </v-container>

  </div>
</template>

<script>

import CommentPost from './CommentPost'



export default {
    components:{
        CommentPost
    },
    created(){
        console.log("and the commensts are " + this.comments)
        this.comments.forEach(element => {
            console.log("comment person " + element.comment)
        });
    },
    data(){
        return{
          report:{}
        }        
    },
    props:{
        comments:Array
    }
}
</script>
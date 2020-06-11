<template>
  <div>

    <!-- <div v-if="getCurrentUser == '-1'"> -->

  <v-container>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-card >
            <v-card-text>
                <v-container>
                    <v-form>
                      <v-layout>
                        <v-flex xs12>
                           <div v-if="authSelection === 'login'">
                                <v-text-field name ="username" label = "username" id = "username" v-model ="username"></v-text-field>
                                <v-text-field name ="password" label = "password" id = "password" v-model ="password"></v-text-field>
  
                                <v-btn @click="login()">Login</v-btn>
                                <v-btn @click="authSelection='register'">Sign Up</v-btn>
                            </div>
                            <div v-if="authSelection === 'register'">
                             <v-text-field name ="email" label = "email" id = "email" v-model ="email"></v-text-field>
                                <v-text-field name ="username" label = "username" id = "username" v-model ="username"></v-text-field>
                                <v-text-field name ="password" label = "password" id = "password" v-model ="password"></v-text-field>
                                <v-text-field name ="password2" label = "password2" id = "password2" v-model ="password2"></v-text-field>
                                <v-btn @click="register()">Register</v-btn>
                            </div>
                        </v-flex>
                      </v-layout>
                      
                    </v-form>
                </v-container>
            </v-card-text>

        </v-card>
      </v-flex>
    </v-layout>
  </v-container>

  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";

// import { Register } from './Register'
// import { Login } from './Login'

export default {
    components:{

    },
    data(){
        return{
            email: '',
            password: '',
            password2:'',
            username:'',
            authSelection:'login'
        }
    },
    methods:{
        ...mapActions(["loginUser"]),
        login(){
          this.loginUser({username:this.username,password:  this.password})
        },
        register(){
            
        },
        // onSaveChanges(){
        //     const newDate = new Date(this.start)
        // }
    },
    computed:{
        ...mapGetters(["getCurrentUser","getToken"]),
  
    },
    mixins:[]
}

</script>
<template>
  <v-container>
    <v-layout>
      <v-flex md6 offset-3>
        <v-card>
          Hello
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
    computed:{
        ...mapGetters(["getCart"]),
    },
    methods:{
        ...mapActions(["changeProductCartCount"]),
    }
}

</script>

<template>
<div>
  <PipelineNavBar />
</div>
</template>
<script>
import PipelineNavBar from './PipelineNavBar'
import { mapGetters, mapActions } from "vuex";
export default {
  
    methods:{
    ...mapActions(["setPipelineSelection"]),
  },

  data(){
    return{
      selection:1  
    }
  },


  components:{
    PipelineNavBar
  },
  computed: {
    ...mapGetters(["getPipelineSelection"]),

  }
}
</script>
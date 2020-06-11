<template>
<div>
  <PipelineNavBar />
</div>
</template>
<script>
import PipelineNavBar from './PipelineNavBar'
export default {
  data(){
    return{
      selection:1  
    }
  },


  components:{
    PipelineNavBar
  }
}
</script>
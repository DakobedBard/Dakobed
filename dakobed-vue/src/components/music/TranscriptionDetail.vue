<template>
  <div>
      Transcription Detail {{fileID}}
      {{ getNotes}}
    <!-- <audio controls autoplay>
      <source src="http://d3rak0tzwsp682.cloudfront.net/fileID3/3audio.wav" type="audio/wav">
    </audio> -->

  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  created(){
    this.fileID = this.$route.params.fileID
    this.getS3Transcription(this.fileID)
  },
  
  methods:{
    ...mapActions(["getS3Transcription"])
  },


  props:{
      // fileID:Number
  },

  data(){
    return {
        audiourl:"http://d3rak0tzwsp682.cloudfront.net/fileID" + this.fileID + "/" + this.fileID + "audio.wav",
        transcriptionurl:"http://d3rak0tzwsp682.cloudfront.net/fileID" + this.fileID + "/" + this.fileID + "transcription.json"
    }
  },
    computed: {
      ...mapGetters(["getNotes"])
    }
  


}
</script>


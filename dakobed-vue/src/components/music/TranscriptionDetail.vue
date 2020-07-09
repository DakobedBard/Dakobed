<template>
  <div>
      Transcription Detail
    
    <audio controls autoplay>
      <source src="http://d3rak0tzwsp682.cloudfront.net/fileID3/3audio.wav" type="audio/wav">
    </audio>

  </div>
</template>

<script>

export default {
  props:{
      fileID:Number
  },

  data(){
    return {
        audiourl:"http://d3rak0tzwsp682.cloudfront.net/fileID" + this.fileID + "/" + this.fileID + "audio.wav",
        transcriptionurl:"http://d3rak0tzwsp682.cloudfront.net/fileID" + this.fileID + "/" + this.fileID + "transcription.json"
    }
  }
  


}
</script>


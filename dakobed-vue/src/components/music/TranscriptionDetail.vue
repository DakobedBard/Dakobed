<template>
  <div>
      Transcription Detail
    
    <audio controls autoplay>
      <source src="http://d3rak0tzwsp682.cloudfront.net/fileID3/3audio.wav" type="audio/wav">
    </audio>

  </div>
</template>

<script>
import axios from 'axios';
export default {
  props:{
      fileID:Number
  },
  created(){
    axios.get("https://dakobed-guitarset.s3-us-west-2.amazonaws.com/fileID1/" + this.fileID + "/" + this.fileID + "transcription.json").then((response) => {
    console.log(response)

    }, (error) => {
      console.log(error);
    });
  },

  data(){
    return {
        audiourl:"http://d3rak0tzwsp682.cloudfront.net/fileID" + this.fileID + "/" + this.fileID + "audio.wav",
        transcriptionurl:"http://d3rak0tzwsp682.cloudfront.net/fileID" + this.fileID + "/" + this.fileID + "transcription.json"
    }
  }
  


}
</script>


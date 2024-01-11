<template>
    <div id="contents">
      <div class="camera">
        <video id="video" ref="video" @canplay="playVideo"></video>
        <button id="takephoto" @click="takePhoto">take Photo</button>
      </div>
  
      <canvas id= canvas ref="canvas"></canvas>
  
      <div class="output">
        <img id="photo" :src="photoSrc" alt="The screen capture will appear in this box."/>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "start-video-practice",
    data() {
      return {
        video: null,
        canvas: null,
        photoSrc: null,
        streaming: false,
        height: 0,
        width: 320
      }
    },
    mounted() {
      this.video = this.$refs.video;
      this.canvas = this.$refs.canvas;
      this.getMediaStream();
      this.clearPhoto();
    },
    methods: {
      getMediaStream() {
        navigator.mediaDevices.getUserMedia(
            {video: true, audio: true}
        ).then((stream) => {
          this.video.srcObject = stream;
          this.video.play();
        }).catch((err) => {
          console.error(`error occurred : ${err}`);
        })
      }, playVideo() {
        if (!this.streaming) {
          this.streaming = true;
          this.height = this.video.videoHeight / this.video.videoWidth * this.width;
  
          this.video.height = this.height;
          this.video.width = this.width;
          this.canvas.height = this.height;
          this.canvas.width = this.width;
        }
      }, takePhoto() {
        const context = this.canvas.getContext('2d');
        context.drawImage(this.video,0,0, this.width, this.height);
  
        this.photoSrc = this.canvas.toDataURL('image/png');
      }, clearPhoto() {
        const context = this.canvas.getContext('2d');
        context.fillStyle = "#AAA";
        context.fillRect(0, 0, this.width, this.height);
  
        this.photoSrc = this.canvas.toDataURL('image/png');
      }
    }
  }
  </script>
  
  <style scoped>
  #video {
    border: 1px solid black;
    box-shadow: 2px 2px 3px black;
    width: 320px;
    height: 240px;
  }
  
  #photo {
    border: 1px solid black;
    box-shadow: 2px 2px 3px black;
    width: 320px;
    height: 240px;
  }
  
  #canvas {
    display: none;
  }
  
  .camera {
    width: 340px;
    display: inline-block;
  }
  
  .output {
    width: 340px;
    display: inline-block;
    vertical-align: top;
  }
  
  #takephoto {
    display: block;
    position: relative;
    margin-left: auto;
    margin-right: auto;
    bottom: 32px;
    background-color: rgba(0, 150, 0, 0.5);
    border: 1px solid rgba(255, 255, 255, 0.7);
    box-shadow: 0px 0px 1px 2px rgba(0, 0, 0, 0.2);
    font-size: 14px;
    font-family: "Lucida Grande", "Arial", sans-serif;
    color: rgba(255, 255, 255, 1);
  }
  </style>
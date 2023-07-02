<template>
  <div style="height: 100%;padding: 20px;">
    <div class="avatar_part">
      <el-avatar :src="avatarImg"/>
    </div>
    <div class='msg' ref="bubble">
      {{content}}
    </div>
  </div>
</template>

<script>
import avatar from "@/assets/img/avatar.jpeg"

export default {
  name: "LeftBubble",
  props: {
    maxWidth: {
      type: String,
      default: "300px"
    },
    content: {
      type: String,
    }
  },
  mounted() {
    this.$refs.bubble.style.setProperty("max-width", this.maxWidth);
  },
  data() {
    return {
      avatarImg: avatar
    }
  }
}
</script>

<style scoped>
div:first-child {
  vertical-align: top;
}

.msg {
  display: inline-block;
  margin-left: 20px;
  min-height: 20px;
  min-width: 20px;
  padding: 8px;
  border-radius: 10px;
  background: limegreen;
  word-wrap: break-word;
}

.avatar_part {
  margin-top: 3px;
  display: inline-block;
}

</style>

<template>
  <div class="main_part">
    <div class="left_part">
      <el-scrollbar class="chat_window">
        <div>
          <LeftBubble max-width="400px"/>
        </div>
        <div>
          <LeftBubble max-width="400px"/>
        </div>
        <div>
          <LeftBubble max-width="400px"/>
        </div>
        <div>
          <LeftBubble max-width="400px"/>
        </div>
        <div>
          <RightBubble max-width="400px"/>
        </div>
        <div>
          <RightBubble max-width="400px"/>
        </div>
        <div>
          <RightBubble max-width="400px"/>
        </div>
        <div>
          <RightBubble max-width="400px"/>
        </div>
      </el-scrollbar>
      <el-input
          :disabled="this.selectedPerson === ''"
          class="submit_window"
          v-model="submitText"
          :rows="5"
          resize="none"
          type="textarea"
          placeholder="在这里输入消息"
      />
      <el-button class="btn" type="primary">发送</el-button>
    </div>
    <div class="right_part">
      <div class="person_list_part">
        <el-scrollbar height="249px">
          <p
              v-for="person in personList"
              :key="person"
              class="scrollbar-demo-item"
              @click="chatWith(person)"
          >
            {{ person }}
          </p>
        </el-scrollbar>
      </div>
      <div class="system_inform_part">
        暂无系统消息
      </div>
    </div>
  </div>
</template>

<script>
import LeftBubble from "@/components/bubble/LeftBubble";
import RightBubble from "@/components/bubble/RightBubble";
import {Right} from "@element-plus/icons-vue";

export default {
  name: "ChatRoom",
  components: {Right, LeftBubble, RightBubble},
  data() {
    return {
      submitText: '',
      selectedPerson: '',
      personList: ['小明', '小兰', '小红', '张三', '李四', '王五', '赵六'],
    }
  },
  methods: {
    chatWith(person) {
      this.selectedPerson = person
    }
  }
}
</script>

<style scoped>
.main_part {
  width: 1000px;
  height: 500px;
  background-color: white;
  border-radius: 8px;
  margin: 30px auto;
  padding: 5px;
}

.left_part {
  float: left;
  display: inline-block;
  width: 593px;
  height: 498px;
}

.chat_window {
  box-sizing: border-box;
  width: 100%;
  height: 330px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.submit_window {
  font-size: 15px;
  margin-top: 3px;
}

.el-textarea {
  --el-input-focus-border-color: darkgray;
}

.main_part .btn {
  float: right;
  width: 80px;
  height: 30px;
  margin-top: 7px;
}

.right_part {
  float: right;
  display: inline-block;
  width: 398px;
  height: 498px;
}

.person_list_part {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.scrollbar-demo-item {
  display: flex;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  height: 40px;
  margin: 3px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.system_inform_part {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  margin-top: 3px;
  height: 237px;
  padding: 3px;
}

</style>

<template>
  <div class="main_part">
    <div class="left_part">
      <el-scrollbar ref="chatWindow" class="chat_window">
        <div v-for="message in messages" :key="message">
          <div v-if="message.from != this.userId">
            <LeftBubble
                :content="message.content"
                max-width="400px"/>
          </div>
          <div v-else>
            <RightBubble
                :content="message.content"
                max-width="400px"/>
          </div>
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
      <el-button
          class="btn"
          type="primary"
          @click="submit"
      >
        发送
      </el-button>
    </div>
    <div class="right_part">
      <div class="person_list_part">
        <el-scrollbar height="493px">
          <div
              v-for="person in personList"
              :key="person"
              class="scrollbar-demo-item"
              @click="chatWith(person)"
          >
            <div style="float: left">{{ person.username }}</div>
            <div style="float: right">{{ person.status }}</div>
          </div>
        </el-scrollbar>
      </div>
    </div>
  </div>
</template>

<script>
import LeftBubble from "@/components/bubble/LeftBubble";
import RightBubble from "@/components/bubble/RightBubble";
import {Right} from "@element-plus/icons-vue";
import {getRecord} from "@/api/record";
import {getFriends} from "@/api/friend";
import {ElNotification} from "element-plus";
import {h} from "vue";

export default {
  name: "ChatRoom",
  components: {Right, LeftBubble, RightBubble},
  mounted() {
    getFriends()
        .then(res => {
          this.personList = res.data.map(item => {
            return {
              ...item,
              status: '离线'
            }
          });
          this.initWebSocket();
        })
  },
  data() {
    return {
      userId: sessionStorage.getItem("userId"),
      username: sessionStorage.getItem("username"),
      submitText: '',
      selectedPerson: '',
      personList: [],
      onlinePeople: [],
      messages: [],
      ws: '',
    }
  },
  methods: {
    initWebSocket() {
      var vue = this;
      //创建websocket
      if (window.WebSocket) {
        vue.ws = new WebSocket("ws://localhost:8088/chat");
      }
      vue.ws.onopen = function (evt) {

      }
      vue.ws.onmessage = function (evt) {
        //接收服务器推送的消息
        var res = JSON.parse(evt.data);
        //判断是系统消息还是推送给个人的消息
        if (res.isSystem) {
          //系统消息
          vue.personList.forEach((value, index) => {
            if (res.message.hasOwnProperty(value.id)) {
              vue.personList[index].status = "在线";
            } else {
              vue.personList[index].status = "离线";
            }
          })
        } else {
          if (vue.selectedPerson.id === res.from) {
            //将数据追加到聊天区
            vue.messages.push({
              id: "",
              from: vue.selectedPerson.id,
              to: vue.userId,
              content: res.message,
            })
          }
          var fromName = ''
          for (var person of vue.personList) {
            if (person.id === res.from) {
              fromName = person.username;
              break;
            }
          }
          ElNotification({
            title: '新消息',
            message: h('i', {style: 'color: teal'}, `${fromName}有新消息`),
          })
        }
      }
      vue.ws.onclose = function (evt) {

      }
    },
    chatWith(person) {
      // TODO 查询与该用户的聊天记录
      getRecord(person.id)
          .then(res => {
            console.log(res.data)
            this.messages = res.data;
          })

      // 滚动到最底端
      this.scrollToBottom();
      this.selectedPerson = person;
    },
    scrollToBottom() {
      this.$refs.chatWindow.setScrollTop(this.$refs.chatWindow.wrapRef.scrollHeight)
    },
    submit() {
      this.messages.push({
        id: '',
        from: this.userId,
        to: this.selectedPerson.id,
        content: this.submitText
      })
      this.$nextTick(() => {
        this.scrollToBottom()
      })
      //定义服务端需要的数据格式
      var message = {toId: this.selectedPerson.id, message: this.submitText};
      //将输入的数据发送给服务器
      this.ws.send(JSON.stringify(message));
      this.submitText = '';
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
  width: 693px;
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
  width: 298px;
  height: 498px;
}

.person_list_part {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.scrollbar-demo-item {
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

.scrollbar-demo-item div {
  line-height: 40px;
  margin: 0 40px 0 40px;
}

</style>

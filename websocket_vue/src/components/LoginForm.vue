<template>
  <div class="login-wrapper">
    <div class="header">Login</div>
    <div class="form-wrapper">
      <input type="text" :value="loginForm.username" name="username" placeholder="username" class="input-item">
      <input type="password" :value="loginForm.password" name="password" placeholder="password" class="input-item">
      <div class="btn" @click="submit">Login</div>
    </div>
  </div>
</template>

<script>
import {login} from "@/api/user";

export default {
  name: "LoginForm",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    submit() {
      login()
          .then(res => {
            if (res.flag) {
              //跳转到 main.html页面
              location.href = "main";
            } else {
              alert("登录失败")
            }
          })
    }
  }
}
</script>

<style scoped>
.login-wrapper {
  background-color: #fff;
  width: 358px;
  height: 588px;
  border-radius: 15px;
  padding: 0 50px;
  margin: 0 auto;
}

.header {
  font-size: 38px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}

.input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: 0;
  padding: 10px;
  border-bottom: 1px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}

.input-item:placeholder {
  text-transform: uppercase;
}

.btn {
  text-align: center;
  padding: 10px;
  width: 100%;
  margin-top: 40px;
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  color: #fff;
}

.msg {
  text-align: center;
  line-height: 88px;
}

a {
  text-decoration-line: none;
  color: #abc1ee;
}
</style>

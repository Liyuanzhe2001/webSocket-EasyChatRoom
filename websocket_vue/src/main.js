import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/global.css'
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import locale from "element-plus/lib/locale/lang/zh-cn";
import * as ElIconModules from "@element-plus/icons-vue";

const app = createApp(App)
app.use(store)
app.use(router)
app.use(ElementPlus, {
    locale,
    size: "small",
})
app.mount('#app')

//  统一注册el-icon图标
for (const iconName in ElIconModules) {
    app.component(iconName, ElIconModules[iconName]);
}

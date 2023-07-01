import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'
import HomeView from '../views/Login.vue'

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import("@/views/Login.vue")
    },
    {
        path: '/main',
        name: 'main',
        component: () => import("@/views/Main.vue")
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router

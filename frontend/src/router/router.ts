import {createRouter, createWebHistory} from "vue-router";
import MainPage from "@/pages/MainPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import WordlePage from "@/pages/WordlePage.vue";

const routes = [
    {
        path: '/',
        component: MainPage
    },
    {
        path: '/login',
        component: LoginPage
    },
    {
        path: '/wordle',
        component: WordlePage
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router

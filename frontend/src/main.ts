import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config'
import Lara from '@primevue/themes/lara'
import Button from "primevue/button";
import Toolbar from "primevue/toolbar";
import Avatar from "primevue/avatar";
import Panel from "primevue/panel";
import Image from "primevue/image"
import router from "@/router/router";
import store from "@/store/store";
import InputText from "primevue/inputtext";
import FloatLabel from "primevue/floatlabel";
import Password from "primevue/password";
import ToastService from "primevue/toastservice";
import Toast from "primevue/toast";

const app = createApp(App)

app.use(PrimeVue, {
    theme: {
        preset: Lara
    }
})

app
    .use(store)
    .use(router)
    .use(ToastService)
    .component("Button", Button)
    .component("Toolbar", Toolbar)
    .component("Avatar", Avatar)
    .component("Panel", Panel)
    .component("Image", Image)
    .component("InputText", InputText)
    .component("FloatLabel", FloatLabel)
    .component("Password", Password)
    .component("Toast", Toast)

app
    .mount('#app')

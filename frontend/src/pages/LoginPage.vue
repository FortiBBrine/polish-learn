<template>
  <div class="login-page">
    <Toast />
    <div class="login-page__form">
      <FloatLabel>
        <InputText id="username" v-model="username" />
        <label for="username">Ім'я користувача</label>
      </FloatLabel>

      <FloatLabel>
        <Password id="password" v-model="password" />
        <label for="password">Пароль</label>
      </FloatLabel>

      <Button @click="auth" label="Ввійти" severity="secondary" />
    </div>

  </div>
</template>

<script setup lang="ts">
import {computed, ref} from "vue";
import {useStore} from "vuex";
import {useRouter} from "vue-router";
import {login} from "@/api/login/loginApi";
import {useToast} from "primevue/usetoast";

const toast = useToast()
const store = useStore()
const router = useRouter()

const isAuth = computed(() => store.state.login.isAuth)

if (isAuth.value) {
  router.push('/')
}

const username = ref("")
const password = ref("")

const auth = async () => {
  const response = await login(username.value, password.value)

  if (response === null) {
    await router.push("/")
  } else if (response.token === null) {
    Object.keys(response.result).forEach(key => {
      toast.add({
        severity: "error",
        summary: "Помилка",
        detail: response.result[key],
      })
    })
  } else {
    store.commit("login/setAuth", response.token)
    await router.push("/")
  }
}

</script>

<style scoped>
.login-page {
  flex: 1;
  display: flex;
  align-items: center;
  align-content: center;
  justify-content: center;
}

.login-page__form {
  display: flex;
  gap: 30px;
  flex-direction: column;
  border: 1px solid teal;
  padding: 30px;
  border-radius: 15px;
}
</style>

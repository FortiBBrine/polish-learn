<template>
  <div class="app">
    <Toolbar>
      <template #start>
        <div class="flex items-center gap-2">
          <Button @click="router.push('/')" class="mr-2" label="Polish Learn" text plain />

          <Button @click="router.push('/lessons')" class="mr-2" label="Уроки" text plain />
          <Button @click="router.push('/tests')" class="mr-2" label="Тести" text plain />
          <Button @click="router.push('/wordle')" class="mr-2" label="Wordle" text plain />
        </div>
      </template>

      <template #end>
        <div class="flex items-center gap-2">
          <div v-show="isAuth">
            <Button label="Мій профіль" text plain />
          </div>
          <div v-show="!isAuth">
            <Button @click="router.push('/login')" label="Увійти" text plain />
            <Button label="Реєстрація" text plain />
          </div>
        </div>
      </template>
    </Toolbar>

    <div class="content">
      <router-view />
    </div>

    <Panel>
      <div class="footer">
        <div class="footer__column">
          Використані технології

          <ul>
            <li><a href="https://vuejs.org/">Vue</a></li>
            <li><a href="https://spring.io/">Spring Boot</a></li>
            <li><a href="https://kotlinlang.org/">Kotlin</a></li>
            <li><a href="https://www.typescriptlang.org/">TypeScript</a></li>
          </ul>
        </div>
        <div class="footer__column">
          Розробники

          <ul>
            <li><a href="https://github.com/FortiBBrine">FortiBrine</a></li>
          </ul>
        </div>
      </div>
    </Panel>
  </div>
</template>

<script setup lang="ts">
import {computed} from "vue";
import {useStore} from "vuex";
import {useRouter} from "vue-router";

const router = useRouter()
const store = useStore()
const isAuth = computed(() => store.state.login.isAuth)
// const account = computed(() => store.state.login.login)

// store.commit("login/setLogin", null)

// console.log(account.value)
</script>

<style>
* {
  font-family: Montserrat, serif;
  font-size: 20px;
}
.app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.content {
  display: flex;
  flex: 1;
  margin: 20px 0;
}

.footer {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}

.footer__column {
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
  flex: 1;
  flex-basis: min-content;
  margin: 20px 20px;
}

a {
  text-decoration: none;
}

strong {
  font-family: "Montserrat ExtraBold", serif;
  font-size: 20px;
}
</style>

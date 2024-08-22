<template>
  <div class="wordle-page">
    <div class="wordle-page__element">
      <div class="wordle">
        <template v-for="attempt in attempts" :key="attempt">
          <template v-for="letter in size" :key="letter">
            <div
                :style="colors[(attempt - 1) * size + letter - 1]"
                class="wordle__letter"
            >
              <template
                  v-if="((attempt - 1) * size + letter) > colors.length && ((attempt - 1) * size + letter) <= colors.length + size"
              >
                {{ currentInput[letter - 1] }}
              </template>
              <template
                v-else
                >
                {{ letters[(attempt - 1) * size + letter - 1] }}
              </template>
            </div>
          </template>
        </template>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onBeforeUnmount, onMounted, ref} from "vue";
import api from "@/api/api";
import router from "@/router/router";

const size = 6
const attempts = 7

const canWrite = ref(true)

const words = ref([])

const word = ref("")
const colors = ref(
    [

    ]
)
let letters = ""
const currentInput = ref("")

const handleKeyUp = (event: KeyboardEvent) => {
  if (!canWrite.value) return
  if (event.key === "Enter" && currentInput.value.length === size) {

    if (!words.value.includes(currentInput.value)) return

    for (let i = 0; i < currentInput.value.length; i++) {
      let letter = currentInput.value[i]

      if (word.value[i] === currentInput.value[i]) {
        colors.value.push("background-color: green")
      } else if (word.value.includes(letter)) {
        colors.value.push("background-color: yellow")
      } else {
        colors.value.push("background-color: gray")
      }
    }


    if (currentInput.value === word.value) {
      canWrite.value = false
    }

    letters += currentInput.value
    currentInput.value = ""

  }
  if (event.key === "Backspace" || event.key == "Backspace") {
    currentInput.value = currentInput.value.substring(0, currentInput.value.length - 1)
  }
  if (currentInput.value.length >= size) return
  if (event.key.length !== 1) return

  currentInput.value += event.key
}

onMounted(() => {
  api.get("/words")
      .then(response => {
        words.value = response.data
        word.value = words.value[0]
      })
  window.addEventListener("keyup", handleKeyUp)
})
onBeforeUnmount(() => {
  window.removeEventListener("keyup", handleKeyUp)
})

</script>

<style scoped>
.wordle-page {
  display: flex;
  flex: 1;
  align-items: center;
  align-content: center;
  justify-content: center;
}
.wordle-page__element {
  display: flex;
  padding: 30px;
  border-radius: 25px;
  border: 1px solid teal;
}
.wordle {
  display: grid;
  grid-template-rows: repeat(v-bind(attempts), min-content);
  grid-template-columns: repeat(v-bind(size), min-content);
  gap: 10px;
}
.wordle__letter {
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  background-color: darkgreen;
  text-transform: uppercase;
  font-weight: bold;
}
</style>

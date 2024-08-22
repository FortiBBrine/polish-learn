<template>
  <div class="main-page">
    <div class="main-page__element">
      <div style="flex: 1">
        <strong>
          Основи польської мови за кілька днів
        </strong>
        <p>
          Polish Learn - це курси польської мови, котрі навчають від нуля та сконцентровані на спілкуванні в щоденних ситуаціях та таких, які найчастіше зустрічаються після приїзду до Польщі. Завдяки їм ти швидко оволодієш основами мови та почуєш себе впевненим в Польщі.
        </p>
      </div>
      <Image
          src="/poland.png"
          :width="120 * 1.5"
          :height="112 * 1.5"
        />

    </div>

    <div class="main-page__element">
      <Image
          src="/hard-work.png"
          :width="256"
          :height="256"
      />
      <div style="flex: 1">
        <strong>
          Граматика польскої мови
        </strong>
        <p>
          Граматика польської мови включає складну систему відмінювання іменників, прикметників і дієслів, а також велику кількість суфіксів, які додають додаткові значення або зміни до слів. Проте, незважаючи на складність, польська мова є дуже логічною і структурованою, що робить її вивчення захопливим процесом.
        </p>
      </div>
    </div>

    <div class="main-page__element">
      <div style="flex: 1">
        <strong>
          Інтерактивна польська
        </strong>
        <p>
          Інтерактивні вправи відіграють важливу роль у вивченні польської мови, особливо для тих, хто прагне швидко опанувати основи чи вдосконалити вже наявні знання. Вони дозволяють не лише запам'ятовувати нові слова і граматичні правила, але й застосовувати їх на практиці, що є ключовим для успішного засвоєння мови.
        </p>
      </div>
      <Image
          src="/interaction.png"
          style="background: white; border-radius: 30px; width: 256px; height: 250px"
          :width="256"
          :height="256"
      />

    </div>
  </div>
</template>

<script setup lang="ts">


</script>

<style scoped>
.main-page {
  flex: 1;
  //background-color: teal;
}
.main-page__element {
  display: flex;
  gap: 40px;
  flex-direction: row;
  flex-wrap: wrap;
  padding: 50px;
  height: min-content;
}

</style>

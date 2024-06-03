<script setup>
// Импорт маршрутизатора
import router from './router';
</script>

<template>
  <div>
    <!-- Логотипы и ссылки на главной странице -->
    <div>
      <a href="https://vitejs.dev" target="_blank">
        <img src="/vite.svg" class="logo" alt="Vite logo" />
      </a>
      <a href="https://vuejs.org/" target="_blank">
        <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
      </a>
    </div>
    <!-- Навигация по страницам -->
    <nav>
      <router-link to="/professions">Professions</router-link> |
      <router-link to="/departments">Departments</router-link> |
      <router-link to="/employees">Employees</router-link>
    </nav>
    <!-- Место для отображения выбранной страницы -->
    <router-view></router-view>
  </div>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
nav {
  margin-top: 2em;
}
router-link {
  margin-right: 1em;
  text-decoration: none;
  color: #42b983;
}
router-link:hover {
  text-decoration: underline;
}
</style>

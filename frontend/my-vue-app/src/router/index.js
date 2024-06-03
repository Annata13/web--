// Импорт необходимых модулей из Vue Router
import { createRouter, createWebHistory } from 'vue-router';

// Импорт компонентов для маршрутизации
import Professions from '../components/Professions.vue';
import Departments from '../components/Departments.vue';
import Employees from '../components/Employees.vue';

// Определение маршрутов
const routes = [
    { path: '/professions', component: Professions },
    { path: '/departments', component: Departments },
    { path: '/employees', component: Employees }
];

// Создание экземпляра маршрутизатора
const router = createRouter({
    history: createWebHistory(),
    routes
});


// Экспорт маршрутизатора для использования в приложении
export default router;


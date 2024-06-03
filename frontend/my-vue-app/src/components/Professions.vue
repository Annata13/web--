<template>
  <div>
    <h1>Профессии</h1>
    <!-- Список профессий -->
    <ul>
      <li v-for="profession in professions" :key="profession.id">
        {{ profession.name }}
        <button @click="openEditForm(profession)">Редактировать</button>
        <button @click="deleteProfession(profession.id)">Удалить</button>
      </li>
    </ul>

    <!-- Форма для добавления новой профессии -->
    <form @submit.prevent="createProfession">
      <input v-model="newProfession.name" placeholder="Название профессии" required />
      <input v-model="newProfession.note" placeholder="Примечание" />
      <button type="submit">Добавить профессию</button>
    </form>

    <!-- Форма для редактирования существующей профессии -->
    <div v-if="isEditing">
      <h2>Редактирование профессии</h2>
      <form @submit.prevent="updateProfession">
        <input v-model="editProfession.name" placeholder="Название профессии" required />
        <input v-model="editProfession.note" placeholder="Примечание" />
        <button type="submit">Сохранить изменения</button>
        <button @click="cancelEdit">Отмена</button>
      </form>
    </div>
  </div>
</template>

<script>
import { getProfessions, createProfession, updateProfession, deleteProfession } from '../services/professionService';

export default {
  data() {
    return {
      // Список профессий
      professions: [],
      // Новая профессия
      newProfession: { name: '', note: '' },
      // Флаг, указывающий, идет ли редактирование
      isEditing: false,
      // Профессия для редактирования
      editProfession: { id: null, name: '', note: '' }
    };
  },
  async created() {
    // Загружаем список профессий при создании компонента
    this.professions = await getProfessions();
  },
  methods: {
    // Метод для создания новой профессии
    async createProfession() {
      await createProfession(this.newProfession);
      // Очищаем форму
      this.newProfession.name = '';
      this.newProfession.note = '';
      // Обновляем список профессий
      this.professions = await getProfessions();
    },
    // Открываем форму для редактирования
    openEditForm(profession) {
      this.isEditing = true;
      this.editProfession = { ...profession };
    },
    // Метод для обновления профессии
    async updateProfession() {
      try {
        await updateProfession(this.editProfession.id, this.editProfession);
        this.isEditing = false;
        this.editProfession = { id: null, name: '', note: '' };
        this.professions = await getProfessions();
      } catch (error) {
        console.error("Ошибка при обновлении профессии:", error);
      }
    },
    // Отмена редактирования
    cancelEdit() {
      this.isEditing = false;
      this.editProfession = { id: null, name: '', note: '' };
    },
    // Метод для удаления профессии
    async deleteProfession(professionId) {
      try {
        await deleteProfession(professionId);
        this.professions = await getProfessions();
      } catch (error) {
        console.error("Ошибка при удалении профессии:", error);
      }
    }
  }
};
</script>

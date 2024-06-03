<template>
  <div>
    <h1>Отделы</h1>
    <ul>
      <li v-for="department in departments" :key="department.id">
        {{ department.name }}
        <button @click="openEditForm(department)">Редактировать</button>
        <button @click="deleteDepartment(department.id)">Удалить</button>
      </li>
    </ul>
    <form @submit.prevent="createDepartment">
      <input v-model="newDepartment.name" placeholder="Название отдела" required />
      <input v-model="newDepartment.note" placeholder="Примечание" />
      <select v-model="newDepartment.parentDepartmentId">
        <option value="">Нет родительского отдела</option>
        <option v-for="dept in departments" :value="dept.id" :key="dept.id">{{ dept.name }}</option>
      </select>
      <button type="submit">Добавить отдел</button>
    </form>
    <div v-if="isEditing">
      <h2>Редактирование отдела</h2>
      <form @submit.prevent="updateDepartment">
        <input v-model="editDepartment.name" placeholder="Название отдела" required />
        <input v-model="editDepartment.note" placeholder="Примечание" />
        <select v-model="editDepartment.parentDepartmentId">
          <option value="">Нет родительского отдела</option>
          <option v-for="dept in departments" :value="dept.id" :key="dept.id">{{ dept.name }}</option>
        </select>
        <button type="submit">Сохранить изменения</button>
        <button @click="cancelEdit">Отмена</button>
      </form>
    </div>
  </div>
</template>

<script>
import { getDepartments, createDepartment, updateDepartment, deleteDepartment } from '../services/departmentService';

export default {
  data() {
    return {
      departments: [],
      newDepartment: { name: '', note: '', parentDepartmentId: '' },
      isEditing: false,
      editDepartment: { id: null, name: '', note: '', parentDepartmentId: '' }
    };
  },
  async created() {
    try {
      this.departments = await getDepartments();
      console.log(this.departments); // Логирование данных для проверки
    } catch (error) {
      console.error("Ошибка при получении данных отделов:", error);
    }
  },
  methods: {
    async createDepartment() {
      try {
        await createDepartment(this.newDepartment);
        this.newDepartment.name = '';
        this.newDepartment.note = '';
        this.newDepartment.parentDepartmentId = '';
        this.departments = await getDepartments();
        console.log(this.departments); // Логирование данных для проверки
      } catch (error) {
        console.error("Ошибка при создании отдела:", error);
      }
    },
    openEditForm(department) {
      this.isEditing = true;
      this.editDepartment = { ...department, parentDepartmentId: department.parentDepartmentId || '' };
      console.log(this.editDepartment); // Логирование данных для проверки
    },
    async updateDepartment() {
      try {
        await updateDepartment(this.editDepartment.id, this.editDepartment);
        this.isEditing = false;
        this.editDepartment = { id: null, name: '', note: '', parentDepartmentId: '' };
        this.departments = await getDepartments();
        console.log(this.departments); // Логирование данных для проверки
      } catch (error) {
        console.error("Ошибка при обновлении отдела:", error);
      }
    },
    cancelEdit() {
      this.isEditing = false;
      this.editDepartment = { id: null, name: '', note: '', parentDepartmentId: '' };
    },
    async deleteDepartment(departmentId) {
      try {
        await deleteDepartment(departmentId);
        this.departments = await getDepartments();
        console.log(this.departments); // Логирование данных для проверки
      } catch (error) {
        console.error("Ошибка при удалении отдела:", error);
      }
    }
  }
};
</script>

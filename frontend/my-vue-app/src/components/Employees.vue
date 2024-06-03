<template>
  <div>
    <h1>Сотрудники</h1>
    <ul>
      <li v-for="employee in employees" :key="employee.id">
        {{ employee.name }} ({{ employee.departmentName }} - {{ employee.professionName }})
        <button @click="openEditForm(employee)">Редактировать</button>
        <button @click="deleteEmployee(employee.id)">Удалить</button>
      </li>
    </ul>
    <form @submit.prevent="createEmployee">
      <input v-model="newEmployee.name" placeholder="ФИО" required />
      <input v-model="newEmployee.note" placeholder="Примечание" />
      <select v-model="newEmployee.departmentId">
        <option value="">Выберите отдел</option>
        <option v-for="dept in departments" :value="dept.id" :key="dept.id">{{ dept.name }}</option>
      </select>
      <select v-model="newEmployee.professionId">
        <option value="">Выберите профессию</option>
        <option v-for="prof in professions" :value="prof.id" :key="prof.id">{{ prof.name }}</option>
      </select>
      <button type="submit">Добавить сотрудника</button>
    </form>
    <div v-if="isEditing">
      <h2>Редактирование сотрудника</h2>
      <form @submit.prevent="updateEmployee">
        <input v-model="editEmployee.name" placeholder="ФИО" required />
        <input v-model="editEmployee.note" placeholder="Примечание" />
        <select v-model="editEmployee.departmentId">
          <option value="">Выберите отдел</option>
          <option v-for="dept in departments" :value="dept.id" :key="dept.id">{{ dept.name }}</option>
        </select>
        <select v-model="editEmployee.professionId">
          <option value="">Выберите профессию</option>
          <option v-for="prof in professions" :value="prof.id" :key="prof.id">{{ prof.name }}</option>
        </select>
        <button type="submit">Сохранить изменения</button>
        <button @click="cancelEdit">Отмена</button>
      </form>
    </div>
  </div>
</template>

<script>
import { getEmployees, createEmployee, updateEmployee, deleteEmployee } from '../services/employeeService';
import { getDepartments } from '../services/departmentService';
import { getProfessions } from '../services/professionService';

export default {
  data() {
    return {
      employees: [],
      departments: [],
      professions: [],
      newEmployee: { name: '', note: '', departmentId: '', professionId: '' },
      isEditing: false,
      editEmployee: { id: null, name: '', note: '', departmentId: '', professionId: '' }
    };
  },
  async created() {
    try {
      this.employees = await getEmployees();
      this.departments = await getDepartments();
      this.professions = await getProfessions();
      console.log(this.employees); // Логирование данных для проверки
    } catch (error) {
      console.error("Ошибка при получении данных сотрудников:", error);
    }
  },
  methods: {
    async createEmployee() {
      try {
        await createEmployee(this.newEmployee);
        this.newEmployee.name = '';
        this.newEmployee.note = '';
        this.newEmployee.departmentId = '';
        this.newEmployee.professionId = '';
        this.employees = await getEmployees();
        console.log(this.employees); // Логирование данных для проверки
      } catch (error) {
        console.error("Ошибка при создании сотрудника:", error);
      }
    },
    openEditForm(employee) {
      this.isEditing = true;
      this.editEmployee = { ...employee, departmentId: employee.departmentId || '', professionId: employee.professionId || '' };
      console.log(this.editEmployee); // Логирование данных для проверки
    },
    async updateEmployee() {
      try {
        await updateEmployee(this.editEmployee.id, this.editEmployee);
        this.isEditing = false;
        this.editEmployee = { id: null, name: '', note: '', departmentId: '', professionId: '' };
        this.employees = await getEmployees();
        console.log(this.employees); // Логирование данных для проверки
      } catch (error) {
        console.error("Ошибка при обновлении сотрудника:", error);
      }
    },
    cancelEdit() {
      this.isEditing = false;
      this.editEmployee = { id: null, name: '', note: '', departmentId: '', professionId: '' };
    },
    async deleteEmployee(employeeId) {
      try {
        await deleteEmployee(employeeId);
        this.employees = await getEmployees();
        console.log(this.employees); // Логирование данных для проверки
      } catch (error) {
        console.error("Ошибка при удалении сотрудника:", error);
      }
    }
  }
};
</script>

// src/services/DepartmentService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/departments';

export const getDepartments = async () => {
    const response = await axios.get(API_URL);
    return response.data;
};

export const createDepartment = async (department) => {
    const response = await axios.post(API_URL, department);
    return response.data;
};

export const updateDepartment = async (id, department) => {
    const response = await axios.put(`${API_URL}/${id}`, department);
    return response.data;
};

export const deleteDepartment = async (id) => {
    await axios.delete(`${API_URL}/${id}`);
};

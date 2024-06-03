import axios from 'axios';

const API_URL = 'http://localhost:8080/api/employees';

export async function getEmployees() {
    const response = await axios.get(API_URL);
    return response.data;
}

export async function createEmployee(employee) {
    const response = await axios.post(API_URL, employee);
    return response.data;
}

export async function updateEmployee(id, employee) {
    const response = await axios.put(`${API_URL}/${id}`, employee);
    return response.data;
}

export async function deleteEmployee(id) {
    await axios.delete(`${API_URL}/${id}`);
}

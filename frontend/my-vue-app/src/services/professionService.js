import axios from 'axios';

const API_URL = 'http://localhost:8080/api/professions';

export async function getProfessions() {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error("Ошибка при получении профессий:", error);
        throw error;
    }
}

export async function createProfession(profession) {
    try {
        const response = await axios.post(API_URL, profession);
        return response.data;
    } catch (error) {
        console.error("Ошибка при создании профессии:", error);
        throw error;
    }
}

export async function updateProfession(id, profession) {
    try {
        const response = await axios.put(`${API_URL}/${id}`, profession);
        return response.data;
    } catch (error) {
        console.error("Ошибка при обновлении профессии:", error);
        throw error;
    }
}

export async function deleteProfession(id) {
    try {
        await axios.delete(`${API_URL}/${id}`);
    } catch (error) {
        console.error("Ошибка при удалении профессии:", error);
        throw error;
    }
}

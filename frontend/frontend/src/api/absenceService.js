import api from './axios';

export const getAbsences = (page = 0, taille = 10) =>
  api.get(`/absences?page=${page}&taille=${taille}`);

export const getAbsenceById = (id) => api.get(`/absences/${id}`);

export const createAbsence = (absence) => api.post('/absences', absence);

export const updateAbsence = (id, absence) => api.put(`/absences/${id}`, absence);

export const deleteAbsence = (id) => api.delete(`/absences/${id}`);

export const getAbsencesByEtudiant = (etudiantId) =>
  api.get(`/absences/etudiant/${etudiantId}`);
import api from '../api/axios';

export const getAbsences = (page = 0, taille = 10) =>
  api.get(`/absences?page=${page}&taille=${taille}`);

export const addAbsence = (absence) =>
  api.post('/absences', absence);

export const deleteAbsence = (id) =>
  api.delete(`/absences/${id}`);

export const getAbsencesByEtudiant = (etudiantId) =>
  api.get(`/absences/etudiant/${etudiantId}`);
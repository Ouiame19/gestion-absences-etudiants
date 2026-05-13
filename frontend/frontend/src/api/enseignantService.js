import api from './axios';

// GET  /api/enseignants?page=0&taille=10
export const getEnseignants = (page = 0, taille = 10) =>
  api.get(`/enseignants?page=${page}&taille=${taille}`);

// GET  /api/enseignants/:id
export const getEnseignantById = (id) =>
  api.get(`/enseignants/${id}`);

// POST /api/enseignants
export const createEnseignant = (enseignant) =>
  api.post('/enseignants', enseignant);

// PUT  /api/enseignants/:id
export const updateEnseignant = (id, enseignant) =>
  api.put(`/enseignants/${id}`, enseignant);

// DELETE /api/enseignants/:id
export const deleteEnseignant = (id) =>
  api.delete(`/enseignants/${id}`);
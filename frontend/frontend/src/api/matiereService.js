import api from './axios';

// GET  /api/matieres?page=0&taille=10
export const getMatieres = (page = 0, taille = 10) =>
  api.get(`/matieres?page=${page}&taille=${taille}`);

// GET  /api/matieres/:id
export const getMatiereById = (id) =>
  api.get(`/matieres/${id}`);

// POST /api/matieres
export const createMatiere = (matiere) =>
  api.post('/matieres', matiere);

// PUT  /api/matieres/:id
export const updateMatiere = (id, matiere) =>
  api.put(`/matieres/${id}`, matiere);

// DELETE /api/matieres/:id
export const deleteMatiere = (id) =>
  api.delete(`/matieres/${id}`);
import api from './axios';

// GET  /api/utilisateurs?page=0&taille=10
export const getUtilisateurs = (page = 0, taille = 10) =>
  api.get(`/utilisateurs?page=${page}&taille=${taille}`);

// GET  /api/utilisateurs/:id
export const getUtilisateurById = (id) =>
  api.get(`/utilisateurs/${id}`);

// POST /api/utilisateurs
export const createUtilisateur = (utilisateur) =>
  api.post('/utilisateurs', utilisateur);

// PUT  /api/utilisateurs/:id
export const updateUtilisateur = (id, utilisateur) =>
  api.put(`/utilisateurs/${id}`, utilisateur);

// DELETE /api/utilisateurs/:id
export const deleteUtilisateur = (id) =>
  api.delete(`/utilisateurs/${id}`);
import api from './axios';

// GET  /api/etudiants?page=0&taille=10  → { content: [...], totalPages, ... }
export const getEtudiants = (page = 0, taille = 10) =>
  api.get(`/etudiants?page=${page}&taille=${taille}`);

// GET  /api/etudiants/:id
export const getEtudiantById = (id) =>
  api.get(`/etudiants/${id}`);

// POST /api/etudiants  — body: { nom, prenom, email, cne, filiere, utilisateur: { id } }
export const createEtudiant = (etudiant) =>
  api.post('/etudiants', etudiant);

// PUT  /api/etudiants/:id
export const updateEtudiant = (id, etudiant) =>
  api.put(`/etudiants/${id}`, etudiant);

// DELETE /api/etudiants/:id
export const deleteEtudiant = (id) =>
  api.delete(`/etudiants/${id}`);
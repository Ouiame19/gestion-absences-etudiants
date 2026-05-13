import { getUtilisateurs } from '../api/utilisateurService';

const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    const res = await getUtilisateurs();
    const user = res.data.content.find((u) => u.email === email);
    if (user) {
      alert(`Bienvenue ${user.nom} !`);
      // navigate('/') si tu veux rediriger
    } else {
      alert('Utilisateur non trouvé.');
    }
  } catch (err) {
    alert('Erreur de connexion.');
  }
};

const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    const res = await getUtilisateurs();
    // On cherche l'utilisateur par son email
    const user = res.data.content.find((u) => u.email === email);
    
    if (user) {
      // ✅ ÉTAPE CRUCIALE : On enregistre l'utilisateur dans le navigateur
      localStorage.setItem('user', JSON.stringify(user));
      
      alert(`Bienvenue ${user.nom} !`);
      
      // ✅ Redirection vers la page des absences
      window.location.href = '/absences'; 
    } else {
      alert('Utilisateur non trouvé.');
    }
  } catch (err) {
    alert('Erreur de connexion.');
  }
};

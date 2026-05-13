import { useState } from 'react';
import { getUtilisateurs } from '../api/utilisateurService';


function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    const res = await getUtilisateurs();
    const user = res.data.content.find((u) => u.email === email);

    if (user) {
      // 1. On enregistre l'utilisateur immédiatement
      localStorage.setItem('user', JSON.stringify(user));
      
      // 2. On affiche le message
      alert(`Bienvenue ${user.nom} !`);

      // 3. On utilise window.location pour forcer React à rafraîchir la sécurité
      window.location.href = '/absences'; 
    } else {
      alert("Email non reconnu.");
    }
  } catch (err) {
    alert("Erreur serveur.");
  }
};
  return (
    <div className="container mt-5" style={{ maxWidth: '400px' }}>
      <div className="card shadow p-4">
        <h2 className="text-center mb-4">Connexion</h2>

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label>Email</label>

            <input
              type="email"
              className="form-control"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className="mb-3">
            <label>Mot de passe</label>

            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <button className="btn btn-dark w-100">
            Se connecter
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;

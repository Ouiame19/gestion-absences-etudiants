import { useState } from 'react'; // Obligatoire pour utiliser email et password
import { getUtilisateurs } from '../api/utilisateurService';

function Login() {
  // ✅ On définit email et password ICI pour qu'ils soient reconnus
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await getUtilisateurs();
      
      // ✅ Maintenant 'email' est bien défini grâce au useState au-dessus
      const user = res.data.content.find((u) => u.email === email);

      if (user) {
        // ✅ On n'oublie pas de donner la clé au PrivateRoute
        localStorage.setItem('user', JSON.stringify(user));
        
        alert(`Bienvenue ${user.nom} !`);
        window.location.href = '/absences'; 
      } else {
        alert('Utilisateur non trouvé.');
      }
    } catch (err) {
      alert('Erreur de connexion.');
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
              onChange={(e) => setEmail(e.target.value)} // Met à jour la variable email
              required
            />
          </div>
          <div className="mb-3">
            <label>Mot de passe</label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button className="btn btn-dark w-100">Se connecter</button>
        </form>
      </div>
    </div>
  );
}

// ✅ INDISPENSABLE : Pour que LoginPage.jsx puisse l'importer
export default Login;

import { BrowserRouter, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import AbsencesPage from './pages/AbsencesPage';
import LoginPage from './pages/LoginPage';
import Navbar from './components/Navbar';

// Composant interne pour protéger les routes
const ProtectedRoute = () => {
  const user = localStorage.getItem('user');
  return user ? <Outlet /> : <Navigate to="/login" />;
};

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div className="container mt-4"> {/* Pour centrer ton contenu Bootstrap */}
        <Routes>
          {/* Routes Publiques */}
          <Route path="/login" element={<LoginPage />} />

          {/* Routes Protégées (Accessibles uniquement si connecté) */}
          <Route element={<ProtectedRoute />}>
            <Route path="/" element={<Dashboard />} />
            <Route path="/absences" element={<AbsencesPage />} />
          </Route>

          {/* Redirection si la page n'existe pas */}
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;

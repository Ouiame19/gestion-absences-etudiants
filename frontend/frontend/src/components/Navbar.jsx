import { Link } from 'react-router-dom';

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4">
      <Link className="navbar-brand" to="/">
        Gestion Absences
      </Link>

      <div className="navbar-nav">
        <Link className="nav-link" to="/">
          Dashboard
        </Link>

        <Link className="nav-link" to="/absences">
          Absences
        </Link>

        <Link className="nav-link" to="/login">
          Login
        </Link>
      </div>
    </nav>
  );
}

export default Navbar;
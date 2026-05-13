import { useEffect, useState } from 'react';
import { getAbsences, deleteAbsence } from '../services/absenceService';

function AbsenceList() {
  const [absences, setAbsences] = useState([]);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(true);
  const [erreur, setErreur] = useState(null);

  useEffect(() => {
    chargerAbsences();
  }, []);

  const chargerAbsences = async () => {
    try {
      setLoading(true);
      const response = await getAbsences();
      setAbsences(response.data.content); // ✅ Page Spring → .content
    } catch (error) {
      console.error(error);
      setErreur('Erreur lors du chargement des absences.');
    } finally {
      setLoading(false);
    }
  };

  const supprimer = async (id) => {
    if (window.confirm('Supprimer cette absence ?')) {
      try {
        await deleteAbsence(id);
        chargerAbsences();
      } catch (error) {
        alert('Erreur lors de la suppression.');
      }
    }
  };

  // ✅ filtrage sur dateAbsence (nom correct du champ backend)
  const filteredAbsences = absences.filter((a) =>
    a.dateAbsence?.toLowerCase().includes(search.toLowerCase())
  );

  if (loading) return <p className="text-center mt-4">Chargement...</p>;
  if (erreur)  return <p className="text-center mt-4 text-danger">{erreur}</p>;

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Liste des absences</h2>

      {/* Barre de recherche */}
      <input
        type="text"
        className="form-control mb-3"
        placeholder="Rechercher par date..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {filteredAbsences.length === 0 ? (
        <p className="text-muted">Aucune absence trouvée.</p>
      ) : (
        <table className="table table-bordered table-hover">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Étudiant</th>
              <th>Matière</th>
              <th>Date</th>
              <th>Justifiée</th>
              <th>Motif</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredAbsences.map((absence) => (
              <tr key={absence.id}>
                <td>{absence.id}</td>
                <td>{absence.etudiant?.nom} {absence.etudiant?.prenom}</td>
                <td>{absence.matiere?.nom}</td>
                <td>{absence.dateAbsence}</td>  {/* ✅ dateAbsence et non date */}
                <td>
                  <span className={`badge ${absence.justifiee ? 'bg-success' : 'bg-danger'}`}>
                    {absence.justifiee ? 'Oui' : 'Non'}
                  </span>
                </td>
                <td>{absence.motif || '—'}</td>
                <td>
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => supprimer(absence.id)}
                  >
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default AbsenceList;
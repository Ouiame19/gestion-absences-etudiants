import { useEffect, useState } from 'react';
import { getAbsences, deleteAbsence } from '../services/absenceService';

function AbsenceList() {

  const [absences, setAbsences] = useState([]);
  const [search, setSearch] = useState('');

  useEffect(() => {
    chargerAbsences();
  }, []);

  const chargerAbsences = async () => {
    try {
      const response = await getAbsences();

      // si backend retourne directement liste
      setAbsences(response.data);

      // si backend retourne Page Spring
      // setAbsences(response.data.content);

    } catch (error) {
      console.log(error);
    }
  };

  const supprimer = async (id) => {
    if (window.confirm('Supprimer cette absence ?')) {
      await deleteAbsence(id);
      chargerAbsences();
    }
  };

  // filtrage dynamique
  const filteredAbsences = absences.filter((a) =>
    a.date.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="container mt-4">

      <h2 className="mb-4">Liste des absences</h2>

      {/* barre recherche */}
      <input
        type="text"
        className="form-control mb-3"
        placeholder="Rechercher par date..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <table className="table table-bordered table-hover">

        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Justifiée</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>

          {filteredAbsences.map((absence) => (

            <tr key={absence.id}>

              <td>{absence.id}</td>

              <td>{absence.date}</td>

              <td>
                {absence.justifiee ? 'Oui' : 'Non'}
              </td>

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

    </div>
  );
}

export default AbsenceList;
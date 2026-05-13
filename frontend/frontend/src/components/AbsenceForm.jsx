import { useState, useEffect } from 'react';
import { addAbsence } from '../services/absenceService';
import { getEtudiants } from '../api/etudiantService';
import { getMatieres } from '../api/matiereService';

function AbsenceForm() {
  const [date, setDate]           = useState('');
  const [justifiee, setJustifiee] = useState(false);
  const [motif, setMotif]         = useState('');
  const [etudiantId, setEtudiantId] = useState('');
  const [matiereId, setMatiereId]   = useState('');
  const [etudiants, setEtudiants]   = useState([]);
  const [matieres, setMatieres]     = useState([]);

  useEffect(() => {
    getEtudiants().then((r) => setEtudiants(r.data.content));
    getMatieres().then((r) => setMatieres(r.data.content));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addAbsence({
        dateAbsence: date,
        justifiee,
        motif,
        etudiant: { id: etudiantId },
        matiere:  { id: matiereId },
      });
      alert('Absence ajoutée avec succès');
      setDate(''); setJustifiee(false);
      setMotif(''); setEtudiantId(''); setMatiereId('');
    } catch (error) {
      console.error(error);
      alert('Erreur lors de l\'ajout.');
    }
  };

  return (
    <div className="container mt-4">
      <h2>Ajouter une absence</h2>
      <form onSubmit={handleSubmit}>

        <div className="mb-3">
          <label>Étudiant</label>
          <select className="form-select" value={etudiantId}
            onChange={(e) => setEtudiantId(e.target.value)} required>
            <option value="">-- Choisir --</option>
            {etudiants.map((et) => (
              <option key={et.id} value={et.id}>
                {et.nom} {et.prenom}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label>Matière</label>
          <select className="form-select" value={matiereId}
            onChange={(e) => setMatiereId(e.target.value)} required>
            <option value="">-- Choisir --</option>
            {matieres.map((m) => (
              <option key={m.id} value={m.id}>{m.nom}</option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label>Date</label>
          <input type="date" className="form-control"
            value={date} onChange={(e) => setDate(e.target.value)} required />
        </div>

        <div className="mb-3">
          <label>Motif (optionnel)</label>
          <input type="text" className="form-control"
            value={motif} onChange={(e) => setMotif(e.target.value)} />
        </div>

        <div className="form-check mb-3">
          <input type="checkbox" className="form-check-input"
            checked={justifiee} onChange={(e) => setJustifiee(e.target.checked)} />
          <label className="form-check-label">Absence justifiée</label>
        </div>

        <button className="btn btn-primary" type="submit">Ajouter</button>
      </form>
    </div>
  );
}

export default AbsenceForm;

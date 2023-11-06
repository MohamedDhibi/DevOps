package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId) {
		Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);

		if (optionalEquipe.isPresent()) {
			return optionalEquipe.get();
		} else {
			// Handle the case where the value is not present, e.g., return a default value or throw an exception.
			// Example:
			return null;
		}
	}


	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();

		for (Equipe equipe : equipes) {
			if ((equipe.getNiveau().equals(Niveau.JUNIOR)) || (equipe.getNiveau().equals(Niveau.SENIOR))) {
				List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
				Integer nbEtudiantsAvecContratsActifs = 0;
				boolean shouldUpdateNiveau = false;

				for (Etudiant etudiant : etudiants) {
					Set<Contrat> contrats = etudiant.getContrats();

					for (Contrat contrat : contrats) {
						Date dateSysteme = new Date();
						long differenceInMillis = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
						long differenceInYears = (differenceInMillis / (1000l * 60 * 60 * 24 * 365));

						if (Boolean.FALSE.equals(contrat.getArchive()) && (differenceInYears > 1)) {
							nbEtudiantsAvecContratsActifs++;

							if (nbEtudiantsAvecContratsActifs >= 3) {
								shouldUpdateNiveau = true;
								break;
							}
						}
					}

					if (shouldUpdateNiveau) {
						break;
					}
				}

				if (shouldUpdateNiveau) {
					if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
						equipe.setNiveau(Niveau.SENIOR);
					} else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
						equipe.setNiveau(Niveau.EXPERT);
					}

					equipeRepository.save(equipe);
				}
			}
		}
	}

}
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
			if (equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR)) {
				boolean shouldUpdateNiveau = false;

				for (Etudiant etudiant : equipe.getEtudiants()) {
					if (shouldUpdateNiveau(etudiant)) {
						shouldUpdateNiveau = true;
						break;
					}
				}

				if (shouldUpdateNiveau) {
					updateNiveau(equipe);
				}
			}
		}
	}

	private boolean shouldUpdateNiveau(Etudiant etudiant) {
		Date dateSysteme = new Date();
		Set<Contrat> contrats = etudiant.getContrats();
		int nbEtudiantsAvecContratsActifs = 0;

		for (Contrat contrat : contrats) {
			if (contrat.getArchive() != null && !contrat.getArchive()) {
				long differenceInMillis = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
				long differenceInYears = (differenceInMillis / (1000L * 60 * 60 * 24 * 365));

				if (differenceInYears > 1) {
					nbEtudiantsAvecContratsActifs++;

					if (nbEtudiantsAvecContratsActifs >= 3) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private void updateNiveau(Equipe equipe) {
		Niveau niveau = equipe.getNiveau();

		if (niveau.equals(Niveau.JUNIOR)) {
			equipe.setNiveau(Niveau.SENIOR);
		} else if (niveau.equals(Niveau.SENIOR)) {
			equipe.setNiveau(Niveau.EXPERT);
		}

		equipeRepository.save(equipe);
	}

}
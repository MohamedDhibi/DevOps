package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

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
			// Handle the case where the Optional is empty
			// You can throw an exception, return a default value, or take other appropriate actions.
			throw new NoSuchElementException("Equipe not found for ID: " + equipeId);
		}
	}


	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();

		for (Equipe equipe : equipes) {
			if (equipeShouldEvolue(equipe)) {
				evolueEquipe(equipe);
			}
		}
	}

	private boolean equipeShouldEvolue(Equipe equipe) {
		if ((equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR))) {
			List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
			int nbEtudiantsAvecContratsActifs = countActiveContractStudents(etudiants);
			return nbEtudiantsAvecContratsActifs >= 3;
		}
		return false;
	}

	private int countActiveContractStudents(List<Etudiant> etudiants) {
		int nbEtudiantsAvecContratsActifs = 0;
		for (Etudiant etudiant : etudiants) {
			Set<Contrat> contrats = etudiant.getContrats();
			for (Contrat contrat : contrats) {
				if (isContractActive(contrat)) {
					nbEtudiantsAvecContratsActifs++;
					break;
				}
			}
			if (nbEtudiantsAvecContratsActifs >= 3) {
				break;
			}
		}
		return nbEtudiantsAvecContratsActifs;
	}

	private boolean isContractActive(Contrat contrat) {
		Date currentDate = new Date();
		long differenceInTime = currentDate.getTime() - contrat.getDateFinContrat().getTime();
		long differenceInYears = differenceInTime / (1000l * 60 * 60 * 24 * 365);
		return !contrat.getArchive() && differenceInYears > 1;
	}

	private void evolueEquipe(Equipe equipe) {
		if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
			equipe.setNiveau(Niveau.SENIOR);
		} else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
			equipe.setNiveau(Niveau.EXPERT);
		}
		equipeRepository.save(equipe);
	}

}
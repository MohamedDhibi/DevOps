package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService{
	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
	@Autowired
	DepartementRepository departementRepository;
	public List<Etudiant> retrieveAllEtudiants(){
		return (List<Etudiant>) etudiantRepository.findAll();
	}

	public Etudiant addEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant updateEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}


	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);

		if (optionalEtudiant.isPresent()) {
			return optionalEtudiant.get();
		} else {
			// Handle the case when the value is not present (e.g., return null or throw an exception)
			return null; // Or you can throw an exception here
		}
	}


	public void removeEtudiant(Integer idEtudiant){
		Etudiant e=retrieveEtudiant(idEtudiant);
		etudiantRepository.delete(e);
	}


	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
		Optional<Departement> departementOptional = departementRepository.findById(departementId);

		if (etudiantOptional.isPresent() && departementOptional.isPresent()) {
			Etudiant etudiant = etudiantOptional.get();
			Departement departement = departementOptional.get();
			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
		} else {
			throw new EntityNotFoundException("Etudient or Departement not found");
		}
	}


	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		Contrat c = contratRepository.findById(idContrat).orElse(null);
		Equipe eq = equipeRepository.findById(idEquipe).orElse(null);

		if (c != null && eq != null) {
			c.setEtudiant(e);
			eq.getEtudiants().add(e);
			return e;
		} else {
			throw new EntityNotFoundException("Contrat or Equipe not found");
		}
	}


	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
		return  etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
	}
}
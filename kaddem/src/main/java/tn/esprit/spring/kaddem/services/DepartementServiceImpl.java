package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService{
	private final DepartementRepository departementRepository;

	@Autowired
	public DepartementServiceImpl(DepartementRepository departementRepository) {
		this.departementRepository = departementRepository;
	}
	public List<Departement> retrieveAllDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement addDepartement (Departement d){
		return departementRepository.save(d);
	}

	public   Departement updateDepartement (Departement d){
		return departementRepository.save(d);
	}

	public Departement retrieveDepartement(Integer idDepart) {
		Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

		if (optionalDepartement.isPresent()) {
			return optionalDepartement.get();
		} else {
			// Handle the case where the Optional is empty, e.g., return null or throw an exception
			return null; // You can choose an appropriate fallback behavior
		}
	}


	public  void deleteDepartement(Integer idDepartement){
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}



}
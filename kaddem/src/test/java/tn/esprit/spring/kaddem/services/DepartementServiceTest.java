package tn.esprit.spring.kaddem.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllDepartements() {
        // Arrange
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "Departement 1"));
        departements.add(new Departement(2, "Departement 2"));

        Mockito.when(departementRepository.findAll()).thenReturn(departements);

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Departement 1", result.get(0).getNomDepart());
        assertEquals("Departement 2", result.get(1).getNomDepart());
    }

    @Test
    void addDepartement() {
        // Arrange
        Departement newDepartement = new Departement(3, "New Departement");

        Mockito.when(departementRepository.save(newDepartement)).thenReturn(newDepartement);

        // Act
        Departement addedDepartement = departementService.addDepartement(newDepartement);

        // Assert
        assertNotNull(addedDepartement);
        assertEquals("New Departement", addedDepartement.getNomDepart());
    }

    @Test
    void updateDepartement() {
        // Arrange
        Departement existingDepartement = new Departement(4, "Existing Departement");

        Mockito.when(departementRepository.save(existingDepartement)).thenReturn(existingDepartement);

        // Act
        Departement updatedDepartement = departementService.updateDepartement(existingDepartement);

        // Assert
        assertNotNull(updatedDepartement);
        assertEquals("Existing Departement", updatedDepartement.getNomDepart());
    }

    @Test
    void retrieveDepartement() {
        // Arrange
        int departementId = 5;
        Departement departement = new Departement(departementId, "Departement 5");

        Mockito.when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        // Act
        Departement retrievedDepartement = departementService.retrieveDepartement(departementId);

        // Assert
        assertNotNull(retrievedDepartement);
        assertEquals("Departement 5", retrievedDepartement.getNomDepart());
    }

    @Test
    void deleteDepartement() {
        // Arrange
        int departementId = 6;
        Departement departement = new Departement(departementId, "Departement 6");

        Mockito.when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        // Act
        departementService.deleteDepartement(departementId);

        // Assert
        Mockito.verify(departementRepository, Mockito.times(1)).delete(departement);
    }
}
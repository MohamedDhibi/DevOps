package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class EtudiantServiceTest {
    @Mock
    EtudiantRepository etudiantRepository; // Mock the repository

    @InjectMocks
    EtudiantServiceImpl etudiantService; // The service to be tested
   /* @Mock
    DepartementRepository departementRepository ;
    @InjectMocks
    DepartementServiceImpl departementService;
    @Mock
    ContratRepository contratRepository ;
    @InjectMocks
    ContratServiceImpl contratService;
    @Mock
    EquipeRepository equipeRepository ;
    @InjectMocks
    EquipeServiceImpl equipeService;// The service to be tested
*/

    /*@Test
    void testCreateEtudiant() {
        // Arrange
        Etudiant etudiantToCreate = new Etudiant();
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiantToCreate);

        // Act
        Etudiant createdEtudiant = etudiantService.addEtudiant(etudiantToCreate);

        // Assert
        assertNotNull(createdEtudiant);
        verify(etudiantRepository, times(1)).save(etudiantToCreate);
    }
*/

    @Test
    void testAddEtudiant() {
        // Create a sample Etudiant object
        Etudiant sampleEtudiant = new Etudiant();
        // Mock the behavior of the repository
        when(etudiantRepository.save(sampleEtudiant)).thenReturn(sampleEtudiant);

        // Call the method you want to test
        Etudiant result = etudiantService.addEtudiant(sampleEtudiant);

        // Verify that the repository's save method was called
        verify(etudiantRepository, times(1)).save(sampleEtudiant);

        // Add assertions to check the result, if necessary
        // For example, you can check if the returned object is the same as the one you passed in.
        assertEquals(sampleEtudiant, result);
    }

    @Test
    void testUpdateEtudiant() {
        // Create a sample Etudiant object
        Etudiant sampleEtudiant = new Etudiant();

        // Mock the behavior of the repository to return the same sampleEtudiant object when saved
        when(etudiantRepository.save(sampleEtudiant)).thenReturn(sampleEtudiant);

        // Call the method you want to test
        Etudiant result = etudiantService.updateEtudiant(sampleEtudiant);

        // Verify that the repository's save method was called once with the sampleEtudiant object
        verify(etudiantRepository, times(1)).save(sampleEtudiant);

        // Add assertions to check the result, if necessary
        // For example, you can check if the returned object is the same as the sampleEtudiant.
        assertEquals(sampleEtudiant, result);
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Arrange
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1, "John", "Doe", Option.GAMIX));
        etudiants.add(new Etudiant(2, "Alice", "Smith", Option.SE));

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNomE());
        assertEquals("Doe", result.get(0).getPrenomE());
        assertEquals(Option.GAMIX, result.get(0).getOp());
        assertEquals("Alice", result.get(1).getNomE());
        assertEquals("Smith", result.get(1).getPrenomE());
        assertEquals(Option.SE, result.get(1).getOp());
    }
    @Test
    void testRetrieveEtudiant() {
        // Create a sample Etudiant object
        Etudiant sampleEtudiant = new Etudiant();

        // Mock the behavior of the repository to return the same sampleEtudiant object when findById is called
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(sampleEtudiant));

        // Call the method you want to test
        Etudiant result = etudiantService.retrieveEtudiant(1); // Pass an arbitrary ID (1 in this case)

        // Verify that the repository's findById method was called once with the provided ID
        verify(etudiantRepository, times(1)).findById(1);

        // Add assertions to check the result, if necessary
        // For example, you can check if the returned object is the same as the sampleEtudiant.
        assertEquals(sampleEtudiant, result);
    }
    @Test
    void testRemoveEtudiant() {
        // Arrange
        Etudiant sampleEtudiant = new Etudiant();

        // Mock the behavior of the repository to return the same sampleEtudiant object when findById is called
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(sampleEtudiant));

        // Call the method you want to test
        etudiantService.removeEtudiant(1); // Pass an arbitrary ID (1 in this case)

        // Verify that the repository's findById method was called once with the provided ID
        verify(etudiantRepository, times(1)).findById(1);

        // Verify that the repository's delete method was called once with the sampleEtudiant object
        verify(etudiantRepository, times(1)).delete(sampleEtudiant);
    }

   /* @Test
    void testAssignEtudiantToDepartement() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();

        // Mock the behavior of the repository to return the sample etudiant when findById is called
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));

        // Call the method you want to test
        etudiantService.assignEtudiantToDepartement(1, 1); // Pass arbitrary IDs

        // Verify that the repository's findById methods were called with the provided IDs
        verify(etudiantRepository, times(1)).findById(1);
        verify(departementRepository, times(1)).findById(1);

        // Verify that the etudiant's departement was set to the sample departement
        assertNotNull(etudiant.getDepartement());
        assertEquals(departement, etudiant.getDepartement());
    }

*/
  /*  @Test
  void testAddAndAssignEtudiantToEquipeAndContract() {
      // Arrange
      Etudiant sampleEtudiant = new Etudiant();
      Contrat sampleContrat = new Contrat();
      Equipe sampleEquipe = new Equipe();

      // Mock the behavior of the repository to return the same sampleEtudiant, sampleContrat, and sampleEquipe objects
      when(contratRepository.findById(anyInt())).thenReturn(Optional.of(sampleContrat));
      when(equipeRepository.findById(anyInt())).thenReturn(Optional.of(sampleEquipe));

      // Call the method you want to test
      Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(sampleEtudiant, 1, 2); // Pass arbitrary IDs (1 for contrat, 2 for equipe)

      // Verify that the repository's findById methods were called once with the provided IDs
      verify(contratRepository, times(1)).findById(1);
      verify(equipeRepository, times(1)).findById(2);

      // Verify that the sampleEtudiant is associated with sampleContrat and sampleEquipe
      assertEquals(sampleContrat, sampleEtudiant.getContrats().iterator().next());
      assertEquals(sampleEquipe, sampleEtudiant.getEquipes().get(0));

      // Add assertions to check the result, if necessary
      // For example, you can check if the returned object is the same as the sampleEtudiant.
      assertEquals(sampleEtudiant, result);
  }
*/

}

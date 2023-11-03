package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import java.util.ArrayList;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EtudiantServicetest {
    @Mock
    EtudiantRepository etudiantRepository; // Mock the repository

    @InjectMocks
    EtudiantServiceImpl etudiantService; // The service to be tested

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


}

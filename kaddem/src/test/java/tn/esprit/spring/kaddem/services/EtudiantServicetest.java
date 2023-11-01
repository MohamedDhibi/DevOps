package tn.esprit.spring.kaddem.services;
import org.testng.annotations.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EtudiantServicetest{
    @Mock
    private EtudiantRepository etudiantRepository; // Mock the repository

    @InjectMocks
    private EtudiantServiceImpl etudiantService; // The service to be tested

    @Test
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


}

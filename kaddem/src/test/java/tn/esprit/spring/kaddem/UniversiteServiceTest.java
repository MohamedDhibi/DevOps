package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {
    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Test
    void testAddUniversite() {
        // Create a sample Universite object
        Universite sampleUniversite = new Universite();
        // Mock the behavior of the repository
        when(universiteRepository.save(sampleUniversite)).thenReturn(sampleUniversite);

        // Call the method you want to test
        Universite result = universiteService.addUniversite(sampleUniversite);

        // Verify that the repository's save method was called
        verify(universiteRepository, times(1)).save(sampleUniversite);

        // Add assertions to check the result, if necessary
        // For example, you can check if the returned object is the same as the one you passed in.
        assertEquals(sampleUniversite, result);
    }
}

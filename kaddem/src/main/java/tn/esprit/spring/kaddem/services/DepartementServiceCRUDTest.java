import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartementServiceCRUDTest {

    @Mock
    private DepartementRepository departementRepository; // Mock the repository

    @InjectMocks
    private DepartementService departementService; // The service to be tested

    @Test
    public void testCreateDepartement() {
        // Arrange
        Departement departementToCreate = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departementToCreate);

        // Act
        Departement createdDepartement = departementService.createDepartement(departementToCreate);

        // Assert
        assertNotNull(createdDepartement);
        verify(departementRepository, times(1)).save(eq(departementToCreate));
    }

    @Test
    public void testRetrieveDepartement() {
        // Arrange
        Integer departementId = 1;
        Departement departement = new Departement();
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        // Act
        Departement retrievedDepartement = departementService.retrieveDepartement(departementId);

        // Assert
        assertNotNull(retrievedDepartement);
        verify(departementRepository, times(1)).findById(eq(departementId));
    }

    @Test
    public void testUpdateDepartement() {
        // Arrange
        Integer departementId = 1;
        Departement updatedDepartement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(updatedDepartement);

        // Act
        Departement result = departementService.updateDepartement(updatedDepartement);

        // Assert
        assertNotNull(result);
        verify(departementRepository, times(1)).save(eq(updatedDepartement));
    }

    @Test
    public void testDeleteDepartement() {
        // Arrange
        Integer departementId = 1;

        // Act
        departementService.deleteDepartement(departementId);

        // Assert
        verify(departementRepository, times(1)).deleteById(eq(departementId));
    }
}

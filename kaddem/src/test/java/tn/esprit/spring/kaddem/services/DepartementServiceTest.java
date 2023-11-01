package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DepartementServiceTest {
    @Mock
    DepartementRepository departementRepository; // Mock the repository

    @InjectMocks
    DepartementServiceImpl departementService; // The service to be tested

    @Test
    void testCreateDepartement() {
        // Arrange
        Departement departementToCreate = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departementToCreate);

        // Act
        Departement createdDepartement = departementService.addDepartement(departementToCreate);

        // Assert
        assertNotNull(createdDepartement);
        verify(departementRepository, times(1)).save(any(Departement.class));
    }


}

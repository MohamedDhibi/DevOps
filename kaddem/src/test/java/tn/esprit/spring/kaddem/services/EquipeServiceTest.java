package tn.esprit.spring.kaddem.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EquipeServiceTest {
    @Mock
    EquipeRepository equipeRepository;
    @InjectMocks
    EquipeServiceImpl equipeService;
    Equipe equipe1 = new Equipe(1, "Equipe 1", Niveau.JUNIOR);
    Equipe equipe2 = new Equipe(2, "Equipe 2", Niveau.SENIOR);
    List<Equipe> equipes = Arrays.asList(equipe1, equipe2);

    @Test
    void testRetrieveAllEquipes() {
        // Arrange

        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);

        // Act
        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(2, retrievedEquipes.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        // Arrange
        Equipe newEquipe = new Equipe("New Equipe", Niveau.JUNIOR);

        Mockito.when(equipeRepository.save(any(Equipe.class))).thenReturn(newEquipe);

        // Act
        Equipe addedEquipe = equipeService.addEquipe(newEquipe);

        // Assert
        assertNotNull(addedEquipe);
        assertEquals("New Equipe", addedEquipe.getNomEquipe());
        verify(equipeRepository, times(1)).save(any(Equipe.class));
    }

    @Test
    void updateEquipe() {
        // Arrange
        Equipe newEquipe = new Equipe("New Equipe", Niveau.JUNIOR);

        Mockito.when(equipeRepository.save(newEquipe)).thenReturn(newEquipe);

        // Act
        Equipe updatedEquipe = equipeService.updateEquipe(newEquipe);

        // Assert
        assertNotNull(updatedEquipe);
        assertEquals("New Equipe", updatedEquipe.getNomEquipe());
    }

    @Test
    void retrieveEquipe() {
        // Arrange
        int EquipetId = 5;
        Equipe newEquipe = new Equipe(EquipetId, "New Equipe 5", Niveau.JUNIOR);

        Mockito.when(equipeRepository.findById(EquipetId)).thenReturn(Optional.of(newEquipe));

        // Act
        Equipe retrievedEquipe = equipeService.retrieveEquipe(EquipetId);

        // Assert
        assertNotNull(retrievedEquipe);
        assertEquals("New Equipe 5", retrievedEquipe.getNomEquipe());
    }

    @Test
    void deleteDepartement() {
        // Arrange
        int EquipetId = 6;
        Equipe newEquipe = new Equipe(EquipetId, "New Equipe 6", Niveau.JUNIOR);

        Mockito.when(equipeRepository.findById(EquipetId)).thenReturn(Optional.of(newEquipe));

        // Act
        equipeService.deleteEquipe(EquipetId);

        // Assert
        Mockito.verify(equipeRepository, Mockito.times(1)).delete(newEquipe);
    }


}



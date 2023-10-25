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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplMock {
    @Mock
    EquipeRepository equipeRepository;
    @InjectMocks
    EquipeServiceImpl equipeService;
    Equipe equipe1 = new Equipe(1, "Equipe 1", Niveau.JUNIOR);
    Equipe equipe2 = new Equipe(2, "Equipe 2", Niveau.SENIOR);
    List<Equipe> equipes = Arrays.asList(equipe1, equipe2);

    @Test
    public void testRetrieveAllEquipes() {
        // Arrange

        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);

        // Act
        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(2, retrievedEquipes.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    public void testAddEquipe() {
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

}

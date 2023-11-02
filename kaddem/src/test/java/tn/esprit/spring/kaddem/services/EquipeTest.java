package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EquipeTest {
    EquipeRepository equipeRepository;
    @Autowired
    EquipeServiceImpl equipeService;
    @Test
    @Order(1)
    void testretrieveAllEquipes()
    {
        List<Equipe> equipeList =equipeService.retrieveAllEquipes();
        Assertions.assertEquals(0 ,equipeList.size());
    }
    @Test
    @Order(2)
    void testaddEquipe () throws ParseException {
        Equipe equipe = new Equipe( );
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Equipe");
        equipe.setNiveau(Niveau.JUNIOR);
        Equipe e =equipeService.addEquipe(equipe);
        Assertions.assertNotNull(equipe.getIdEquipe()); // Assuming getIdEquipe returns the ID
        Assertions.assertEquals("Equipe", equipe.getNomEquipe());

        Assertions.assertEquals(Niveau.JUNIOR, equipe.getNiveau());

    }
    @Test
    @Order(3)
    void testretrieveEquipe()
    {
        Equipe e=equipeService.retrieveEquipe(1);
        Assertions.assertEquals(1,e.getIdEquipe());

    }
}

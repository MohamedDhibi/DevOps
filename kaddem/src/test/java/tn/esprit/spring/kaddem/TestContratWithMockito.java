package tn.esprit.spring.kaddem;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
 class TestContratWithMockito {



    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository ;

    @InjectMocks
    private IContratService contratService = new ContratServiceImpl();

    @Test
    void testRetrieveAllContrats() {
        List<Contrat> listContrats = Arrays.asList(
                new Contrat(1, new Date(), new Date(), Specialite.CLOUD, true, 3000),
                new Contrat(2, new Date(), new Date(), Specialite.RESEAUX, true, 4000),
                new Contrat(3, new Date(), new Date(), Specialite.SECURITE, true, 5000)
        );
        when(contratRepository.findAll()).thenReturn(listContrats);
        List<Contrat> list = contratService.retrieveAllContrats();
        assertEquals(3, list.size());
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    void testAddContratWithMockito() {
        Contrat contrat = new Contrat(1, new Date(), new Date(), Specialite.CLOUD, true, 3000);
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat addedContrat = contratService.addContrat(contrat);
        verify(contratRepository).save(contrat);
        assertEquals(contrat, addedContrat);
    }

    // Similar updates for other test methods...

    @Test
    void testGetChiffreAffaireEntreDeuxDates() {
        List<Contrat> mockContrats = Arrays.asList(
                new Contrat(1, new Date(2023, Calendar.FEBRUARY, 1), new Date(2025, Calendar.DECEMBER, 31), Specialite.CLOUD, true, 1500),
                new Contrat(2, new Date(2024, Calendar.FEBRUARY, 3), new Date(2025, Calendar.DECEMBER, 31), Specialite.RESEAUX, true, 2000),
                new Contrat(3, new Date(2025, Calendar.FEBRUARY, 4), new Date(2025, Calendar.DECEMBER, 31), Specialite.SECURITE, true, 2500),
                new Contrat(4, new Date(2026, Calendar.FEBRUARY, 5), new Date(2025, Calendar.DECEMBER, 31), Specialite.CLOUD, true, 3000)
        );

        Date startDate = new Date(2023, Calendar.FEBRUARY, 1);
        Date endDate = new Date(2025, Calendar.DECEMBER, 31);

        Logger logger = (Logger) LoggerFactory.getLogger(ContratServiceImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        when(contratRepository.findAll()).thenReturn(mockContrats);

         contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        List<ILoggingEvent> logEvents = listAppender.list;
        assertEquals(0, logEvents.size());
    }
}

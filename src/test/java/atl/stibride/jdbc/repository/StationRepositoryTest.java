package atl.stibride.jdbc.repository;

import atl.stibride.jdbc.dao.StationsDao;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class StationRepositoryTest {

    @Mock
    private StationsDao mockDao;

    private final List<StationDto> allStations;

    public StationRepositoryTest() {
        allStations = List.of(
                new StationDto(8012, "DE BROUCKERE"),
                new StationDto(8022, "GARE CENTRALE"),
                new StationDto(8032, "PARC"),
                new StationDto(8042, "ARTS-LOI"),
                new StationDto(8052, "MAELBEEK")
        );
    }

    @BeforeEach
    void setUp() throws RepositoryException {
        /* Mockito behavior */
        Mockito.lenient()
                .when(mockDao.selectAll())
                .thenReturn(allStations);
    }

    @Test
    void testGetAll() throws RepositoryException {
        System.out.println("testGetAll");
        // Arrange
        StationRepository stationRepository = new StationRepository(mockDao);
        // Action
        List<StationDto> result = stationRepository.getAll();
        // Assert
        Mockito.verify(mockDao, times(1)).selectAll();
        assertEquals(allStations, result);
    }
}
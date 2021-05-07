package atl.stibride.jdbc.repository;

import atl.stibride.jdbc.dao.FavoritesDao;
import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class FavoriteRepositoryTest {

    @Mock
    private FavoritesDao mockDao;

    /**
     * Maison will exist in the dao.
     */
    private static final Pair<Integer, Integer> MAISON_KEYS = new Pair<>(8032, 8052);
    private final FavoriteDto MAISON
            = new FavoriteDto(MAISON_KEYS.getKey(), MAISON_KEYS.getValue(), "maison");

    /**
     * Ecole will not exist in the dao.
     */
    private static final Pair<Integer, Integer> ECOLE_KEYS = new Pair<>(8052, 8032);
    private static final FavoriteDto ECOLE
            = new FavoriteDto(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue(), "ecole");
    
    private final List<FavoriteDto> allFavorites;

    public FavoriteRepositoryTest() {
        allFavorites = List.of(MAISON, ECOLE);
    }

    @BeforeEach
    void setUp() throws RepositoryException {
        /* Mock dao behavior */

        // Select All
        Mockito.lenient()
                .when(mockDao.selectAll())
                .thenReturn(allFavorites);
        // Select
        Mockito.lenient()
                .when(mockDao.select(MAISON_KEYS.getKey(), MAISON_KEYS.getValue()))
                .thenReturn(MAISON); // Maison exists
        Mockito.lenient()
                .when(mockDao.select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue()))
                .thenReturn(null); // Ecole does not exists
        Mockito.lenient()
                .when(mockDao.select(null, null))
                .thenThrow(RepositoryException.class);
        // SelectNameById
        Mockito.lenient()
                .when(mockDao.selectNameById(MAISON_KEYS.getKey(), MAISON_KEYS.getValue()))
                .thenReturn(MAISON.getName());
        Mockito.lenient()
                .when(mockDao.selectNameById(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue()))
                .thenReturn(null);
        Mockito.lenient()
                .when(mockDao.selectNameById(null, null))
                .thenThrow(RepositoryException.class);
    }

    @Test
    void testAddWhenExisting() throws RepositoryException {
        System.out.println("testAddWhenExisting");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Action
        repository.add(MAISON);
        //Assert
        Mockito.verify(mockDao, times(1))
                .select(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        Mockito.verify(mockDao, times(1))
                .update(MAISON);
        Mockito.verify(mockDao, times(0))
                .insert(any(FavoriteDto.class));
        assertEquals(MAISON, repository.get(MAISON_KEYS.getKey(), MAISON_KEYS.getValue()));
    }

    @Test
    public void testAddWhenNotExisting() throws RepositoryException {
        System.out.println("testAddWhenNotExisting");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Action
        repository.add(ECOLE);
        //Assert
        Mockito.verify(mockDao, times(1))
                .select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        Mockito.verify(mockDao, times(0))
                .update(ECOLE);
        Mockito.verify(mockDao, times(1))
                .insert(any(FavoriteDto.class));
        assertNull(repository.get(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue()));
    }

    @Test
    public void testAddIncorrectParameter() throws RepositoryException {
        System.out.println("testAddIncorrectParameter");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Assert
        assertThrows(NullPointerException.class, () -> {
            // Action
            repository.add(null);
        });
        Mockito.verify(mockDao, times(0))
                .select(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        Mockito.verify(mockDao, times(0))
                .update(MAISON);
        Mockito.verify(mockDao, times(0))
                .insert(any(FavoriteDto.class));
    }

    @Test
    public void testRemoveExisting() throws RepositoryException {
        System.out.println("testRemoveExisting");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Action
        repository.remove(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        // Assert
        Mockito.verify(mockDao, times(1))
                .delete(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
    }

    @Test
    public void testRemoveNotExisting() throws RepositoryException {
        System.out.println("testRemoveNotExisting");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Assert
        assertThrows(RepositoryException.class, () -> {
            // Action
            repository.remove(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        });
        Mockito.verify(mockDao, times(1))
                .select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        Mockito.verify(mockDao, times(0))
                .delete(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
    }

    @Test
    public void testRemoveIncorrectParameter() throws RepositoryException {
        System.out.println("testRemoveIncorrectParameter");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Assert
        assertThrows(RepositoryException.class, () -> {
            // Action
            repository.remove(null, null);
        });
        Mockito.verify(mockDao, times(1))
                .select(null, null);
        Mockito.verify(mockDao, times(0))
                .delete(null, null);
    }

    @Test
    public void testGetAllExisting() throws RepositoryException {
        System.out.println("testGetAllExisting");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Action
        List<FavoriteDto> result = repository.getAll();
        // Assert
        Mockito.verify(mockDao, times(1))
                .selectAll();
        assertEquals(allFavorites, result);
    }


    @Test
    public void testGetExist() throws RepositoryException {
        System.out.println("testGetExist");
        //Arrange
        FavoriteDto expected = MAISON;
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Action
        FavoriteDto result = repository.get(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mockDao, times(1))
                .select(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
    }

    @Test
    public void testGetNotExist() throws RepositoryException {
        System.out.println("testGetNotExist");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Action
        FavoriteDto result = repository.get(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        //Assert
        assertNull(result);
        Mockito.verify(mockDao, times(1))
                .select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
    }

    @Test
    public void testGetIncorrectParameter() throws RepositoryException {
        System.out.println("testGetIncorrectParameter");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            repository.get(null, null);
        });
        Mockito.verify(mockDao, times(1))
                .select(null, null);
    }

    @Test
    public void testContainsExisting() throws RepositoryException {
        System.out.println("testContainsExisting");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Action
        boolean expected = repository.contains(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        // Assert
        Mockito.verify(mockDao, times(1))
                .select(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        assertTrue(expected);
    }

    @Test
    public void testContainsNotExisting() throws RepositoryException {
        System.out.println("testContainsNotExisting");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Action
        boolean expected = repository.contains(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        // Assert
        Mockito.verify(mockDao, times(1))
                .select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        assertFalse(expected);
    }

    @Test
    public void testContainsIncorrectParameter() throws RepositoryException {
        System.out.println("testContainsIncorrectParameter");
        // Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        // Assert
        assertThrows(RepositoryException.class, () -> {
            // Action
            repository.contains(null, null);
        });
        Mockito.verify(mockDao, times(1)).select(null, null);
    }

    @Test
    public void testGetFavoriteNameByIdExist() throws RepositoryException {
        System.out.println("testGetFavoriteNameByIdExist");
        //Arrange
        String expected = MAISON.getName();
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Action
        String result = repository.getFavoriteNameById(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mockDao, times(1))
                .selectNameById(MAISON_KEYS.getKey(), MAISON_KEYS.getValue());
    }

    @Test
    public void testGetFavoriteNameByIdNotExist() throws RepositoryException {
        System.out.println("testGetFavoriteNameByIdNotExist");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Action
        String result = repository.getFavoriteNameById(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
        //Assert
        assertNull(result);
        Mockito.verify(mockDao, times(1))
                .selectNameById(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue());
    }

    @Test
    public void testGetFavoriteNameByIdIncorrectParameter() throws RepositoryException {
        System.out.println("testGetFavoriteNameByIdIncorrectParameter");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mockDao);
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            String result = repository.getFavoriteNameById(null, null);
        });
        Mockito.verify(mockDao, times(1))
                .selectNameById(null, null);
    }
}
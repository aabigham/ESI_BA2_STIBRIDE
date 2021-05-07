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

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class FavoriteRepositoryTest {

    private static final Pair<Integer, Integer> MAISON_KEYS = new Pair<>(8032, 8052);
    private final FavoriteDto MAISON
            = new FavoriteDto(MAISON_KEYS.getKey(), MAISON_KEYS.getValue(), "maison");

    private static final Pair<Integer, Integer> ECOLE_KEYS = new Pair<>(8052, 8032);
    private static final FavoriteDto ECOLE
            = new FavoriteDto(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue(), "ecole");

    private List<FavoriteDto> allFavorites;

    @Mock
    private FavoritesDao mockDao;

    public FavoriteRepositoryTest() {
        allFavorites = List.of(MAISON, ECOLE);
    }

    @BeforeEach
    void setUp() throws RepositoryException {
        // Mock dao select behavior
        Mockito.lenient()
                .when(mockDao.selectAll())
                .thenReturn(allFavorites);
        Mockito.lenient()
                .when(mockDao.select(MAISON_KEYS.getKey(), MAISON_KEYS.getValue()))
                .thenReturn(MAISON);
        Mockito.lenient()
                .when(mockDao.select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue()))
                .thenReturn(null);
        Mockito.lenient()
                .when(mockDao.select(null, null))
                .thenThrow(RepositoryException.class);
    }

    @Test
    void testAddWhenExisting() {
    }

    @Test
    public void testAddWhenNotExisting() {

    }

    @Test
    public void testAddIncorrectParameter() {

    }

    @Test
    public void testRemoveExisting() {

    }

    @Test
    public void testRemoveNotExisting() {

    }

    @Test
    public void testRemoveIncorrectParameter() {

    }

    @Test
    public void testGetAllExisting() {

    }


    @Test
    public void testGetExist() {

    }

    @Test
    public void testGetNotExist() {

    }

    @Test
    public void testGetIncorrectParameter() {

    }

    @Test
    public void testContainsExisting() {

    }

    @Test
    public void testContainsNotExisting() {

    }

    @Test
    public void testContainsIncorrectParameter() {
    }

    @Test
    void getFavoriteNameById() {
    }
}
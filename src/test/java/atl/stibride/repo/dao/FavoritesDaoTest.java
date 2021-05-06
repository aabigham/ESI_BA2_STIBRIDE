package atl.stibride.repo.dao;

import atl.stibride.config.ConfigManager;
import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.exceptions.RepositoryException;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class runs the tests of the favorites dao.
 */
class FavoritesDaoTest {

    private FavoritesDao instance;

    // Parc => Maelbeek, inside Db
    private static final Pair<Integer, Integer> MAISON_KEYS = new Pair<>(8032, 8052);
    private final FavoriteDto MAISON
            = new FavoriteDto(MAISON_KEYS.getKey(), MAISON_KEYS.getValue(), "maison");

    // Maelbeek => Parc, not inside Db
    private static final Pair<Integer, Integer> ECOLE_KEYS = new Pair<>(8052, 8032);
    private static final FavoriteDto ECOLE
            = new FavoriteDto(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue(), "ecole");

    private final List<FavoriteDto> allFavorites;

    public FavoritesDaoTest() {
        allFavorites = new ArrayList<>();
        allFavorites.add(new FavoriteDto(8222, 8062, "HANKAR => SCHUMAN"));
        allFavorites.add(new FavoriteDto(8462, 8112, "RIBAUCOURT => TOMBERG"));
        allFavorites.add(new FavoriteDto(8161, 8132, "STOCKEL => VANDERVELDE"));
        allFavorites.add(new FavoriteDto(8132, 8804, "VANDERVELDE => STUYVENBERGH"));
        allFavorites.add(MAISON);

        /*try {
            ConfigManager.getInstance().load();
            instance = FavoritesDao.getInstance();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }*/
    }

    @BeforeEach
    void setUp() {
        try {
            ConfigManager.getInstance().load();
            instance = FavoritesDao.getInstance();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsertExists() {
        System.out.println("testInsertExists");
        // Arrange
        FavoriteDto existing = MAISON;
        // Assert
        assertThrows(RepositoryException.class, () -> {
            // Action
            instance.insert(existing);
        });
    }

    @Test
    void testInsertNotExists() throws RepositoryException {
        System.out.println("testInsertNotExists");
        // Arrange
        Pair<Integer, Integer> expected = ECOLE_KEYS;
        // Action
        Pair<Integer, Integer> result = instance.insert(ECOLE);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testDeleteExists() throws RepositoryException {
        System.out.println("testDeleteExists");
        // Arrange
        Pair<Integer, Integer> to_delete = MAISON_KEYS;
        // Action
        instance.delete(to_delete.getKey(), to_delete.getValue());
        FavoriteDto expected = instance.select(to_delete.getKey(), to_delete.getValue());
        // Assert
        assertNull(expected);
    }

    @Test
    void testDeleteNotExists() throws RepositoryException {
        System.out.println("testDeleteNotExists");
        // Arrange
        Pair<Integer, Integer> to_delete = ECOLE_KEYS;
        // Action
        instance.delete(to_delete.getKey(), to_delete.getValue());
        // Assert
        assertNull(instance.select(ECOLE_KEYS.getKey(), ECOLE_KEYS.getValue()));
    }

    @Test
    void testUpdate() throws RepositoryException {
        System.out.println("testUpdate");
        // Arrange
        FavoriteDto to_update = new FavoriteDto(
                MAISON.getFirstKey(),
                MAISON.getSecondKey(),
                "NewName"
        );
        // Action
        instance.update(to_update);
        FavoriteDto expected = instance.select(MAISON.getFirstKey(), MAISON.getSecondKey());
        // Assert
        assertEquals(expected.getName(), to_update.getName());
    }

    @Test
    void testSelectAll() throws RepositoryException {
        System.out.println("testSelectAll");
        // Arrange
        List<FavoriteDto> expected = allFavorites;
        // Action
        List<FavoriteDto> result = instance.selectAll();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testSelectExists() throws RepositoryException {
        System.out.println("testSelectExists");
        // Arrange
        FavoriteDto expected = MAISON;
        // Action
        FavoriteDto result = instance.select(expected.getFirstKey(), expected.getSecondKey());
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testSelectNotExists() throws RepositoryException {
        System.out.println("testSelectNotExists");
        // Arrange
        FavoriteDto to_select = ECOLE;
        // Action
        FavoriteDto result = instance.select(to_select.getFirstKey(), to_select.getSecondKey());
        // Assert
        assertNull(result);
    }

    @Test
    void testSelectNameById() throws RepositoryException {
        System.out.println("testSelectNameById");
        // Arrange
        String expected = MAISON.getName();
        // Action
        String result = instance.selectNameById(MAISON.getFirstKey(), MAISON.getSecondKey());
        // Assert
        assertEquals(expected, result);
    }
}
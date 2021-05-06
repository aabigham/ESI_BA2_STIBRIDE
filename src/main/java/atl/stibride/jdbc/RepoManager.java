package atl.stibride.jdbc;

import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import atl.stibride.jdbc.repository.FavoriteRepository;
import atl.stibride.jdbc.repository.StationRepository;

import java.util.List;

/**
 * This class manages the requests to the databases.
 */
public class RepoManager {

    /* The metro stations repository */
    private StationRepository stationRepo;
    /* The favorite repository */
    private FavoriteRepository favoriteRepository;

    /**
     * Constructor of RepoManager.
     */
    public RepoManager() {
        try {
            this.stationRepo = new StationRepository();
            this.favoriteRepository = new FavoriteRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /*============================*/
    /*========= STATIONS =========*/
    /*============================*/

    /**
     * Gets every metro stations as a list.
     *
     * @return every metro stations, as a List.
     * @throws RepositoryException if there was a database error.
     */
    public List<StationDto> getAllStations() throws RepositoryException {
        return stationRepo.getAll();
    }

    /*============================*/
    /*========= FAVORITES ========*/
    /*============================*/

    /**
     * Gets all the favorites stations of the user from the repository.
     *
     * @return all the favorites stations of the user.
     * @throws RepositoryException if there was a database error.
     */
    public List<FavoriteDto> getAllFavorites() throws RepositoryException {
        return favoriteRepository.getAll();
    }

    /**
     * Adds a favorite Ride to the repository.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @param name        the name of the favorite.
     * @throws RepositoryException if there was a database error.
     */
    public void addFavorite(Integer origin, Integer destination, String name) throws RepositoryException {
        favoriteRepository.add(new FavoriteDto(origin, destination, name));
    }

    /**
     * Removes a favorite ride from the repository.
     *
     * @param origin      the origin station id.
     * @param destination the destination station id.
     * @throws RepositoryException if there was a database error.
     */
    public void removeFavorite(Integer origin, Integer destination) throws RepositoryException {
        favoriteRepository.remove(origin, destination);
    }

    /**
     * Gets the name of a favorite ride from the repository.
     *
     * @param origin      the origin station id.
     * @param destination the destination station id.
     * @return the name of a favorite ride.
     * @throws RepositoryException if there was a database error.
     */
    public String getFavName(Integer origin, Integer destination) throws RepositoryException {
        return favoriteRepository.getFavoriteNameById(origin, destination);
    }
}

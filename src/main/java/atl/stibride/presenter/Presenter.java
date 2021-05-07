package atl.stibride.presenter;

import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import atl.stibride.model.Model;
import atl.stibride.model.Ride;
import atl.stibride.model.validation.StationValidation;
import atl.stibride.observer.Observable;
import atl.stibride.observer.Observer;
import atl.stibride.view.View;
import org.javatuples.Triplet;

import java.util.List;

/**
 * The presenter acts upon the model and the view.
 * It retrieves data from repositories (the model),
 * and formats it for display in the view.
 */
public class Presenter implements Observer {

    private final Model model;
    private final View view;

    /**
     * Instaciates the Presenter.
     *
     * @param model the model.
     * @param view  the view.
     */
    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Initializes the view via the model datas,
     * in this case it initializes the metro stations along
     * with the favorite ones.
     *
     * @throws RepositoryException if there was a database error.
     */
    public void initialize() throws RepositoryException {
        /* All stations */
        List<StationDto> stations = model.getAllStations();
        view.initComboBoxes(stations);

        /* All Favorites */
        List<FavoriteDto> favorites = model.getAllFavorites();
        view.initFavorites(model.getAllFavorites());

        view.addHandlers(this);
    }

    /**
     * Starts searching the best ride between the two selected stations.
     */
    public void searchRide() {
        System.out.println("Search button");
        view.disableButtons();
        try {
            StationDto origin = view.getOrigin();
            StationDto destination = view.getDestination();
            model.computeRide(origin, destination);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    /**
     * Adds the selected ride to the user's favorites.
     */
    public void addToFavorite() {
        System.out.println("Add to favorite button");
        view.disableButtons();
        try {
            StationDto origin = view.getOrigin();
            StationDto destination = view.getDestination();
            StationValidation.validateStations(origin, destination);
            String name = view.showFavNamePopup(origin.getName() + " => " + destination.getName());
            model.addToFavorite(origin, destination, name);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    /**
     * Launches the selected favorite ride.
     */
    public void launchFavorite() {
        System.out.println("Launch favorite button");
        view.disableButtons();
        try {
            // Favorite is stored as a Pair
            Integer origin = view.getFavorite().getKey();
            Integer destination = view.getFavorite().getValue();
            model.computeRide(
                    model.getStationDto(origin),
                    model.getStationDto(destination)
            );
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    /**
     * Removes a favorites ride from the user's favorites.
     */
    public void removeFavorite() {
        System.out.println("Remove favorite button");
        view.disableButtons();
        try {
            // Favorite is stored as a Pair
            Integer origin = view.getFavorite().getKey();
            Integer destination = view.getFavorite().getValue();
            model.removeFavorite(origin, destination);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    /**
     * Allows the editing of user's favorite ride,
     * according to the user's input.
     */
    public void editFavorite() {
        System.out.println("Edit favorite button");
        view.disableButtons();
        try {
            // Selected favorite
            Integer origin = view.getFavorite().getKey();
            Integer destination = view.getFavorite().getValue();
            String favName = model.getFavName(origin, destination);

            // New values to be inserted by the user
            Triplet<Integer, Integer, String> newValues
                    = view.showEditPopup(model.getAllStations(), favName);
            Integer newOrigin = newValues.getValue0();
            Integer newDestination = newValues.getValue1();
            StationValidation.validateStations(newOrigin, newDestination);
            String newName = newValues.getValue2();

            // Remove old favorite
            model.removeFavorite(origin, destination);
            // Add new favorite
            model.addToFavorite(
                    model.getStationDto(newOrigin),
                    model.getStationDto(newDestination),
                    newName
            );
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    /**
     * Updates the view and presents it to the user.
     *
     * @param observable the observable object.
     * @param arg        an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("model update");
        Model model = (Model) observable;
        try {
            Ride ride = model.getRide();
            if (ride != null) {
                view.showRide(ride);
                view.showFeedBack(ride.getPathSize());
            }
            view.initFavorites(model.getAllFavorites());
        } catch (RepositoryException e) {
            view.showException(e.getMessage());
        }
    }


}

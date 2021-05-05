package atl.stibride.presenter;

import atl.stibride.model.Model;
import atl.stibride.model.Ride;
import atl.stibride.observer.Observable;
import atl.stibride.observer.Observer;
import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.dto.StationDto;
import atl.stibride.repo.exceptions.RepositoryException;
import atl.stibride.view.View;
import org.javatuples.Triplet;

import java.util.List;

public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initialize() throws RepositoryException {
        List<StationDto> stations = model.getAllStations();
        view.initComboBoxes(stations);

        List<FavoriteDto> favorites = model.getAllFavorites();
        view.initFavorites(favorites);

        view.addHandlers(this);
    }

    public void searchRide() {
        System.out.println("Search button");
        view.disableButtons();
        StationDto origin = view.getOrigin();
        StationDto destination = view.getDestination();
        try {
            model.computeRide(origin, destination);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    public void addToFavorite() {
        System.out.println("Add to favorite button");
        view.disableButtons();
        StationDto origin = view.getOrigin();
        StationDto destination = view.getDestination();
        String name = view.showFavNamePopup(origin.getName() + " => " + destination.getName());
        try {
            model.addToFavorite(origin, destination, name);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    public void launchFavorite() {
        System.out.println("Launch favorite button");
        view.disableButtons();
        // Favorite is a stored as a Pair
        Integer origin = view.getFavorite().getKey();
        Integer destination = view.getFavorite().getValue();
        try {
            model.computeRide(
                    model.getStationDto(origin),
                    model.getStationDto(destination)
            );
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    public void removeFavorite() {
        System.out.println("Remove favorite button");
        view.disableButtons();
        // Favorite is a stored as a Pair
        Integer origin = view.getFavorite().getKey();
        Integer destination = view.getFavorite().getValue();
        try {
            model.removeFavorite(origin, destination);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    public void editFavorite() {
        System.out.println("Edit favorite button");
        view.disableButtons();
        // TODO popup window
        Integer origin = view.getFavorite().getKey();
        Integer destination = view.getFavorite().getValue();
        try {
            String favName = model.getFavName(origin, destination);
            Triplet<Integer, Integer, String> newValues
                    = view.showEditPopup(model.getAllStations(), favName);
            // Add new
            model.addToFavorite(
                    model.getStationDto(newValues.getValue0()),
                    model.getStationDto(newValues.getValue1()),
                    newValues.getValue2()
            );
            // Remove old
            model.removeFavorite(origin, destination);
        } catch (Exception e) {
            view.showException(e.getMessage());
        }
        view.enableButtons();
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("model update");
        Model model = (Model) observable;
        try {
            Ride ride = model.getRide();
            if (ride != null) {
                view.showRide(ride);
            }
            view.initFavorites(model.getAllFavorites());
        } catch (IllegalAccessException | RepositoryException e) {
            view.showException(e.getMessage());
        }
    }


}

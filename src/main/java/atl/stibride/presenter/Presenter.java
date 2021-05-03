package atl.stibride.presenter;

import atl.stibride.dto.StationDto;
import atl.stibride.model.Model;
import atl.stibride.observer.Observable;
import atl.stibride.observer.Observer;
import atl.stibride.repository.RepositoryException;
import atl.stibride.view.View;

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
        view.addHandlers(this);
    }

    public void searchRide() {
        view.disableButtons();
        System.out.println("Search button");
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
        // TODO
        view.enableButtons();
    }

    public void launchFavorite() {
        System.out.println("Launch favorite button");
        view.disableButtons();
        // TODO
        view.enableButtons();
    }

    public void removeFavorite() {
        System.out.println("Remove favorite button");
        view.disableButtons();
        // TODO
        view.enableButtons();
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("model update");
        Model model = (Model) observable;
        try {
            //Ride ride = model.getRide();
            view.showRide(model.getRide());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // TODO
    }
}

package atl.stibride.presenter;

import atl.stibride.dto.StationDto;
import atl.stibride.model.Model;
import atl.stibride.model.Ride;
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
        List<StationDto> stations = model.getStations();
        view.initComboBoxes(stations);
        view.addHandlers(this);
    }

    public void doSomething() {
        // TODO
    }

    public void searchRide() {
        System.out.println("Search button");
        StationDto origin = view.getOrigin();
        StationDto destination = view.getDestination();
        model.computeRide(origin, destination);
    }

    public void addToFavorite() {
        // TODO
        System.out.println("Add to favorite button");
    }

    public void launchFavorite() {
        // TODO
        System.out.println("Launch favorite button");
    }

    public void removeFavorite() {
        // TODO
        System.out.println("Remove favorite button");
    }

    @Override
    public void update(Observable observable, Object arg) {
        Model model = (Model) observable;
        Ride ride;
        try {
            ride = model.getRide();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // TODO
    }
}

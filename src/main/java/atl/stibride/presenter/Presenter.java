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
        List<StationDto> stations = model.getStations();
        view.initComboBoxes(stations);
        view.addHandlers(this);
    }

    public void doSomething() {
        // TODO
    }

    @Override
    public void update(Observable observable, Object arg) {
        // TODO
    }
}

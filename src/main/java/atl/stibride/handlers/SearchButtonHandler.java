package atl.stibride.handlers;

import atl.stibride.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SearchButtonHandler implements EventHandler<ActionEvent> {

    private final Presenter presenter;

    public SearchButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // TODO
        presenter.searchRide();
    }
}

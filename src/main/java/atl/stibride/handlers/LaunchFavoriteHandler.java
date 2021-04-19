package atl.stibride.handlers;

import atl.stibride.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LaunchFavoriteHandler implements EventHandler<ActionEvent> {

    private final Presenter presenter;

    public LaunchFavoriteHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // TODO
        System.out.println("Launch favorite button");
        presenter.doSomething();
    }
}
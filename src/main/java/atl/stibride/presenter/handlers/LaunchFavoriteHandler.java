package atl.stibride.presenter.handlers;

import atl.stibride.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the launch favorite button.
 */
public class LaunchFavoriteHandler implements EventHandler<ActionEvent> {

    /**
     * The Presenter to link the button to.
     */
    private final Presenter presenter;

    /**
     * Constructor of the handler.
     *
     * @param presenter the Presenter to link the button to.
     */
    public LaunchFavoriteHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param actionEvent the event which occurred
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        presenter.launchFavorite();
    }
}
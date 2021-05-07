package atl.stibride.presenter.handler;

import atl.stibride.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the remove favorite button.
 */
public class SearchButtonHandler implements EventHandler<ActionEvent> {

    /**
     * The Presenter to link the button to.
     */
    private final Presenter presenter;

    /**
     * Constructor of the handler.
     *
     * @param presenter the Presenter to link the button to.
     */
    public SearchButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param actionEvent the event which occurred.
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        presenter.searchRide();
    }
}

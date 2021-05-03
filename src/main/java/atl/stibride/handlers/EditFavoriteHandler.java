package atl.stibride.handlers;

import atl.stibride.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EditFavoriteHandler implements EventHandler<ActionEvent> {
    private final Presenter presenter;

    public EditFavoriteHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        presenter.editFavorite();
    }
}

package atl.stibride.view;

import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.model.Ride;
import atl.stibride.presenter.Presenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.javatuples.Triplet;

import java.io.IOException;
import java.util.List;

/**
 * The view is a passive interface that displays data (the model)
 * and routes user commands (events) to the presenter to act upon that data.
 */
public class View {

    /* The fxml controller */
    private final FXMLController fxmlController;

    /**
     * Constructor of view .
     *
     * @param stage the stage to show.
     * @throws IOException if the fxml could not load.
     */
    public View(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view.fxml"));
        Parent root = loader.load();
        fxmlController = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stib ride !");
        stage.show();
    }

    /**
     * Updates the view's ride.
     *
     * @param ride the ride to show.
     */
    public void update(Ride ride) {
        fxmlController.showRide(ride);
        fxmlController.setFeedBack(ride.getPathSize());
    }

    /**
     * Updates the view's favorites list.
     *
     * @param allFavorites the favorites to show.
     */
    public void update(List<FavoriteDto> allFavorites) {
        fxmlController.setFavoritesListView(allFavorites);
    }

    /**
     * Initializes the combo boxes that show the stations
     *
     * @param stations the stations of the metro network.
     */
    public void initStationsSearchBoxes(List<StationDto> stations) {
        fxmlController.initStationsSearchBoxes(stations);
    }

    /**
     * Initializes the favorite rides of the user.
     *
     * @param favorites the favorite rides of the user.
     */
    public void setFavoritesListView(List<FavoriteDto> favorites) {
        fxmlController.setFavoritesListView(favorites);
    }

    /**
     * Adds the button handlers to the presenter.
     *
     * @param presenter the presenter in the MVP pattern.
     */
    public void addHandlers(Presenter presenter) {
        fxmlController.addHandlers(presenter);
    }

    /**
     * Gets the selected origin station.
     *
     * @return the selected origin station.
     */
    public StationDto getOrigin() {
        return fxmlController.getOrigin();
    }

    /**
     * Gets the selected destination station.
     *
     * @return the selected destination station.
     */
    public StationDto getDestination() {
        return fxmlController.getDestination();
    }

    /**
     * Gets the stations keys of the favorite selected ride.
     *
     * @return the stations keys of the favorite selected ride.
     */
    public Pair<Integer, Integer> getFavorite() {
        return fxmlController.getFavorite();
    }

    /**
     * Disables all the buttons.
     */
    public void disableButtons() {
        fxmlController.disableButtons();
    }

    /**
     * Enables all the buttons.
     */
    public void enableButtons() {
        fxmlController.enableButtons();
    }

    /**
     * Shows an exception dialog to the user.
     *
     * @param message the message to show inside the dialog.
     */
    public void showException(String message) {
        fxmlController.showException(message);
    }

    /**
     * Shows a dialog for the user to choose a name to give its favorite ride.
     *
     * @param default_str the default name.
     * @return the name to give its favorite ride.
     */
    public String showFavNamePopup(String default_str) {
        return fxmlController.showFavNamePopup(default_str);
    }

    /**
     * Shows a dialog for the user to edit its favorite ride.
     *
     * @param stations    every stations to choose from.
     * @param default_str the default name of the ride.
     * @return a triplet containing all of the new values inserted by the user.
     * @throws IllegalArgumentException is a value was missing.
     */
    public Triplet<Integer, Integer, String> showEditFavoritePopup(List<StationDto> stations, String default_str)
            throws IllegalArgumentException {
        return fxmlController.showEditFavoritePopup(stations, default_str);
    }

}

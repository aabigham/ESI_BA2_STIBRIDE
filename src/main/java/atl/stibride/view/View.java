package atl.stibride.view;

import atl.stibride.model.Ride;
import atl.stibride.presenter.Presenter;
import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.dto.StationDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.javatuples.Triplet;

import java.io.IOException;
import java.util.List;

public class View {

    private final FXMLController fxmlController;

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

    public void initComboBoxes(List<StationDto> stations) {
        fxmlController.initComboBoxes(stations);
    }

    public void initFavorites(List<FavoriteDto> favorites) {
        fxmlController.initFavorites(favorites);
    }

    public void addHandlers(Presenter presenter) {
        fxmlController.addHandlers(presenter);
    }

    public StationDto getOrigin() {
        return fxmlController.getOrigin();
    }

    public StationDto getDestination() {
        return fxmlController.getDestination();
    }

    public Pair<Integer, Integer> getFavorite() {
        return fxmlController.getFavorite();
    }

    public void showRide(Ride ride) {
        fxmlController.showRide(ride);
    }

    public void showException(String message) {
        fxmlController.showException(message);
    }

    public void disableButtons() {
        fxmlController.disableButtons();
    }

    public void enableButtons() {
        fxmlController.enableButtons();
    }

    public String showFavNamePopup(String default_str) {
        return fxmlController.showFavNamePopup(default_str);
    }

    public Triplet<Integer, Integer, String> showEditPopup(List<StationDto> stations, String default_str)
            throws IllegalArgumentException {
        return fxmlController.showEditPopup(stations, default_str);
    }
}

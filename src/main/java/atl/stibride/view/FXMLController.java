package atl.stibride.view;

import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.model.Ride;
import atl.stibride.presenter.Presenter;
import atl.stibride.presenter.handler.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import org.controlsfx.control.SearchableComboBox;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Optional;

/**
 * The FXML Controller class which contains all of the Javafx nodes.
 */
public class FXMLController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView stibLogo;
    @FXML
    private SearchableComboBox<StationDto> originSearch;
    @FXML
    private SearchableComboBox<StationDto> destinationSearch;
    @FXML
    private Button searchButton;
    @FXML
    private Button addFavorite;
    @FXML
    private TableView<StationDto> tableView;
    @FXML
    private TableColumn<StationDto, String> stationsCol;
    @FXML
    private TableColumn<StationDto, String> lignesCol;
    @FXML
    private ListView<FavoriteDto> listFavorite;
    @FXML
    private Button launchFavorite;
    @FXML
    private Button removeFavorite;
    @FXML
    private Button editFavorite;
    @FXML
    private Label leftStatus;
    @FXML
    private Label rightStatus;

    /**
     * Initializes the fxml.
     */
    @FXML
    void initialize() {
        /*Image metro_img = new Image(getClass().getResourceAsStream("/img/metro.png"));
        scrollPane.setContent(new ImageView(metro_img));
        Image logo_img = new Image(getClass().getResourceAsStream("/img/logo.png"));
        stibLogo.setImage(logo_img);*/
        //
        leftStatus.setText("Aucune recherche lancée");
        rightStatus.setText("Nombre de stations : 0");
        //
        stationsCol.setCellValueFactory(new PropertyValueFactory<StationDto, String>("name"));
        lignesCol.setCellValueFactory(new PropertyValueFactory<StationDto, String>("lines"));
    }

    /**
     * Initializes the combo boxes that show the stations
     *
     * @param stations the stations of the metro network.
     */
    void initComboBoxes(List<StationDto> stations) {
        ObservableList<StationDto> stationsObs = FXCollections.observableList(stations);
        originSearch.setItems(stationsObs);
        originSearch.getSelectionModel().select(0);
        destinationSearch.setItems(stationsObs);
        destinationSearch.getSelectionModel().select(0);
    }

    /**
     * Initializes the favorite rides of the user.
     *
     * @param favorites the favorite rides of the user.
     */
    void initFavorites(List<FavoriteDto> favorites) {
        ObservableList<FavoriteDto> favoritesObs = FXCollections.observableList(favorites);
        listFavorite.setItems(favoritesObs);
        listFavorite.getSelectionModel().select(0);
    }

    /**
     * Adds the button handlers to the presenter.
     *
     * @param presenter the presenter in the MVP pattern.
     */
    void addHandlers(Presenter presenter) {
        searchButton.setOnAction(new SearchButtonHandler(presenter));
        addFavorite.setOnAction(new AddToFavoriteHandler(presenter));
        launchFavorite.setOnAction(new LaunchFavoriteHandler(presenter));
        removeFavorite.setOnAction(new RemoveFavoriteHandler(presenter));
        editFavorite.setOnAction(new EditFavoriteHandler(presenter));
    }

    /**
     * Gets the selected origin station.
     *
     * @return the selected origin station.
     */
    StationDto getOrigin() {
        return originSearch.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets the selected destination station.
     *
     * @return the selected destination station.
     */
    StationDto getDestination() {
        return destinationSearch.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets the stations keys of the favorite selected ride.
     *
     * @return the stations keys of the favorite selected ride.
     */
    Pair<Integer, Integer> getFavorite() {
        FavoriteDto favorite = listFavorite.getSelectionModel().getSelectedItem();
        return new Pair<>(favorite.getFirstKey(), favorite.getSecondKey());
    }

    /**
     * Displays a ride to the user.
     *
     * @param ride the ride to display to the user.
     */
    void showRide(Ride ride) {
        ObservableList<StationDto> stations = FXCollections.observableList(ride.getPath());
        tableView.setItems(stations);
    }

    /**
     * Shows an exception dialog to the user.
     *
     * @param message the message to show inside the dialog.
     */
    void showException(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(message);
        alert.setContentText("Careful with the next steps !");
        alert.showAndWait();
    }

    /**
     * Disables all the buttons.
     */
    void disableButtons() {
        searchButton.setDisable(true);
        addFavorite.setDisable(true);
        launchFavorite.setDisable(true);
        removeFavorite.setDisable(true);
        editFavorite.setDisable(true);
    }

    /**
     * Enables all the buttons.
     */
    void enableButtons() {
        searchButton.setDisable(false);
        addFavorite.setDisable(false);
        launchFavorite.setDisable(false);
        removeFavorite.setDisable(false);
        editFavorite.setDisable(false);
    }

    /**
     * Sets the feedback labels with the size of the ride path.
     *
     * @param size the size of the ride path.
     */
    public void setFeedBack(int size) {
        leftStatus.setText("Recherche terminée");
        rightStatus.setText("Nombre de stations : " + size);
    }

    /**
     * Shows a dialog for the user to choose a name to give its favorite ride.
     *
     * @param default_str the default name.
     * @return the name to give its favorite ride.
     */
    String showFavNamePopup(String default_str) {
        TextInputDialog dialog = new TextInputDialog(default_str);
        dialog.setTitle("Add name dialog");
        dialog.setHeaderText("The name of your favorite ride");
        dialog.setContentText("Please enter the name of your favorite ride:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(default_str);
    }

    /**
     * Shows a dialog for the user to edit its favorite ride.
     *
     * @param stations    every stations to choose from.
     * @param default_str the default name of the ride.
     * @return a triplet containing all of the new values inserted by the user.
     * @throws IllegalArgumentException is a value was missing.
     */
    Triplet<Integer, Integer, String> showEditPopup(List<StationDto> stations, String default_str)
            throws IllegalArgumentException {
        // Dialog
        EditFavoriteDialog dialog = new EditFavoriteDialog(stations, default_str);
        Optional<Triplet<Integer, Integer, String>> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("You must fill in the form.");
        }
    }
}

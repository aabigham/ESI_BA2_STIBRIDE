package atl.stibride.view;

import atl.stibride.handlers.*;
import atl.stibride.model.Ride;
import atl.stibride.presenter.Presenter;
import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.dto.StationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.controlsfx.control.SearchableComboBox;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Optional;

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

    void initComboBoxes(List<StationDto> stations) {
        ObservableList<StationDto> stationsObs = FXCollections.observableList(stations);
        originSearch.setItems(stationsObs);
        originSearch.getSelectionModel().select(0);
        destinationSearch.setItems(stationsObs);
        destinationSearch.getSelectionModel().select(0);
    }

    void initFavorites(List<FavoriteDto> favorites) {
        ObservableList<FavoriteDto> favoritesObs
                = FXCollections.observableList(favorites);
        listFavorite.setItems(favoritesObs);
        listFavorite.getSelectionModel().select(0);
    }

    void addHandlers(Presenter presenter) {
        searchButton.setOnAction(new SearchButtonHandler(presenter));
        addFavorite.setOnAction(new AddToFavoriteHandler(presenter));
        launchFavorite.setOnAction(new LaunchFavoriteHandler(presenter));
        removeFavorite.setOnAction(new RemoveFavoriteHandler(presenter));
        editFavorite.setOnAction(new EditFavoriteHandler(presenter));
    }

    StationDto getOrigin() {
        return originSearch.getSelectionModel().getSelectedItem();
    }

    StationDto getDestination() {
        return destinationSearch.getSelectionModel().getSelectedItem();
    }

    Pair<Integer, Integer> getFavorite() {
        FavoriteDto favorite = listFavorite.getSelectionModel().getSelectedItem();
        return new Pair<>(favorite.getFirstKey(), favorite.getSecondKey());
    }

    void showRide(Ride ride) {
        ObservableList<StationDto> stations
                = FXCollections.observableList(ride.getPath());
        tableView.setItems(stations);
    }

    void showException(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(message);
        alert.setContentText("Careful with the next steps !");
        alert.showAndWait();
    }

    void disableButtons() {
        searchButton.setDisable(true);
        addFavorite.setDisable(true);
        launchFavorite.setDisable(true);
        removeFavorite.setDisable(true);
        editFavorite.setDisable(true);
    }

    void enableButtons() {
        searchButton.setDisable(false);
        addFavorite.setDisable(false);
        launchFavorite.setDisable(false);
        removeFavorite.setDisable(false);
        editFavorite.setDisable(false);
    }

    String showFavNamePopup(String default_str) {
        TextInputDialog dialog = new TextInputDialog(default_str);
        dialog.setTitle("Modification dialog");
        dialog.setHeaderText("Modification of your favorite ride");
        dialog.setContentText("Please enter a new favorite name:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(default_str);
    }

    Triplet<Integer, Integer, String> showEditPopup(List<StationDto> stations, String default_str)
            throws IllegalArgumentException {
        // Dialog
        Dialog<Triplet<Integer, Integer, String>> dialog = new Dialog<>();
        dialog.setTitle("Modification dialog");
        dialog.setHeaderText("Enter the new values");

        // Button type
        ButtonType enterButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(enterButtonType);

        // Ui elements
        ObservableList<StationDto> stationsObs = FXCollections.observableList(stations);
        SearchableComboBox<StationDto> origin = new SearchableComboBox<>(stationsObs);
        origin.getSelectionModel().select(0);
        SearchableComboBox<StationDto> destination = new SearchableComboBox<>(stationsObs);
        destination.getSelectionModel().select(0);
        TextField name = new TextField();
        name.setPromptText(default_str);

        // Gridpane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("New origin:"), 0, 0);
        grid.add(origin, 1, 0);
        grid.add(new Label("New destination:"), 0, 1);
        grid.add(destination, 1, 1);
        grid.add(new Label("New name:"), 0, 2);
        grid.add(name, 1, 2);

        // Checks when name is empty
        Node loginButton = dialog.getDialogPane().lookupButton(enterButtonType);
        loginButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        // Set grid
        dialog.getDialogPane().setContent(grid);

        // Converts the result to a triplet when button clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == enterButtonType) {
                return new Triplet<>(
                        origin.getSelectionModel().getSelectedItem().getKey(),
                        destination.getSelectionModel().getSelectedItem().getKey(),
                        name.getText()
                );
            }
            return null;
        });

        Optional<Triplet<Integer, Integer, String>> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("You must fill in the form.");
        }
    }
}

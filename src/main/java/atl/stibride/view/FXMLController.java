package atl.stibride.view;

import atl.stibride.dto.StationDto;
import atl.stibride.handlers.AddToFavoriteHandler;
import atl.stibride.handlers.RemoveFavoriteHandler;
import atl.stibride.handlers.SearchButtonHandler;
import atl.stibride.presenter.Presenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.controlsfx.control.SearchableComboBox;

import java.util.List;

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
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> stationsCol;
    @FXML
    private TableColumn<?, ?> lignesCol;
    @FXML
    private ListView<?> listFavorite;
    @FXML
    private Button launchFavorite;
    @FXML
    private Button removeFavorite;
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
        leftStatus.setText("Aucune recherche lancée");
        rightStatus.setText("Nombre de stations : 0");
        //
    }

    void initComboBoxes(List<StationDto> stations) {
        ObservableList<StationDto> stationsObs
                = FXCollections.observableList(stations);
        originSearch.setItems(stationsObs);
        originSearch.getSelectionModel().select(0);
        destinationSearch.setItems(stationsObs);
        destinationSearch.getSelectionModel().select(0);
    }

    void addHandlers(Presenter presenter) {
        searchButton.setOnAction(new SearchButtonHandler(presenter));
        addFavorite.setOnAction(new AddToFavoriteHandler(presenter));
        launchFavorite.setOnAction(new AddToFavoriteHandler(presenter));
        removeFavorite.setOnAction(new RemoveFavoriteHandler(presenter));
    }
    

}

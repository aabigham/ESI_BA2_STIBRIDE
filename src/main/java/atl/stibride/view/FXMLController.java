package atl.stibride.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.SearchableComboBox;

public class FXMLController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView stibLogo;
    @FXML
    private SearchableComboBox<?> origineSearch;
    @FXML
    private SearchableComboBox<?> destinationSearch;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> stationsCol;
    @FXML
    private TableColumn<?, ?> lignesCol;
    @FXML
    private Label leftStatus;
    @FXML
    private Label rightStatus;

    @FXML
    void initialize() {
        Image metro_img = new Image(getClass().getResourceAsStream("/img/metro.png"));
        scrollPane.setContent(new ImageView(metro_img));
        Image logo_img = new Image(getClass().getResourceAsStream("/img/logo.png"));
        stibLogo.setImage(logo_img);
    }
}

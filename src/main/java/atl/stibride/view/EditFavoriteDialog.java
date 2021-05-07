package atl.stibride.view;

import atl.stibride.jdbc.dto.StationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.SearchableComboBox;
import org.javatuples.Triplet;

import java.util.List;

/**
 * This dialogs allows the user to input new values when editing a favorite.
 */
class EditFavoriteDialog extends Dialog<Triplet<Integer, Integer, String>> {

    private final ButtonType enterButtonType;
    private final SearchableComboBox<StationDto> origin;
    private final SearchableComboBox<StationDto> destination;
    private final TextField name;

    /**
     * The constructor of the dialog.
     *
     * @param stations    every stations to propose to the user.
     * @param default_str the default favorite name field.
     */
    EditFavoriteDialog(List<StationDto> stations, String default_str) {
        // Dialog
        this.setTitle("Modification dialog");
        this.setHeaderText("Enter the new values");

        // Button type
        enterButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(enterButtonType);

        // Ui elements
        ObservableList<StationDto> stationsObs = FXCollections.observableList(stations);

        origin = new SearchableComboBox<>(stationsObs);
        origin.getSelectionModel().select(0);

        destination = new SearchableComboBox<>(stationsObs);
        destination.getSelectionModel().select(0);

        name = new TextField();
        name.setPromptText(default_str);

        // GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        gridPane.add(new Label("New origin:"), 0, 0);
        gridPane.add(origin, 1, 0);
        gridPane.add(new Label("New destination:"), 0, 1);
        gridPane.add(destination, 1, 1);
        gridPane.add(new Label("New name:"), 0, 2);
        gridPane.add(name, 1, 2);

        // Checks when name is empty and deactivate button
        Node loginButton = this.getDialogPane().lookupButton(enterButtonType);
        loginButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        // Sets grid
        this.getDialogPane().setContent(gridPane);

        // Converts the result to a triplet when button clicked (show and wait method)
        this.setResultConverter(dialogButton -> {
            if (dialogButton == enterButtonType) {
                return new Triplet<>(
                        origin.getSelectionModel().getSelectedItem().getKey(),
                        destination.getSelectionModel().getSelectedItem().getKey(),
                        name.getText()
                );
            }
            return null;
        });

    }
}

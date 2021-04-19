package atl.stibride.view;

import atl.stibride.dto.StationDto;
import atl.stibride.presenter.Presenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public void addHandlers(Presenter presenter) {
        fxmlController.addHandlers(presenter);
    }
}

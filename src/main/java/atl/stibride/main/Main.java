package atl.stibride.main;

import atl.stibride.config.ConfigManager;
import atl.stibride.jdbc.exceptions.RepositoryException;
import atl.stibride.model.Model;
import atl.stibride.presenter.Presenter;
import atl.stibride.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of the project.
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set
     */
    @Override
    public void start(Stage stage) {
        try {
            // Loads config file (contains db url)
            ConfigManager.getInstance().load();
            // Model
            Model model = new Model();
            model.initialize();
            // View
            View view = new View(stage);
            // Presenter takes the view and model
            Presenter presenter = new Presenter(model, view);
            // Model is an observable
            model.addObserver(presenter);
            // Presenter initializes the view from the model datas
            presenter.initializeView();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}

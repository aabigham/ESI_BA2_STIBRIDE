package atl.stibride.main;

import atl.stibride.config.ConfigManager;
import atl.stibride.exceptions.RepositoryException;
import atl.stibride.model.Model;
import atl.stibride.model.RepoManager;
import atl.stibride.presenter.Presenter;
import atl.stibride.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            ConfigManager.getInstance().load();
            RepoManager repoManager = new RepoManager();

            Model model = new Model(repoManager);
            model.initialize();
            View view = new View(stage);
            Presenter presenter = new Presenter(model, view);

            model.addObserver(presenter);

            presenter.initialize();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}

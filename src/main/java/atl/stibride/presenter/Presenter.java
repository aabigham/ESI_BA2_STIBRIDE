package atl.stibride.presenter;

import atl.stibride.observer.Observable;
import atl.stibride.observer.Observer;
import atl.stibride.view.View;

public class Presenter implements Observer {
    private View view;

    public Presenter(View view) {
        this.view = view;
    }

    public void initialize() {
        // TODO
    }

    public void soSomething() {
        // TODO
    }

    @Override
    public void update(Observable observable, Object arg) {
        // TODO
    }
}
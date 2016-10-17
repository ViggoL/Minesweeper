package minesweeper.View;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import minesweeper.Model.Minesweeper;

/**
 * An observer, implementing the java.util.Observer interface and the method
 * update. The method update is called whenever the subject (observable), in
 * this case the TempModel, calls Observable.setChanged followed by
 * Observable.notifyObservers.
 *
 * @author anderslm@kth.se
 */
public class TimeLabel extends Label implements Observer {

    public TimeLabel() {
        super("- - -");
        this.setFont(Font.font("Consolas", 20));
    }

    public TimeLabel(String string) {
        this();
        this.setText(string);
    }

    @Override
    public void update(Observable o, Object arg) {
        Minesweeper model = (Minesweeper) o;
        this.setText("Time:" + model.getTime() + " s");
    }
}

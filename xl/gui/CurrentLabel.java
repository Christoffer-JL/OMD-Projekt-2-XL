package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class CurrentLabel extends ColoredLabel implements Observer {

    private String label = "A1";

    public CurrentLabel() {
        super("A1", Color.black);
    }

    public void update() {
        // TODO - Ska uppdatera labelvärdet
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
    }

}

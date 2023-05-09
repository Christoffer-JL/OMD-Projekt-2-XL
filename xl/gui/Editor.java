package xl.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {

    private Controller controller;

    public Editor(Controller controller) {
        setBackground(Color.WHITE);
        this.controller = controller;

        // När användare trycker på Enter så skickas inmatat text till controllern och
        // textfältet resetas till blankt
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newFormula = getText();
                controller.newFormula(newFormula);
                setText("");
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
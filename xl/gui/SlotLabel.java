package xl.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SlotLabel extends ColoredLabel implements MouseListener {

    private Controller c;
    private String address;

    public SlotLabel(Controller c, String address) {
        super("                    ", Color.WHITE, RIGHT);
        this.c = c;
        this.address = address;
        this.addMouseListener(this);
    }

    public void update() {
        // Uppdaterar texten i rutan
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        c.changeFocus(this, address);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
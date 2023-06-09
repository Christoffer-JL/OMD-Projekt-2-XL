package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import xl.gui.menu.XLMenuBar;
import xl.model.Grid;

public class XL extends JFrame {

    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();
        Grid grid = null;
        try {
            grid = new Grid();
        } catch (IOException e) {
        }
        Controller controller = new Controller(grid);
        JPanel statusPanel = new StatusPanel(statusLabel, grid);
        Editor editor = new Editor(controller);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, controller, grid, editor);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel, controller, grid, editor));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}

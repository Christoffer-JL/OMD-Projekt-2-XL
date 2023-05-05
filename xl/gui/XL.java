package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import javax.sound.sampled.Control;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jdk.jshell.execution.Util;
import xl.gui.menu.XLMenuBar;
import xl.util.Adjustment;

public class XL extends JFrame {

    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private Controller c;
    private StatusLabel statusLabel = new StatusLabel(c);
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
        c = new Controller();
        JPanel statusPanel = new StatusPanel(statusLabel, c);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, c);
        Editor editor = new Editor(c);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel));
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
        // Skapa grid
        new XL(new XLList(), new XLCounter());
        Adjustment ad = new Adjustment(20);
        String s = ad.center("SO    UTH     ");
        System.out.println(s);
    }
}

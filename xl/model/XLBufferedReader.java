package xl.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import xl.util.XLException;

public class XLBufferedReader extends BufferedReader {
    CellFactory factory;

    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
        factory = new CellFactory();
    }

    // TODO Change Object to something appropriate
    public void load(Map<String, Cell> map) {
        System.out.println("sg");
        try {
            while (ready()) {
                String string = readLine();
                int i = string.indexOf('=') + 1;
                map.put(string.substring(0, i - 1), factory.buildCell(string.substring(i, string.length())));
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}

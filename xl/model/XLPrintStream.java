package xl.model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;

public class XLPrintStream extends PrintStream {

    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public void save(Set<Entry<String, Cell>> set) {
        for (Entry<String, Cell> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue().getFormula());
        }
        flush();
        close();
    }
}

package xl.model;

import java.io.IOException;

import xl.expr.*;

public class CellFactory {

    public Cell buildCell(String str) throws IOException {
        if (str.startsWith("#")) {
            return new CommentCell(str);
        } else {
            return new ExprCell(str);
        }
    }

}

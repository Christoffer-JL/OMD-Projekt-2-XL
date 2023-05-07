package xl.model;

import xl.expr.*;

public class CommentCell implements Cell {
    public String comment;

    public CommentCell(String comment) {
        this.comment = comment;
    }

    @Override
    public double getValue(Environment e) {
        return 0;
    }

    @Override
    public String getValueAsString(Environment e) { 
        return comment.substring(1);    //returnerar kommentaren exklusive "#"
    }

    @Override
    public String getFormula() {
        return comment;     //returnerar kommentaren inkluvisve "#"
    }

}

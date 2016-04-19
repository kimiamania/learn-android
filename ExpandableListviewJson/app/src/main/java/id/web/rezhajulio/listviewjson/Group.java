package id.web.rezhajulio.listviewjson;

import java.util.ArrayList;
import java.util.List;

public class Group {

    public String string;
    public final List<Orders> children = new ArrayList<Orders>();

    public Group(String string) {
        this.string = string;
    }

}

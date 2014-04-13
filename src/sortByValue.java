import java.util.Comparator;

/**
 * Created by xzjs on 14-4-13.
 */
public class sortByValue implements Comparator{
    public int compare(Object o1,Object o2){
        Node n1 = (Node)o1;
        Node n2 = (Node)o2;
        return n1._value>n2._value?1:0;
    }
}

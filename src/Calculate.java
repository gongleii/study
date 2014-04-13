import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzjs on 14-4-13.
 */
public class Calculate {
    public char shapes[];
    public ArrayList<Node> open = new ArrayList<Node>();
    public ArrayList<Node> closed = new ArrayList<Node>();
    public int[] moveStep;

    public Calculate(String s){
        shapes=s.toCharArray();
        Cubes c=new Cubes(shapes);
        ArrayList<Cubes> lc =new ArrayList<Cubes>();
        lc.add(c);
        Node n = new Node(lc,0);
        open.add(n);
        if(!open.get(0)._lc.get(open.get(0)._lc.size()-1).isFinished()) {
            tryOneTry();
        }
    }

    private void tryOneTry(){
        closed.add(open.get(0));
        open=open.get(0).getNextNodes(open);
        if(!open.get(0)._lc.get(open.get(0)._lc.size()-1).isFinished()) {
            tryOneTry();
        }
    }


}
